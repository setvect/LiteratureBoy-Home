package com.setvect.common.http;

/**
 * request���� ��Ű ���� �Ľ��ѵ� ��Ű ��� �̸��� Hashtable�� ���� ���� ���� get()�� �̿��Ͽ� ��Ű�̸��� �ش��ϴ� ����
 * �����´�.
 * 
 * @version $Id: CookieProcess.java,v 1.5 2005/06/20 09:25:46 setvect Exp $
 */
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieProcess {
	private Map<String, Cookie> cook = new HashMap<String, Cookie>();

	/**
	 * ��Ű �Ľ�
	 * 
	 * @param request
	 */
	public CookieProcess(HttpServletRequest request) {
		Cookie ck[] = request.getCookies();

		// ��Ű���� ���� ���� ������.
		if (ck == null) {
			ck = new Cookie[0];
		}

		// ��Ű���� �����Ѵ�.
		for (int i = 0; i < ck.length; i++) {
			cook.put(ck[i].getName(), ck[i]);
		}
	}

	/**
	 * @param name
	 *            ��Ű�̸�
	 * @return ��Ű�̸��� ���� ��. �̸��� �ش��ϴ� ���� ������ null
	 */
	public String get(String name) {
		// ��Ű�� �ش��ϴ� ���� null
		Cookie c = cook.get(name);

		if (c == null) {
			return null;
		}
		return c.getValue();
	}

	/**
	 * @param name
	 *            ��Ű�̸�
	 * @return ��Ű ��ü , ������ null
	 */
	public Cookie getCookie(String name) {
		return cook.get(name);
	}
}