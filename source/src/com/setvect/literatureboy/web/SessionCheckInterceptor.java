package com.setvect.literatureboy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * 로그인 체크<br>
 * 모든 액션에 대해서 로그인 여부를 검사하여 로그인이 되지 않으면 로그인 페이지로 이동
 */
public class SessionCheckInterceptor extends HandlerInterceptorAdapter {
	private static final String LOGIN_URL = "/etc/login.do";
	/**
	 * 로그인 체크 제외 주소
	 */
	private static final String[] EXCUSE_URL = { LOGIN_URL };


	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	
		return true;
	}
}