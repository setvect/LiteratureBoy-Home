package com.setvect.literatureboy.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.setvect.common.log.LogPrinter;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.boot.ApplicationException;
import com.setvect.literatureboy.vo.user.User;

/**
 * 로그인 체크<br>
 * 모든 액션에 대해서 로그인 여부를 검사하여 로그인이 되지 않으면 로그인 페이지로 이동
 */
public class SessionCheckInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws UnsupportedEncodingException, IOException {
		String currentUrl = request.getRequestURI();

		LogPrinter.out.info("[Connect] IP: " + request.getRemoteAddr() + ", " + request.getHeader("User-Agent"));

		// 호출한 서블릿 주소(~~.do 시작하는)를 저장
		// JSP에서 form action에 주소로 사용
		request.setAttribute(ConstraintWeb.AttributeKey.SERVLET_URL.name(), currentUrl);
		User user = CommonUtil.getLoginSession(request);

		// 개발중에는 자동 로그인
		// if(user == null){
		// user = userService.getUser("setvect");
		// }

		request.setAttribute(ConstraintWeb.USER_SESSION_KEY, user);
		Map<String, String> param = makeParamMap(request);

		boolean hasAuth = AccessChecker.isAccessToUrl(user, currentUrl, param);
		if (hasAuth) {
			return true;
		}

		if (hasAuth == false && user == null) {
			String returnUrl = currentUrl + "?" + StringUtilAd.null2str(request.getQueryString(), "");
			response.sendRedirect(AccessChecker.LOGIN_URL + "?" + ConstraintWeb.RETURN_URL + "="
					+ URLEncoder.encode(returnUrl, request.getCharacterEncoding()));
			return false;
		}

		// 로그인 했는데 권한이 없으면 에러 메시지 표시
		throw new ApplicationException(user.getUserId() + "는 해당 경로의 접근 권한이 없습니다.");
		// return true;
	}

	/**
	 * 파라미터 맵을 만듬 <br>
	 * 참고로 request.getParameterMap()을 사용하면 값에 타입이 String[] 됨
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