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
 * 환경설정>운영자 관리 메뉴 컨트롤러
 */
@Controller
@Scope("prototype")
public class DevlopController {
	@Autowired
	private BoardArticleController boardArticleController;

	/**
	 * 뷰에 전달할 객체를 가르키는 키
	 */
	public static enum AttributeKey {
		MAIN_ARTICLE, // 메인화면에 표시될 최신 게시물
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