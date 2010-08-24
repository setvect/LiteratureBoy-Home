package com.setvect.literatureboy.web.board;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 환경설정>운영자 관리 메뉴 컨트롤러
 */
@Controller
public class CustomController {
	@Resource
	private BoardManagerController ba;

	@RequestMapping("/custom/board.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return ba.process(request, response);
	}
}