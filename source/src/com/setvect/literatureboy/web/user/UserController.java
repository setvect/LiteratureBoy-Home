package com.setvect.literatureboy.web.user;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.common.http.UrlParameter;
import com.setvect.common.util.Binder;
import com.setvect.common.util.GenericPage;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.service.user.AuthMapSearch;
import com.setvect.literatureboy.service.user.AuthSearch;
import com.setvect.literatureboy.service.user.UserSearch;
import com.setvect.literatureboy.service.user.UserService;
import com.setvect.literatureboy.vo.user.Auth;
import com.setvect.literatureboy.vo.user.AuthMap;
import com.setvect.literatureboy.vo.user.User;
import com.setvect.literatureboy.web.CommonUtil;
import com.setvect.literatureboy.web.ConstraintWeb;

/**
 * 환경설정>운영자 관리 메뉴 컨트롤러
 */
@Controller
public class UserController {
	/**
	 * 서브 명령어 정의
	 */
	public static enum Mode {
		LIST_FORM, SEARCH_FORM, READ_FORM, CREATE_FORM, UPDATE_FORM, CREATE_ACTION, UPDATE_ACTION, REMOVE_ACTION,
		// Auth Mapping
		AUTHMAP_FORM, AUTHMAP_ACTION
	}

	/**
	 * 뷰에 전달할 객체를 가르키는 키
	 */
	public static enum AttributeKey {
		MODE,
		/** 리스트 */
		LIST,
		//
		ITEM,
		/** 페이지 및 검색 정보 */
		PAGE_SEARCH,
		// 권한 정보 목록
		AUTH_LIST,
	}

	@Autowired
	private UserService userService;

