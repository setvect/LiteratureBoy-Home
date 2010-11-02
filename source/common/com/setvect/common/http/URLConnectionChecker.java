package com.setvect.common.http;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * url �ּҸ� ���� �������� ������� �ľ�
 * @version $Id$
 */
public class URLConnectionChecker {
	/**
	 * @param url
	 *            URL ��ü �ּ�<br>
	 *            ex) http://www.idq.co.kr/page/main.html
	 * @return ���� �����ϸ� true �ƴϸ� false;
	 */
	public static boolean isConnectionable(String url) {
		return isConnectionable(url, null);
	}

	/**
	 * @param url
	 *            ȣ��Ʈ �ּ� ������ ������ URL �ּ� <br>
	 *            ex) /page/main.html
	 * @param host
	 *            ȣ��Ʈ �ּ�<br>
	 *            ex)http://www.google.co.kr
	 * @return ���� �����ϸ� true �ƴϸ� false;
	 */
	public static boolean isConnectionable(String url, String host) {
		String address;
		if (host != null) {
			address = host + url;
		}
		else {
			address = url;
		}
		URL u;
		try {
			u = new URL(address);
			InputStream in = u.openStream();

			in.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
