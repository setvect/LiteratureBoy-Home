package com.setvect.literatureboy.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.literatureboy.config.EnvirmentProperty;
import com.setvect.literatureboy.service.board.BoardService;
import com.setvect.literatureboy.vo.board.Board;

/**
 * ����� ������
 */
@Controller
@Scope("prototype")
public class MobileController {
	@Autowired
	private BoardService boardService;

	/**
	 * �信 ������ ��ü�� ����Ű�� Ű
	 */
	public static enum AttributeKey {
		TITLE, // ������ ����
		BOARD_MENU, // ����ȭ�鿡 ǥ�õ� �Խ���
	}

	/** ��ü ���⿡ ���Ե� �Խ��� �ڵ� */
	private static final String[] ALL_VIEW_BOARD = EnvirmentProperty
			.getStringArray("com.setvect.literatureboy.board.all_view");

	/** ��ü �Խ��� */
	private final List<Board> ALL_BOARD = new ArrayList<Board>();

	@RequestMapping("/m/*.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String requestURI = request.getRequestURI();
		int posStart = requestURI.lastIndexOf("/") + 1;
		int posEnd = requestURI.lastIndexOf(".do");
		String pageName = requestURI.substring(posStart, posEnd);

		// // �Ϲ� �Խ��� UI
		// if (pageName.equals("bd")) {
		// String boardCode = request.getParameter("searchCode");
		// Integer pageItemCount = BOARD_PAGE_PER_ITEM_COUNT.get(boardCode);
		// request.setAttribute(BoardArticleController.Parameter.PAGE_PER_ITEM_COUNT.name(),
		// pageItemCount);
		// HashMap<JspPageKey, String> jsp =
		// boardArticleController.getJspPage();
		// boolean include =
		// StringUtilAd.isInclude(request.getParameter("searchCode"),
		// listContentViewBoard);
		// if (include) {
		// jsp.put(BoardArticleController.JspPageKey.LIST,
		// "/app/board/user/board_article_list_body.jsp");
		// }
		// else {
		// jsp.put(BoardArticleController.JspPageKey.LIST,
		// "/app/board/user/board_article_list.jsp");
		// }
		// jsp.put(BoardArticleController.JspPageKey.READ,
		// "/app/board/user/board_article_read.jsp");
		// boardArticleController.setJspPage(jsp);
		// return boardArticleController.process(request, response);
		// }
		// // �����ڰ� ���� �Խ��� UI
		// else if (pageName.equals("bdm")) {
		// return boardArticleController.process(request, response);
		// }

		if (ALL_BOARD.isEmpty()) {
			for (String bd : ALL_VIEW_BOARD) {
				Board a = boardService.getBoard(bd);
				ALL_BOARD.add(a);
			}
		}

		ModelAndView modelAndView = new ModelAndView(ConstraintWeb.MOBILE_LAYOUT);
		modelAndView.addObject(AttributeKey.TITLE.name(), "Literature Boy");
		modelAndView.addObject(ConstraintWeb.AttributeKey.BOARD_ITEMS.name(), ALL_BOARD);
		modelAndView.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/m/main.jsp");

		return modelAndView;
	}
}