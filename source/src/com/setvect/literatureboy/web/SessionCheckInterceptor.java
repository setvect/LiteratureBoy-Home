package com.setvect.literatureboy.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.boot.ApplicationException;
import com.setvect.literatureboy.service.user.AuthCache;
import com.setvect.literatureboy.vo.user.Auth;
import com.setvect.literatureboy.vo.user.AuthMap;
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

		@SuppressWarnings("unchecked")
		Map<String, String> param = request.getParameterMap();

		List<Auth> matchAuthList = getMathAuth(currentUrl, param);
		// ���� ���� ������ ������ ���
		if (StringUtilAd.isInclude(currentUrl, EXCUSE_URL) || matchAuthList.size() == 0) {
			return true;
		}

		User user = CommonUtil.getLoginSession(request);
		if (user == null) {
			String returnUrl = currentUrl + "?" + StringUtilAd.null2str(request.getQueryString(), "");
			response.sendRedirect(LOGIN_URL + "?" + ConstraintWeb.RETURN_URL + "="
					+ URLEncoder.encode(returnUrl, request.getCharacterEncoding()));
			return false;
		}
		request.setAttribute(ConstraintWeb.USER_SESSION_KEY, user);

		Collection<AuthMap> authMap = AuthCache.getAuthMapCache(user.getUserId());
		for (Auth auth : matchAuthList) {
			for (AuthMap map : authMap) {
				if (auth.getAuthSeq() == map.getAuthSeq()) {
					return true;
				}
			}
		}

//		throw new ApplicationException(user.getUserId() + "�� �ش� ����� ���� ������ �����ϴ�.");
		return true;
	}

	/**
	 * URL�� �Ķ���� ������ ��ġ�Ǵ� ���� ������ ã��
	 * 
	 * @param currentUrl
	 *            �� URL �ּ�
	 * @param param
	 *            query string �Ķ����
	 * @return ���� URL�� �´� ���� ���� ����. �ش� ���� ������ �� List
	 */
	private List<Auth> getMathAuth(String currentUrl, Map<String, String> param) {
		// �� ���� URL�� ���� �ʸ���Ʈ�� ���ԵǾ� �ִ��� ����
		List<Auth> matchAuthList = new ArrayList<Auth>();
		Collection<Auth> authList = AuthCache.getAuthCache();
		for (Auth auth : authList) {
			boolean urlMatch = false;
			boolean paramMath = false;
			urlMatch = isUrlMatch(currentUrl, auth);
			if (urlMatch) {
				paramMath = isParamMatch(param, auth);
			}
			if (urlMatch && paramMath) {
				matchAuthList.add(auth);
			}
		}
		return matchAuthList;
	}

	/**
	 * @param param
	 *            �Ķ���� ����
	 * @param auth
	 * @return
	 */
	private boolean isParamMatch(Map<String, String> param, Auth auth) {
		String paramString = auth.getParameter();
		if (paramString.equals("*")) {
			return true;
		}
		String urlSplit[] = paramString.split("[&]");
		for (String token : urlSplit) {
			String[] t = token.split("[=]");
			// "Ű=��" ���°� �ƴϸ� ����
			if (t.length != 2) {
				continue;
			}
			String name = t[0];
			String value = t[1];
			String paramValue = param.get(name);
			if (paramValue == null) {
				return false;
			}

			if (value.endsWith("*")) {
				String subValue = value.substring(0, value.length() - 1);
				if (!paramValue.startsWith(subValue)) {
					return false;
				}
			}
			else {
				if (!paramValue.equals(value)) {
					return false;
				}
			}

		}
		return true;
	}

	/**
	 * @param currentUrl
	 *            �� URL
	 * @param auth
	 *            ��ġ�� ���� ���� ����
	 * @return URL�� ��ġ �Ǹ� true
	 */
	private boolean isUrlMatch(String currentUrl, Auth auth) {
		boolean urlMatch = false;
		String url = auth.getUrl();
		if (url.endsWith("*")) {
			url = url.substring(0, url.length() - 1);
			if (currentUrl.startsWith(url)) {
				urlMatch = true;
			}
		}
		else {
			if (currentUrl.equals(url)) {
				urlMatch = true;
			}
		}
		return urlMatch;
	}
}