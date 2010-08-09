package com.setvect.common.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import anyframe.common.util.StringUtil;

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
	 * @param password
	 * @param algorithm
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#encodePassword(java.lang.String, java.lang.String)
	 */
	public static String encodePassword(String password, String algorithm) {
		return StringUtil.encodePassword(password, algorithm);
	}

	/**
	 * @param str
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#encodeString(java.lang.String)
	 */
	public static String encodeString(String str) {
		return StringUtil.encodeString(str);
	}

	/**
	 * @param str
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#decodeString(java.lang.String)
	 */
	public static String decodeString(String str) {
		return StringUtil.decodeString(str);
	}

	/**
	 * @param str
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#swapFirstLetterCase(java.lang.String)
	 */
	public static String swapFirstLetterCase(String str) {
		return StringUtil.swapFirstLetterCase(str);
	}

	/**
	 * @param origStr
	 * @param strToken
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#getLastString(java.lang.String, java.lang.String)
	 */
	public static String getLastString(String origStr, String strToken) {
		return StringUtil.getLastString(origStr, strToken);
	}

	/**
	 * @param str
	 * @param strToken
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#getStringArray(java.lang.String, java.lang.String)
	 */
	public static String[] getStringArray(String str, String strToken) {
		return StringUtil.getStringArray(str, strToken);
	}

	/**
	 * @param str
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#string2integer(java.lang.String)
	 */
	public static int string2integer(String str) {
		return StringUtil.string2integer(str);
	}

	/**
	 * @param integer
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#integer2string(int)
	 */
	public static String integer2string(int integer) {
		return StringUtil.integer2string(integer);
	}

	/**
	 * @param str
	 * @param pattern
	 * @return
	 * @throws Exception
	 * @see com.setvect.common.StringUtil.StringUtil#isPatternMatching(java.lang.String, java.lang.String)
	 */
	public static boolean isPatternMatching(String str, String pattern) throws Exception {
		return StringUtil.isPatternMatching(str, pattern);
	}

	/**
	 * @param str
	 * @param maxSeqNumber
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#containsMaxSequence(java.lang.String, java.lang.String)
	 */
	public static boolean containsMaxSequence(String str, String maxSeqNumber) {
		return StringUtil.containsMaxSequence(str, maxSeqNumber);
	}

	/**
	 * @param str
	 * @param invalidChars
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#containsInvalidChars(java.lang.String, char[])
	 */
	public static boolean containsInvalidChars(String str, char[] invalidChars) {
		return StringUtil.containsInvalidChars(str, invalidChars);
	}

	/**
	 * @param str
	 * @param invalidChars
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#containsInvalidChars(java.lang.String, java.lang.String)
	 */
	public static boolean containsInvalidChars(String str, String invalidChars) {
		return StringUtil.containsInvalidChars(str, invalidChars);
	}

	/**
	 * @param originalStr
	 * @param ch
	 * @param cipers
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#fillString(java.lang.String, char, int)
	 */
	public static String fillString(String originalStr, char ch, int cipers) {
		return StringUtil.fillString(originalStr, ch, cipers);
	}

	/**
	 * @param lst
	 * @param separator
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#getTokens(java.lang.String, java.lang.String)
	 */
	public static List<String> getTokens(String lst, String separator) {
		return StringUtil.getTokens(lst, separator);
	}

	/**
	 * @param lst
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#getTokens(java.lang.String)
	 */
	public static List<String> getTokens(String lst) {
		return StringUtil.getTokens(lst);
	}

	/**
	 * @param targetString
	 * @param posChar
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#convertToCamelCase(java.lang.String, char)
	 */
	public static String convertToCamelCase(String targetString, char posChar) {
		return StringUtil.convertToCamelCase(targetString, posChar);
	}

	/**
	 * @param underScore
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#convertToCamelCase(java.lang.String)
	 */
	public static String convertToCamelCase(String underScore) {
		return StringUtil.convertToCamelCase(underScore);
	}

	/**
	 * @param camelCase
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#convertToUnderScore(java.lang.String)
	 */
	public static String convertToUnderScore(String camelCase) {
		return StringUtil.convertToUnderScore(camelCase);
	}

	/**
	 * @param org
	 * @param converted
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#null2str(java.lang.String, java.lang.String)
	 */
	public static String null2str(String org, String converted) {
		return StringUtil.null2str(org, converted);
	}

	/**
	 * @param org
	 * @return
	 * @see com.setvect.common.StringUtil.StringUtil#null2str(java.lang.String)
	 */
	public static String null2str(String org) {
		return StringUtil.null2str(org);
	}

}
