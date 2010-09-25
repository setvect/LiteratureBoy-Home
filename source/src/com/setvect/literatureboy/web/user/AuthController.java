package com.setvect.literatureboy.web.user;

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
import com.setvect.literatureboy.service.user.AuthSearch;
import com.setvect.literatureboy.service.user.UserService;
import com.setvect.literatureboy.vo.user.Auth;
import com.setvect.literatureboy.web.CommonUtil;
import com.setvect.literatureboy.web.ConstraintWeb;

/**
 * ȯ�漳��>��� ���� �޴� ��Ʈ�ѷ�
 */
@Controller
public class AuthController {
	/**
	 * ���� ��ɾ� ����
	 */
	public static enum Mode {
		/** USER,AGEN,Menu ����Ʈ ���� */
		LIST_FORM, SEARCH_FORM, READ_FORM, CREATE_FORM, UPDATE_FORM, CREATE_ACTION, UPDATE_ACTION, REMOVE_ACTION
	}

	/**
	 * �信 ������ ��ü�� ����Ű�� Ű
	 */
	public static enum AttributeKey {
		MODE,
		/** ����Ʈ */
		LIST,
		//
		ITEM,
		/** ������ �� �˻� ���� */
		PAGE_SEARCH
	}

	@Autowired
	private UserService authService;

	@RequestMapping("/user/auth.do")
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

		AuthSearch pageCondition = bindSearch(request);
		mav.addObject(AttributeKey.PAGE_SEARCH.name(), pageCondition);

		mav.setViewName(ConstraintWeb.LITERATUREBOY_LAYOUT);
		if (m == Mode.SEARCH_FORM) {
			String type = request.getParameter("searchType");
			String word = request.getParameter("searchWord");
			if (type.equals("url")) {
				pageCondition.setSearchUrl(word);
			}
			else if (type.equals("name")) {
				pageCondition.setSearchName(word);
			}
			m = Mode.LIST_FORM;
		}
		else if (m == Mode.READ_FORM) {
			String authSeq = request.getParameter("authSeq");
			Auth auth = authService.getAuth(Integer.parseInt(authSeq));
			mav.addObject(AuthController.AttributeKey.ITEM.name(), auth);
			mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/app/user/auth/auth_read.jsp");
		}
		else if (m == Mode.CREATE_FORM) {
			mav.addObject(AttributeKey.MODE.name(), Mode.CREATE_ACTION);
			mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/app/user/auth/auth_create.jsp");
		}
		else if (m == Mode.CREATE_ACTION) {
			Auth auth = new Auth();
			Binder.bind(request, auth);
			authService.createAuth(auth);
			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
			return mav;
		}
		else if (m == Mode.UPDATE_FORM) {
			String authSeq = request.getParameter("authSeq");
			Auth auth = authService.getAuth(Integer.parseInt(authSeq));
			mav.addObject(AuthController.AttributeKey.ITEM.name(), auth);
			mav.addObject(AttributeKey.MODE.name(), Mode.UPDATE_ACTION);
			mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/app/user/auth/auth_create.jsp");
		}
		else if (m == Mode.UPDATE_ACTION) {
			Auth auth = new Auth();
			Binder.bind(request, auth);
			authService.updateAuth(auth);
			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
			return mav;
		}
		else if (m == Mode.REMOVE_ACTION) {
			String authSeq = request.getParameter("authSeq");
			authService.removeAuth(Integer.parseInt(authSeq));
			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
			return mav;
		}
		if (m == Mode.LIST_FORM) {
			GenericPage<Auth> boardPagingList = authService.getAuthPagingList(pageCondition);
			mav.addObject(AttributeKey.LIST.name(), boardPagingList);
			mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/app/user/auth/auth_list.jsp");

			request.setAttribute(AttributeKey.LIST.name(), boardPagingList);
		}

		return mav;
	}

	/**
	 * ���ΰ�ħ�� ���� �� ���ε� ������ �ϱ����� ������ �������� redirection �ϱ� ���� �ּҸ� ����
	 * 
	 * @param request
	 * @param pageCondition
	 * @return redirection �ּ�
	 * @throws Exception
	 */
	private String getRedirectionUrl(HttpServletRequest request, AuthSearch pageCondition) throws Exception {
		UrlParameter param = new UrlParameter();
		Map<String, Object> searchParam = CommonUtil.getSearchMap(pageCondition);
		param.putAll(searchParam);

		String pageParam = new ParamEncoder("articleList").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		param.put(pageParam, pageCondition.getCurrentPage());

		String url = request.getRequestURI() + "?" + param.getParameter();
		return url;
	}

	/**
	 * request parameter���� ����¡ �� �˻� ������ ���� ��
	 * 
	 * @param request
	 * @return ����¡ �� �˻� ����
	 * @throws ServletRequestBindingException
	 */
	private AuthSearch bindSearch(HttpServletRequest request) throws ServletRequestBindingException {
		int currentPage = CommonUtil.getCurrentPage(request, "articleList");
		AuthSearch searchVO = new AuthSearch(currentPage);
		Binder.bind(request, searchVO);
		return searchVO;
	}
}