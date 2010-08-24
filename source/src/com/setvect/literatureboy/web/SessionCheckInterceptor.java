package com.setvect.literatureboy.web;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.vo.user.User;

/**
 * �α��� üũ<br>
 * ��� �׼ǿ� ���ؼ� �α��� ���θ� �˻��Ͽ� �α����� ���� ������ �α��� �������� �̵�
 */
public class SessionCheckInterceptor extends HandlerInterceptorAdapter {
	private static final String LOGIN_URL = "/user/login.do";
	/**
	 * �α��� üũ ���� �ּ�
	 */
	private static final String[] EXCUSE_URL = { LOGIN_URL };

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String currentUrl = request.getRequestURI();

		// ȣ���� ���� �ּ�(~~.do �����ϴ�)�� ����
		// JSP���� form action�� �ּҷ� ���
		request.setAttribute(ConstraintWeb.SERVLET_URL, currentUrl);

		if (StringUtilAd.isInclude(currentUrl, EXCUSE_URL)) {
			return true;
		}

		// TODO ���� üũ
		User user = CommonUtil.getLoginSession(request);
		if (user == null) {
			String returnUrl = currentUrl + "?" + StringUtilAd.null2str(request.getQueryString(), "");
			response.sendRedirect(LOGIN_URL + "?" + ConstraintWeb.RETURN_URL + "="
					+ URLEncoder.encode(returnUrl, request.getCharacterEncoding()));
			return false;
		}

		request.setAttribute(ConstraintWeb.USER_SESSION_KEY, user);
		return true;
	}
}