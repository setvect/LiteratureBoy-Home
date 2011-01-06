package com.setvect.common.http;

import javax.servlet.http.HttpServletRequest;

import com.setvect.common.util.StringUtilAd;

public class HttpUtil {

	/**
	 * URL�� �޾� Ȩ������ ���� ��� �����ϰ� Ȩ������ �ּҸ� ���� (���� '/' ����)<br>
	 * ex) http://www.abcd.com<br>
	 * ex) http://www.abcd.com:2311<br>
	 * ex) http://127.0.0.1:2311<br>
	 * 
	 * @param request
	 * @return ���� Ȩ������ �ּҸ�
	 */
	public static String getHomepageUrl(HttpServletRequest request) {
		StringBuffer rtn = new StringBuffer("http://");
		rtn.append(request.getServerName());
		// 80��Ʈ�� �ƴ� ���� ��Ʈ �߰�
		if (request.getServerPort() != 80) {
			rtn.append(":");
			rtn.append(request.getServerPort());
		}
		return rtn.toString();
	}

	/**
	 * ���� �������� ���� ��Ʈ������ ������ ��θ� ��ȯ<br>
	 * ���� URL ���·� ����ϸ� ��<br>
	 * 
	 * ex) http://www.abcd.com/abc.jsp?aaa=data1&aaa=data2&bbb=data3
	 * 
	 * @param request
	 * @return ���� ������ URL
	 */
	public static String getCurrentPageURL(HttpServletRequest request) {
		String ctPath = request.getContextPath();
		String contextPath = ctPath == null || ctPath.equals("/") ? "" : ctPath;
		String returnurl = getHomepageUrl(request) + contextPath + request.getServletPath() + "?"
				+ StringUtilAd.null2str(request.getQueryString(), "");
		return returnurl;
	}
}
