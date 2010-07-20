package com.setvect.literatureboy.boot;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * WAS가 실행되면 어플리케이션에 기본적인 설정값, 로그설정등을 해준다. <br>
 * $Id$
 */
public class EnvirmentInit extends HttpServlet {
	public EnvirmentInit() {
	}

	public void init() throws ServletException {
		super.init();

		ServletConfig config = getServletConfig();

		ServletContext sc = config.getServletContext();
		/*
		 * OS입장에서 웹루트 디렉토리
		 */
		String webBase = sc.getRealPath("/");
		System.out.println(webBase);

	}

	public void destroy() {
		super.destroy();
	}
}