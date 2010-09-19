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
 * ȯ�漳��>��� ���� �޴� ��Ʈ�ѷ�
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
		
		// �����ڰ� �α��� �ϸ� ���� �޴��� �Խ��� ��ü ��� ǥ�� 
		if(user != null && user.isAdminF()){
			
		}
		
		
		if(pageName.equals("bm")){
			return boardManagerController.process(request, response);
		}
		
		return new ModelAndView();
	}
}