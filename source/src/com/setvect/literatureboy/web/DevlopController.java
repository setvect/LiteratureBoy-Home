package com.setvect.literatureboy.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.literatureboy.web.board.BoardArticleController;
import com.setvect.literatureboy.web.board.BoardArticleController.JspPageKey;

/**
 * ȯ�漳��>��� ���� �޴� ��Ʈ�ѷ�
 */
@Controller
@Scope("prototype")
public class DevlopController {
	@Autowired
	private BoardArticleController boardArticleController;

	/**
	 * �信 ������ ��ü�� ����Ű�� Ű
	 */
	public static enum AttributeKey {
		MAIN_ARTICLE, // ����ȭ�鿡 ǥ�õ� �ֽ� �Խù�
	}

	@RequestMapping("/devlop/*.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(ConstraintWeb.DEVLOP_LAYOUT);
		request.setAttribute(ConstraintWeb.AttributeKey.MODEL_VIEW.name(), mav);
		HashMap<JspPageKey, String> jsp = boardArticleController.getJspPage();
		jsp.put(BoardArticleController.JspPageKey.LIST, "/app/board/board_article_list_body.jsp");
		boardArticleController.setJspPage(jsp);

		return boardArticleController.process(request, response);
	}
}