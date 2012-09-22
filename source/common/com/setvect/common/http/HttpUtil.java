package com.setvect.common.http;

import javax.servlet.http.HttpServletRequest;

import com.setvect.common.util.StringUtilAd;

public class HttpUtil {

	/**
	 * URL을 받아 홈페이지 파일 경로 제거하고 홈페이지 주소만 리턴 (끝에 '/' 없음)<br>
	 * ex) http://www.abcd.com<br>
	 * ex) http://www.abcd.com:2311<br>
	 * ex) http://127.0.0.1:2311<br>
	 * 
	 * @param request
	 * @return 순수 홈페이지 주소만
	 */
	public static String getHomepageUrl(HttpServletRequest request) {
		StringBuffer rtn = new StringBuffer("http://");
		rtn.append(request.getServerName());
		// 80포트가 아닌 것은 포트 추가
		if (request.getServerPort() != 80) {
			rtn.append(":");
			rtn.append(request.getServerPort());
		}
		return rtn.toString();
	}

	/**
	 * 현재 페이지에 쿼리 스트링값을 포함한 경로를 반환<br>
	 * 리턴 URL 형태로 사용하면 됨<br>
	 * 
	 * ex) http://www.abcd.com/abc.jsp?aaa=data1&aaa=data2&bbb=data3
	 * 
	 * @param request
	 * @return 현재 페이지 URL
	 */
	public static String getCurrentPageURL(HttpServletRequest request) {
		String ctPath = request.getContextPath();
		String contextPath = ctPath == null || ctPath.equals("/") ? "" : ctPath;
		String returnurl = getHomepageUrl(request) + contextPath + request.getServletPath() + "?"
				+ StringUtilAd.null2str(request.getQueryString(), "");
		return returnurl;
	}
}
