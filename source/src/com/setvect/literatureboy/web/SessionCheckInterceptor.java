package com.setvect.literatureboy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * �α��� üũ<br>
 * ��� �׼ǿ� ���ؼ� �α��� ���θ� �˻��Ͽ� �α����� ���� ������ �α��� �������� �̵�
 */
public class SessionCheckInterceptor extends HandlerInterceptorAdapter {
	private static final String LOGIN_URL = "/etc/login.do";
	/**
	 * �α��� üũ ���� �ּ�
	 */
	private static final String[] EXCUSE_URL = { LOGIN_URL };

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String currentUrl = request.getRequestURI();
		
		// ȣ���� ���� �ּ�(~~.do �����ϴ�)�� ����
		// JSP���� form action�� �ּҷ� ���
		request.setAttribute(ConstraintWeb.SERVLET_URL, currentUrl);

		return true;
	}
}