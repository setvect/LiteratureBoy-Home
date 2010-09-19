package com.setvect.literatureboy.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.literatureboy.vo.user.User;
import com.setvect.literatureboy.web.board.BoardArticleController;
import com.setvect.literatureboy.web.board.BoardManagerController;

/**
 * 환경설정>운영자 관리 메뉴 컨트롤러
 */
@Controller
public class PageController {
	@Resource
	private BoardManagerController boardManagerController;

	@Resource
	private BoardArticleController boardArticleController;

	@RequestMapping("/page/*.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String requestURI = request.getRequestURI();
		int posStart = requestURI.lastIndexOf("/") + 1;
		int posEnd = requestURI.lastIndexOf(".do");
		String pageName = requestURI.substring(posStart, posEnd);
		User user = (User) request.getAttribute(ConstraintWeb.USER_SESSION_KEY);
		
		// 관리자가 로그인 하면 왼쪽 메뉴에 게시판 전체 목록 표시 
		if(user != null && user.isAdminF()){
			
		}
		
		
		if(pageName.equals("bm")){
			return boardManagerController.process(request, response);
		}
		
		return new ModelAndView();
	}
}