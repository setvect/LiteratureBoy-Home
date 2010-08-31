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
 * 로그인 체크<br>
 * 모든 액션에 대해서 로그인 여부를 검사하여 로그인이 되지 않으면 로그인 페이지로 이동
 */
public class SessionCheckInterceptor extends HandlerInterceptorAdapter {
	private static final String LOGIN_URL = "/user/login.do";
	/**
	 * 로그인 체크 제외 주소
	 */
	private static final String[] EXCUSE_URL = { LOGIN_URL };

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String currentUrl = request.getRequestURI();

		// 호출한 서블릿 주소(~~.do 시작하는)를 저장
		// JSP에서 form action에 주소로 사용
		request.setAttribute(ConstraintWeb.SERVLET_URL, currentUrl);

		@SuppressWarnings("unchecked")
		Map<String, String> param = request.getParameterMap();

		List<Auth> matchAuthList = getMathAuth(currentUrl, param);
		// 접근 권한 정보가 없으면 통과
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

		Collection<AuthMap> authMap = AuthCache.getAuthMapCache();
		for (Auth auth : matchAuthList) {
			for (AuthMap map : authMap) {
				if (auth.getAuthSeq() == map.getAuthSeq()) {
					return true;
				}
			}
		}

		throw new ApplicationException(user.getUserId() + "는 해당 경로의 접근 권한이 없습니다.");
	}

	/**
	 * URL과 파라미터 정보가 매치되는 권한 정보를 찾음
	 * 
	 * @param currentUrl
	 *            현 URL 주소
	 * @param param
	 *            query string 파라미터
	 * @return 현재 URL에 맞는 접근 권한 정보. 해당 사항 없으면 빈 List
	 */
	private List<Auth> getMathAuth(String currentUrl, Map<String, String> param) {
		// 현 접근 URL이 권한 맵리스트에 포함되어 있는지 여부
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
	 *            파라미터 정보
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
			// "키=값" 형태가 아니면 무시
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
	 *            현 URL
	 * @param auth
	 *            매치를 비교할 권한 정보
	 * @return URL이 매치 되면 true
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