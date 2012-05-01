package com.setvect.literatureboy.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.common.jsp.URLParameter;
import com.setvect.literatureboy.config.EnvirmentProperty;
import com.setvect.literatureboy.service.board.BoardService;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.web.board.BoardArticleController;
import com.setvect.literatureboy.web.board.BoardArticleController.JspPageKey;
import com.setvect.literatureboy.web.etc.EmailGetController;

/**
 * ����� ������
 */
@Controller
@Scope("prototype")
public class MobileController {
	@Autowired
	private BoardService boardService;

	@Autowired
	private BoardArticleController boardArticleController;

	@Autowired
	private EmailGetController emailGetController;

	/**
	 * �信 ������ ��ü�� ����Ű�� Ű
	 */
	public static enum AttributeKey {
		STATUS, // ���� ����
		BOARD_MENU, // ����ȭ�鿡 ǥ�õ� �Խ���
		LINK_PARAMETER, // ���� ������ ��ũ �ּҿ� �Ķ���� ����
	}

	public static enum Menu {
		MAIN, // ���� �޴�
		ALL, // ��ü ����
	}

	/** ��ü ���⿡ ���Ե� �Խ��� �ڵ� */
	private static final String[] ALL_VIEW_BOARD = EnvirmentProperty
			.getStringArray("com.setvect.literatureboy.board.all_view");

	/** ��ü �Խ��� */
	private final List<Board> ALL_BOARD = new ArrayList<Board>();

	@RequestMapping("/m/*.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (ALL_BOARD.isEmpty()) {
			loadViewBoardList();
		}

		String requestURI = request.getRequestURI();
		int posStart = requestURI.lastIndexOf("/") + 1;
		int posEnd = requestURI.lastIndexOf(".do");
		String pageName = requestURI.substring(posStart, posEnd);

		// �Ϲ� �Խ��� UI
		if (pageName.equals("bd")) {
			request.setAttribute(BoardArticleController.Parameter.PAGE_PER_ITEM_COUNT.name(), 10);
			HashMap<JspPageKey, String> jsp = boardArticleController.getJspPage();
			jsp.put(BoardArticleController.JspPageKey.LIST, "/m/app/board/board_article_list.jsp");
			jsp.put(BoardArticleController.JspPageKey.READ, "/m/app/board/board_article_read.jsp");
			boardArticleController.setJspPage(jsp);
			ModelAndView mav = new ModelAndView(ConstraintWeb.MOBILE_LAYOUT);
			request.setAttribute(ConstraintWeb.AttributeKey.MODEL_VIEW.name(), mav); 
			
			mav = boardArticleController.process(request, response);

			Board board = (Board) mav.getModel().get(BoardArticleController.AttributeKey.BOARD.name());
			MobilePageStatus ps = new MobilePageStatus(board.getName(), Menu.MAIN);
			ps.setUrlParam(getUrlParam(request));
			mav.addObject(AttributeKey.STATUS.name(), ps);

			return mav;
		}
		// �̸��� �ּ� �˱�
		else if (pageName.equals("emailget")) {
			ModelAndView modelAndView = emailGetController.process(request, response);

			// ���� ���� �� �̸��� ������ ����� �������� �ε� �ǰ� ��.
			// �� ������ ������ �ſ� �������̱� ������ ���� ���� ����� �ƴ�.
			String viewName = modelAndView.getViewName();

			MobilePageStatus ps = new MobilePageStatus("���мҳ� �̸��� �˱�", Menu.MAIN);
			modelAndView.addObject(AttributeKey.STATUS.name(), ps);
			modelAndView.setViewName(ConstraintWeb.MOBILE_LAYOUT);
			modelAndView.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/m/" + viewName + ".jsp");
			return modelAndView;
		}
		else {
			// ����ȭ��
			ModelAndView modelAndView = new ModelAndView(ConstraintWeb.MOBILE_LAYOUT);
			MobilePageStatus ps = new MobilePageStatus("Literature Boy", Menu.MAIN);
			modelAndView.addObject(AttributeKey.STATUS.name(), ps);
			modelAndView.addObject(ConstraintWeb.AttributeKey.BOARD_ITEMS.name(), ALL_BOARD);
			modelAndView.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/m/main.jsp");
			return modelAndView;
		}
	}

	/**
	 * ���� �ּҿ� �ĸ����� ���� �̿��ؼ� ��ũ ���� ���� �ϱ� ����
	 * 
	 * @urlParam request
	 * @return ���� �ּҿ� �Ķ���� ������ �״�� ����
	 */
	private URLParameter getUrlParam(HttpServletRequest request) {
		String current = (String) request.getAttribute(ConstraintWeb.AttributeKey.SERVLET_URL.name());
		URLParameter urlParam = new URLParameter(current, request.getCharacterEncoding());
		urlParam.put(request);

		return urlParam;
	}

	/**
	 * ǥ�ðԽ��� ��ü �ε�
	 */
	private void loadViewBoardList() {
		ALL_BOARD.clear();
		for (String bd : ALL_VIEW_BOARD) {
			Board a = boardService.getBoard(bd);
			ALL_BOARD.add(a);
		}
	}

	/**
	 * ����� ������ ���� ��
	 */
	public static class MobilePageStatus {
		/** ������ ��� ���� */
		private String title;

		/** ���õ� �޴� */
		private Menu menu;

		/** ���� ������ �ּҿ�, �Ķ���� */
		private URLParameter urlParam;

		/**
		 * @urlParam t ������ ����
		 * @urlParam m ���� �޴�
		 */
		public MobilePageStatus(String t, Menu m) {
			title = t;
			menu = m;
		}

		/**
		 * @return ������ ��� ����
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @urlParam title ������ ��� ����
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * @return ���õ� �޴�
		 */
		public Menu getMenu() {
			return menu;
		}

		/**
		 * @urlParam menu ���õ� �޴�
		 */
		public void setMenu(Menu menu) {
			this.menu = menu;
		}

		/**
		 * @return the urlParam
		 */
		public URLParameter getUrlParam() {
			return urlParam;
		}

		/**
		 * @urlParam urlParam the urlParam to set
		 */
		public void setUrlParam(URLParameter param) {
			this.urlParam = param;
		}

	}
}