	@RequestMapping("/user/user.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String mode = request.getParameter("mode");

		Mode m;
		if (StringUtilAd.isEmpty(mode)) {
			m = Mode.LIST_FORM;
		}
		else {
			m = Mode.valueOf(mode);
		}

		UserSearch pageCondition = bindSearch(request);
		mav.addObject(AttributeKey.PAGE_SEARCH.name(), pageCondition);

		mav.setViewName(ConstraintWeb.LITERATUREBOY_LAYOUT);
		if (m == Mode.SEARCH_FORM) {
			String type = request.getParameter("searchType");
			String word = request.getParameter("searchWord");
			if (type.equals("id")) {
				pageCondition.setSearchId(word);
			}
			else if (type.equals("name")) {
				pageCondition.setSearchName(word);
			}
			m = Mode.LIST_FORM;
		}
		else if (m == Mode.READ_FORM) {
			String userId = request.getParameter("userId");
			User user = userService.getUser(userId);
			mav.addObject(AttributeKey.ITEM.name(), user);
			mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/app/user/manager/user_read.jsp");
		}
		else if (m == Mode.CREATE_FORM) {
			mav.addObject(AttributeKey.MODE.name(), Mode.CREATE_ACTION);
			mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/app/user/manager/user_create.jsp");
		}
		else if (m == Mode.CREATE_ACTION) {
			User user = new User();
			Binder.bind(request, user);
			user.setPasswd(StringUtilAd.encodePassword(user.getPasswd(), ConstraintWeb.PASSWD_ALGORITHM));
			userService.createUser(user);
			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
			return mav;
		}
		else if (m == Mode.UPDATE_FORM) {
			String userId = request.getParameter("userId");
			User user = userService.getUser(userId);
			mav.addObject(AttributeKey.ITEM.name(), user);
			mav.addObject(AttributeKey.MODE.name(), Mode.UPDATE_ACTION);
			mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/app/user/manager/user_create.jsp");
		}
		else if (m == Mode.UPDATE_ACTION) {
			User user = new User();
			Binder.bind(request, user);
			if (StringUtilAd.isEmpty(user.getPasswd())) {
				// 비밀번호가 입력되지 않으면 그전 비밀번호 유지
				User temp = userService.getUser(user.getUserId());
				user.setPasswd(temp.getPasswd());
			}	
			else {
				user.setPasswd(StringUtilAd.encodePassword(user.getPasswd(), ConstraintWeb.PASSWD_ALGORITHM));
			}
			userService.updateUser(user);
			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
			return mav;
		}
		else if (m == Mode.REMOVE_ACTION) {
			String userId = request.getParameter("userId");
			User user = userService.getUser(userId);
			user.setDeleteF(true);
			userService.updateUser(user);
			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
			return mav;
		}
		else if (m == Mode.AUTHMAP_FORM) {
			String userId = request.getParameter("userId");
			User user = userService.getUser(userId);
			AuthSearch search = new AuthSearch(1);
			search.setPagePerItemCount(Integer.MAX_VALUE);
			GenericPage<Auth> authList = userService.getAuthPagingList(search);
			Collection<Auth> auth = authList.getList();

			AuthMapSearch authSearch = new AuthMapSearch(1);
			authSearch.setPagePerItemCount(Integer.MAX_VALUE);
			authSearch.setSearchUserId(userId);
			GenericPage<AuthMap> authMap = userService.getAuthMapPagingList(authSearch);
			Collection<AuthMap> map = authMap.getList();

			for (Auth a : auth) {
				boolean ishave = false;
				for (AuthMap mm : map) {
					if (a.getAuthSeq() == mm.getAuthSeq()) {
						ishave = true;
						continue;
					}
				}
				if (ishave) {
					a.setAuthHave(true);
				}
			}

			mav.addObject(AttributeKey.ITEM.name(), user);
			mav.addObject(AttributeKey.AUTH_LIST.name(), authList);
			mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/app/user/manager/user_authmap.jsp");
		}
		else if (m == Mode.AUTHMAP_ACTION) {
			String userId = request.getParameter("userId");
			String[] authSeq = request.getParameterValues("authSeq");
			userService.removeAuthMapForUserId(userId);
			if (authSeq != null) {
				for (String seq : authSeq) {
					AuthMap map = new AuthMap();
					int authNum = Integer.parseInt(seq);
					map.setAuthSeq(authNum);
					map.setUserId(userId);
					userService.createAuthMap(map);
				}
			}

			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
			return mav;
		}

		if (m == Mode.LIST_FORM) {
			GenericPage<User> boardPagingList = userService.getPageList(pageCondition);
			mav.addObject(AttributeKey.LIST.name(), boardPagingList);
			mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/app/user/manager/user_list.jsp");

			request.setAttribute(AttributeKey.LIST.name(), boardPagingList);
		}

		return mav;
	}

	/**
	 * 새로고침을 통한 재 업로드 방지를 하기위해 정해진 페이지로 redirection 하기 위한 주소를 제공
	 * 
	 * @param request
	 * @param pageCondition
	 * @return redirection 주소
	 * @throws Exception
	 */
	private String getRedirectionUrl(HttpServletRequest request, UserSearch pageCondition) throws Exception {
		UrlParameter param = new UrlParameter();
		Map<String, Object> searchParam = CommonUtil.getSearchMap(pageCondition);
		param.putAll(searchParam);

		String pageParam = new ParamEncoder("articleList").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		param.put(pageParam, pageCondition.getCurrentPage());

		String url = request.getRequestURI() + "?" + param.getParameter();
		return url;
	}

	/**
	 * request parameter에서 페이징 및 검색 정보를 추출 함
	 * 
	 * @param request
	 * @return 페이징 및 검색 정보
	 * @throws ServletRequestBindingException
	 */
	private UserSearch bindSearch(HttpServletRequest request) throws ServletRequestBindingException {
		int currentPage = CommonUtil.getCurrentPage(request, "articleList");
		UserSearch searchVO = new UserSearch(currentPage);
		Binder.bind(request, searchVO);
		return searchVO;
	}
}