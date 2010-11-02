package com.setvect.common.http;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * url 주소를 통해 정상적인 경로인지 파악
 * @version $Id$
 */
public class URLConnectionChecker {
	
	/**
	 * @param url
	 *            URL 전체 주소<br>
	 *            ex) http://www.idq.co.kr/page/main.html
	 * @return 접근 가능하면 true 아니면 false;
	 */
	public static boolean isConnectionable(String url) {
		return isConnectionable(url, null);
	}

	/**
	 * @param url
	 *            호스트 주소 영역을 제외한 URL 주소 <br>
	 *            ex) /page/main.html
	 * @param host
	 *            호스트 주소<br>
	 *            ex)http://www.google.co.kr
	 * @return 접근 가능하면 true 아니면 false;
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
