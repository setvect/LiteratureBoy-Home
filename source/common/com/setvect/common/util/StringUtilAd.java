package com.setvect.common.util;

import java.io.IOException;
import java.security.MessageDigest;

import org.apache.commons.lang.StringUtils;

import com.setvect.common.log.LogPrinter;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * anyframe.common.util.StringUtil에서 사용하고 있는 Methd Delegate
 * 
 * @version $Id$
 */
public class StringUtilAd extends StringUtils {

	/**
	 * @param word
	 * 
	 * @return sql String 값에 들어가도록 변경
	 */
	public static String getSqlString(String word) {

		word = null2str(word);
		word = replace(word, "'", "''");
		word = word.trim();
		return new String("'" + word + "'");
	}

	/**
	 * @param word
	 * @return sql String 값에 like 형식으로 검색 되도록 변경<br>
	 *         예) '%단어%'
	 */
	public static String getSqlStringLike(String word) {
		word = null2str(word);
		word = replace(word, "'", "''");
		word = word.trim();
		return new String("'%" + word + "%'");
	}

	/**
	 * @param word
	 * @return sql String 값에 like 형식으로 검색 되도록 변경<br>
	 *         예) '%단어'
	 */
	public static String getSqlStringLikeLeft(String word) {
		word = null2str(word);
		word = replace(word, "'", "''");
		word = word.trim();
		return new String("'%" + word + "'");
	}

	/**
	 * @param word
	 * @return sql String 값에 like 형식으로 검색 되도록 변경<br>
	 *         예) '단어%'
	 */
	public static String getSqlStringLikeRight(String word) {
		word = null2str(word);
		word = replace(word, "'", "''");
		word = word.trim();
		return new String("'" + word + "%'");
	}

	/**
	 * 폼에 맞게 문자를 바꾸어 준다.
	 * 
	 * @param src
	 *            source String
	 * @return 변환된 String
	 */
	public static String toForm(String src) {
		String strBuffer = src;
		if (src == null)
			return EMPTY;
		strBuffer = replace(strBuffer, "<", "&lt;");
		strBuffer = replace(strBuffer, ">", "&gt;");
		strBuffer = replace(strBuffer, "\"", "&quot;");
		strBuffer = replace(strBuffer, "\'", "&#039;");

		// &#33324의 글자를 보존 하기 위해서
		strBuffer = replace(strBuffer, "&amp;#", "&#");
		return strBuffer;
	}

	/**
	 * s2 배열에중에 s1과 같은 문장이 있는지 검사
	 * 
	 * @param s1
	 *            원본값
	 * @param s2
	 *            비교데이터
	 * @return 포함된 문장이 있으면 true
	 */
	public static boolean isInclude(String s1, String[] s2) {
		return positionInclude(s1, s2) != -1;
	}

	/**
	 * @param word
	 *            문자열
	 * @return word가 null 이면 빈문자열로 변환
	 */
	public static String null2str(String word) {
		return word == null ? "" : word;
	}

	/**
	 * @param word
	 *            문자열
	 * @param substitution
	 *            대체 문자열
	 * @return word가 null 이면 대체 문자열로 변환
	 */
	public static String null2str(String word, String substitution) {
		return word == null ? substitution : word;
	}

	/**
	 * s2 배열에중에 s1과 같은 문장이 있는지 검사. 만약 있으면 존제하는 배열위치 리턴
	 * 
	 * @param s1
	 *            원본값
	 * @param s2
	 *            비교데이터
	 * @return 존제하는 배열이 있으면 위치 리턴/없으면 -1리턴
	 */
	public static int positionInclude(String s1, String[] s2) {

		if (s2 == null) {
			return -1;
		}
		for (int i = 0; i < s2.length; i++) {
			if (s1.equals(s2[i]))
				return i;
		}
		return -1;
	}

	public static String encodePassword(String password, String algorithm) {
		byte[] unencodedPassword = password.getBytes();

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			LogPrinter.out.error("Exception: " + e);
			return password;
		}

		md.reset();

		md.update(unencodedPassword);

		byte[] encodedPassword = md.digest();

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < encodedPassword.length; ++i) {
			if ((encodedPassword[i] & 0xFF) < 16) {
				buf.append("0");
			}

			buf.append(Long.toString(encodedPassword[i] & 0xFF, 16));
		}

		return buf.toString();
	}

	public static String decodeString(String str) {
		BASE64Decoder dec = new BASE64Decoder();
		try {
			return new String(dec.decodeBuffer(str));
		} catch (IOException io) {
			throw new RuntimeException(io.getMessage(), io.getCause());
		}
	}

	public static String encodeString(String str) {
		BASE64Encoder encoder = new BASE64Encoder();
		return new String(encoder.encodeBuffer(str.getBytes())).trim();
	}

}
