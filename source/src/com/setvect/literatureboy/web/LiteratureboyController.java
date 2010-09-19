package com.setvect.literatureboy.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.config.EnvirmentProperty;
import com.setvect.literatureboy.service.board.BoardManagerSearch;
import com.setvect.literatureboy.service.board.BoardService;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.vo.user.User;
import com.setvect.literatureboy.web.board.BoardArticleController;
import com.setvect.literatureboy.web.board.BoardArticleController.JspPageKey;

/**
 * ȯ�漳��>��� ���� �޴� ��Ʈ�ѷ�
 */
@Controller
public class LiteratureboyController {
	@Resource
	private BoardArticleController boardArticleController;

	@Resource
	private BoardService boardService;

	/** ��Ͽ��� ������������ �������� �Խ��� �ڵ� */
	private static final String[] listContentViewBoard = EnvirmentProperty
			.getStringArray("com.setvect.literatureboy.board.list_content_view");

	@RequestMapping("/literatureboy/*.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String requestURI = request.getRequestURI();
		int posStart = requestURI.lastIndexOf("/") + 1;
		int posEnd = requestURI.lastIndexOf(".do");
		String pageName = requestURI.substring(posStart, posEnd);
		User user = (User) request.getAttribute(ConstraintWeb.USER_SESSION_KEY);

		// �����ڰ� �α��� �ϸ� ���� �޴��� �Խ��� ��ü ��� ǥ��
		if (user != null && user.isAdminF()) {
			BoardManagerSearch pageCondition = new BoardManagerSearch(1);
			pageCondition.setPagePerItemCount(Integer.MAX_VALUE);
			GenericPage<Board> boards = boardService.getBoardPagingList(pageCondition);
			request.setAttribute(ConstraintWeb.Attribute.BOARD_ITEMS.name(), boards.getList());
		}

		// �Ϲ� �Խ��� UI
		if (pageName.equals("bd")) {
			Map<JspPageKey, String> jsp = boardArticleController.getJspPage();
			boolean include = StringUtilAd.isInclude(request.getParameter("searchCode"), listContentViewBoard);
			if (include) {
				jsp.put(BoardArticleController.JspPageKey.LIST, "/app/board/user/board_article_list_body.jsp");
			}
			else{
				jsp.put(BoardArticleController.JspPageKey.LIST, "/app/board/user/board_article_list.jsp");	
			}			
			jsp.put(BoardArticleController.JspPageKey.READ, "/app/board/user/board_article_read.jsp");
			return boardArticleController.process(request, response);
		}
		// �����ڰ� ���� �Խ��� UI
		else if (pageName.equals("bdm")) {
			return boardArticleController.process(request, response);
		}

		return new ModelAndView();
	}
}