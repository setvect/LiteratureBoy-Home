package com.setvect.literatureboy.web.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.web.ConstraintWeb;

/**
 * 환경설정>운영자 관리 메뉴 컨트롤러
 */
@Controller
public class LogoutController {
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/logout.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstraintWeb.INDEX_VIEW);
		Cookie loginCookie = new Cookie(ConstraintWeb.USER_COOKIE_KEY, null);
		loginCookie.setPath("/");
		response.addCookie(loginCookie);
		String returnUrl = "";
		returnUrl = StringUtilAd.null2str(returnUrl, "/");

		mav.setViewName("redirect:/user/login.do");

		return mav;
	}

}