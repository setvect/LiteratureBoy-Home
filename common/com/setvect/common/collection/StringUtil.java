package com.setvect.common.collection;

import org.apache.commons.lang.StringUtils;

/**
 * 문자열 관련
 * 
 * @version $Id$
 */
public class StringUtil extends StringUtils {

	/**
	 * oracle의 nvl(...)과 동일한 기능을 하는 메소드
	 * 
	 * @param src
	 *            String type의 데이터
	 * @param ret
	 *            String type의 리턴되어질 테이터
	 * @return 변환된 String
	 */
	public static String nvl(String src, String ret) {
		if (src == null || src.equals("null") || src.equals(""))
			return ret;
		else
			return src;
	}

	/**
	 * oracle의 nvl(...)과 동일한 기능을 하는 메소드
	 * 
	 * @param src
	 *            String type의 데이터
	 * @param ret
	 *            int type의 리턴되어질 테이터
	 * @return 변환된 int
	 */
	public static int nvl(String src, int ret) {
		if (src == null || src.equalsIgnoreCase("null"))
			return ret;
		else
			return Integer.parseInt(src);
	}

	/**
	 * src가 null이면 ret를. 아니면 nval을 반환하는 메소드
	 * 
	 * @param src
	 *            String type의 데이터
	 * @param ret
	 *            String 리턴되어질 테이터
	 * @param nval
	 *            String 리턴되어질 테이터
	 * @return int 변환된 데이터
	 */
	public static String nvl(String src, String ret, String nval) {
		if (src == null || src.equalsIgnoreCase("null") || src.equals(""))
			return ret;
		else
			return nval;
	}

	/**
	 * src가 cfm이면 y_str를. 아니면 n_str을 반환하는 메소드
	 * 
	 * @param src
	 *            String type의 데이터
	 * @param cfm
	 *            String type의 조건에 사용될 데이터
	 * @param y_str
	 *            String type의 리턴될 데이터
	 * @param n_str
	 *            String type의 리턴될 데이터
	 * @return 변환된 String
	 */
	public static String nvl(String src, String cfm, String y_str, String n_str) {
		src = StringUtil.nvl(src, "");
		if (src.equals(cfm))
			return y_str;
		else
			return n_str;
	}

	/**
	 * src가 cfm이면 y_str를. 아니면 n_str을 반환하는 메소드
	 * 
	 * @param src
	 *            String type의 데이터
	 * @param cfm
	 *            String type의 조건에 사용될 데이터
	 * @param y_str
	 *            String type의 리턴될 데이터
	 * @param n_str
	 *            String type의 리턴될 데이터
	 * @return 변환된 String
	 */
	public static String nvl(int src, int cfm, String y_str, String n_str) {
		// if ( src.equalsIgnoreCase(cfm) )
		if (src == cfm)
			return y_str;
		else
			return n_str;
	}
}
