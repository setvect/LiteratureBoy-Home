package com.setvect.common.http;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

	/**
	 * URL을 받아 홈페이지 파일 경로 제거하고 홈페이지 주소만 리턴 (끝에 '/' 없음)
	 * 
	 * @param sb
	 *            request.getRequestURL()
	 * @return 순수 홈페이지 주소만
	 */
	public static String getHomepageUrl(HttpServletRequest req) {
		StringBuffer rtn = new StringBuffer("http://");
		rtn.append(req.getServerName());
		// 80포트가 아닌 것은 포트 추가
		if (req.getServerPort() != 80) {
			rtn.append(":");
			rtn.append(req.getServerPort());
		}
		return rtn.toString();
	}
}
