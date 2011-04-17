package com.setvect.common.net;

import javax.servlet.http.HttpServletRequest;

/**
 * @version $Id$
 * @deprecated com.setvect.common.http.HttpUtil �� ���� ��� ����
 */
public class NetworkUtil {
	/**
	 * URL�� �޾� Ȩ������ ���� ��� �����ϰ� Ȩ������ �ּҸ� ���� (���� '/' ����)
	 * 
	 * @param sb
	 *            request.getRequestURL()
	 * @return ���� Ȩ������ �ּҸ�
	 */
	public static String getHomepageUrl(HttpServletRequest req) {
		StringBuffer rtn = new StringBuffer("http://");
		rtn.append(req.getServerName());
		// 80��Ʈ�� �ƴ� ���� ��Ʈ �߰�
		if (req.getServerPort() != 80) {
			rtn.append(":");
			rtn.append(req.getServerPort());
		}
		return rtn.toString();
	}
}
