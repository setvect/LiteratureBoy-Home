package com.setvect.literatureboy.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.common.http.CookieProcess;
import com.setvect.common.util.GenericPage;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.config.EnvirmentProperty;
import com.setvect.literatureboy.service.board.BoardArticleSearch;
import com.setvect.literatureboy.service.board.BoardManagerSearch;
import com.setvect.literatureboy.service.board.BoardService;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.user.User;
import com.setvect.literatureboy.web.board.BoardArticleController;
import com.setvect.literatureboy.web.board.BoardArticleController.JspPageKey;

/**
 * 환경설정>운영자 관리 메뉴 컨트롤러
 */
@Controller
@Scope("prototype")
public class LiteratureboyController {
	@Autowired
	private BoardArticleController boardArticleController;

	@Autowired
	private BoardService boardService;

	/** 게시판 코드에 따른 한 화면 표시 갯수 */
	private static final Map<String, Integer> BOARD_PAGE_PER_ITEM_COUNT;
	static {
		BOARD_PAGE_PER_ITEM_COUNT = new HashMap<String, Integer>();
		BOARD_PAGE_PER_ITEM_COUNT.put("BDAAAA02", 5);
		BOARD_PAGE_PER_ITEM_COUNT.put("BDAAAA03", 5);
		BOARD_PAGE_PER_ITEM_COUNT.put("BDAAAA04", 5);
		BOARD_PAGE_PER_ITEM_COUNT.put("BDAAAA05", 5);
		BOARD_PAGE_PER_ITEM_COUNT.put("BDAAAA06", 5);
		BOARD_PAGE_PER_ITEM_COUNT.put("BDAAAA08", 5);
	}

	/** 목록에서 콘텐츠내용이 보여지는 게시판 코드 */
	private static final String[] listContentViewBoard = EnvirmentProperty
			.getStringArray("com.setvect.literatureboy.board.list_content_view");

	/**
	 * 뷰에 전달할 객체를 가르키는 키
	 */
	public static enum AttributeKey {
		MAIN_ARTICLE, // 메인화면에 표시될 최신 게시물
	}

	@RequestMapping("/literatureboy/*.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String requestURI = request.getRequestURI();
		int posStart = requestURI.lastIndexOf("/") + 1;
		int posEnd = requestURI.lastIndexOf(".do");
		String pageName = requestURI.substring(posStart, posEnd);
		User user = (User) request.getAttribute(ConstraintWeb.USER_SESSION_KEY);

		boolean mobileView = checkMobileView(request, response);

		// 관리자가 로그인 하면 왼쪽 메뉴에 게시판 전체 목록 표시
		if (user != null && user.isAdminF()) {
			BoardManagerSearch pageCondition = new BoardManagerSearch(1);
			pageCondition.setPagePerItemCount(Integer.MAX_VALUE);
			GenericPage<Board> boards = boardService.getBoardPagingList(pageCondition);
			request.setAttribute(ConstraintWeb.AttributeKey.BOARD_ITEMS.name(), boards.getList());
		}

		// 일반 게시판 UI
		if (pageName.equals("bd")) {
			String boardCode = request.getParameter("searchCode");
			Integer pageItemCount = BOARD_PAGE_PER_ITEM_COUNT.get(boardCode);
			request.setAttribute(BoardArticleController.Parameter.PAGE_PER_ITEM_COUNT.name(), pageItemCount);
			HashMap<JspPageKey, String> jsp = boardArticleController.getJspPage();
			boolean include = StringUtilAd.isInclude(request.getParameter("searchCode"), listContentViewBoard);
			if (include) {
				jsp.put(BoardArticleController.JspPageKey.LIST, "/app/board/user/board_article_list_body.jsp");
			}
			else {
				jsp.put(BoardArticleController.JspPageKey.LIST, "/app/board/user/board_article_list.jsp");
			}
			jsp.put(BoardArticleController.JspPageKey.READ, "/app/board/user/board_article_read.jsp");
			boardArticleController.setJspPage(jsp);
			return boardArticleController.process(request, response);
		}
		// 관리자가 보는 게시판 UI
		else if (pageName.equals("bdm")) {
			return boardArticleController.process(request, response);
		}

		// 메인화면 보기
		else if (pageName.equals("main")) {
			BoardArticleSearch pageCondition = new BoardArticleSearch(1);
			pageCondition.setSearchCode(ConstraintWeb.MAIN_BOARD);
			pageCondition.setPagePerItemCount(1);
			GenericPage<BoardArticle> list = boardService.getArticlePagingList(pageCondition);
			BoardArticle[] mainPage = list.getList().toArray(new BoardArticle[0]);
			ModelAndView modelAndView = new ModelAndView(ConstraintWeb.LITERATUREBOY_LAYOUT);
			modelAndView.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/literatureboy/main.jsp");
			if (mainPage.length != 0) {
				modelAndView.addObject(AttributeKey.MAIN_ARTICLE.name(), mainPage[0]);
			}
			return modelAndView;
		}

		return new ModelAndView();
	}

	/**
	 * 모바일 브라우저 해더이면 모바일 페이지로. 단 강제적으로 PC화면을 본다면 PC 화면으로 나옴
	 * 
	 * @param request
	 * @param response
	 * @return true 모바일 페이지로 이동, false 그냥 처리
	 */
	private boolean checkMobileView(HttpServletRequest request, HttpServletResponse response) {
		// 모바일 브라우저 체크
		boolean mobileBrowser = isMobileBrowser(request);
		if (!mobileBrowser) {
			return false;
		}
		
		// 강제 보기 쿠기값 있는지 체크
		CookieProcess cookie = new CookieProcess(request);
		String pcView = cookie.get(ConstraintWeb.PC_VIEW_COOKIE_KEY);
		if (pcView != null && pcView.equals("true")) {
			return false;
		}

		boolean constrantPcView = Boolean.parseBoolean(request.getParameter("pc"));
		if (constrantPcView) {
			Cookie loginCookie = new Cookie(ConstraintWeb.PC_VIEW_COOKIE_KEY, "true");
			loginCookie.setPath("/");
			response.addCookie(loginCookie);
			return false;
		}
		return true;
	}

	/**
	 * 모바일 브라우저 체크
	 * 
	 * @param request
	 *            모바일 브라우저 체크
	 * @return 모바일 브라우저이면 true, 아니면 false
	 */
	private boolean isMobileBrowser(HttpServletRequest request) {
		String agent = request.getHeader("user-agent");
		
		for(String s : ConstraintWeb.MOBILE_BROWSER_AGENT){
			if(agent.contains(s)){
				return true;
			}
		}
		return false;
	}
}