package com.setvect.literatureboy.web;

import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.boot.ApplicationException;
import com.setvect.literatureboy.vo.user.User;

/**
 * �α��� üũ<br>
 * ��� �׼ǿ� ���ؼ� �α��� ���θ� �˻��Ͽ� �α����� ���� ������ �α��� �������� �̵�
 */
public class SessionCheckInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String currentUrl = request.getRequestURI();

		// ȣ���� ���� �ּ�(~~.do �����ϴ�)�� ����
		// JSP���� form action�� �ּҷ� ���
		request.setAttribute(ConstraintWeb.SERVLET_URL, currentUrl);
		User user = CommonUtil.getLoginSession(request);
		request.setAttribute(ConstraintWeb.USER_SESSION_KEY, user);
		Map<String, String> param = makeParamMap(request);

		boolean hasAuth = AccessChecker.isAccessToUrl(user, currentUrl, param);
		if(hasAuth){
			return true;
		}
		
		if (hasAuth == false && user == null) {
			String returnUrl = currentUrl + "?" + StringUtilAd.null2str(request.getQueryString(), "");
			response.sendRedirect(AccessChecker.LOGIN_URL + "?" + ConstraintWeb.RETURN_URL + "="
					+ URLEncoder.encode(returnUrl, request.getCharacterEncoding()));
			return false;
		}

		// �α��� �ߴµ� ������ ������ ���� �޽��� ǥ��
		throw new ApplicationException(user.getUserId() + "�� �ش� ����� ���� ������ �����ϴ�.");
		// return true;
	}

	/**
	 * �Ķ���� ���� ���� <br>
	 * ����� request.getParameterMap()�� ����ϸ� ���� Ÿ���� String[] ��
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, String> makeParamMap(HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();

		@SuppressWarnings("unchecked")
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String key = paramNames.nextElement();
			param.put(key, request.getParameter(key));
		}
		return param;
	}

}