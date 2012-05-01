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
 * ȯ�漳��>��� ���� �޴� ��Ʈ�ѷ�
 */
@Controller
@Scope("prototype")
public class LiteratureboyController {
	@Autowired
	private BoardArticleController boardArticleController;

	@Autowired
	private BoardService boardService;

	/** �Խ��� �ڵ忡 ���� �� ȭ�� ǥ�� ���� */
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

	/** ��Ͽ��� ������������ �������� �Խ��� �ڵ� */
	private static final String[] listContentViewBoard = EnvirmentProperty
			.getStringArray("com.setvect.literatureboy.board.list_content_view");

	/**
	 * �信 ������ ��ü�� ����Ű�� Ű
	 */
	public static enum AttributeKey {
		MAIN_ARTICLE, // ����ȭ�鿡 ǥ�õ� �ֽ� �Խù�
	}

	@RequestMapping("/literatureboy/*.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String requestURI = request.getRequestURI();
		int posStart = requestURI.lastIndexOf("/") + 1;
		int posEnd = requestURI.lastIndexOf(".do");
		String pageName = requestURI.substring(posStart, posEnd);
		User user = (User) request.getAttribute(ConstraintWeb.USER_SESSION_KEY);

		boolean mobileView = checkMobileView(request, response);
		if (mobileView) {
			ModelAndView mav = new ModelAndView();
			if (pageName.equals("bd")) {
				mav.setViewName("redirect:/m/bd.do?" + request.getQueryString());
			}
			else {
				mav.setViewName("redirect:/m");
			}
			return mav;
		}

		// �����ڰ� �α��� �ϸ� ���� �޴��� �Խ��� ��ü ��� ǥ��
		if (user != null && user.isAdminF()) {
			BoardManagerSearch pageCondition = new BoardManagerSearch(1);
			pageCondition.setPagePerItemCount(Integer.MAX_VALUE);
			GenericPage<Board> boards = boardService.getBoardPagingList(pageCondition);
			request.setAttribute(ConstraintWeb.AttributeKey.BOARD_ITEMS.name(), boards.getList());
		}

		// �Ϲ� �Խ��� UI
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
		// �����ڰ� ���� �Խ��� UI
		else if (pageName.equals("bdm")) {
			return boardArticleController.process(request, response);
		}

		// ����ȭ�� ����
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
	 * ����� ������ �ش��̸� ����� ��������. �� ���������� PCȭ���� ���ٸ� PC ȭ������ ����
	 * 
	 * @param request
	 * @param response
	 * @return true ����� �������� �̵�, false �׳� ó��
	 */
	private boolean checkMobileView(HttpServletRequest request, HttpServletResponse response) {
		// ����� ������ üũ
		boolean mobileBrowser = isMobileBrowser(request);
		if (!mobileBrowser) {
			return false;
		}

		// ���� ���� ��Ⱚ �ִ��� üũ
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
	 * ����� ������ üũ
	 * 
	 * @param request
	 *            ����� ������ üũ
	 * @return ����� �������̸� true, �ƴϸ� false
	 */
	private boolean isMobileBrowser(HttpServletRequest request) {
		String agent = request.getHeader("user-agent");

		for (String s : ConstraintWeb.MOBILE_BROWSER_AGENT) {
			if (agent.contains(s)) {
				return true;
			}
		}
		return false;
	}
}