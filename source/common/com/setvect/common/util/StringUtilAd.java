package com.setvect.common.util;

import java.io.IOException;
import java.security.MessageDigest;

import org.apache.commons.lang.StringUtils;

import com.setvect.common.log.LogPrinter;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * anyframe.common.util.StringUtil���� ����ϰ� �ִ� Methd Delegate
 * 
 * @version $Id$
 */
public class StringUtilAd extends StringUtils {

	/**
	 * @param word
	 * 
	 * @return sql String ���� ������ ����
	 */
	public static String getSqlString(String word) {

		word = null2str(word);
		word = replace(word, "'", "''");
		word = word.trim();
		return new String("'" + word + "'");
	}

	/**
	 * @param word
	 * @return sql String ���� like �������� �˻� �ǵ��� ����<br>
	 *         ��) '%�ܾ�%'
	 */
	public static String getSqlStringLike(String word) {
		word = null2str(word);
		word = replace(word, "'", "''");
		word = word.trim();
		return new String("'%" + word + "%'");
	}

	/**
	 * @param word
	 * @return sql String ���� like �������� �˻� �ǵ��� ����<br>
	 *         ��) '%�ܾ�'
	 */
	public static String getSqlStringLikeLeft(String word) {
		word = null2str(word);
		word = replace(word, "'", "''");
		word = word.trim();
		return new String("'%" + word + "'");
	}

	/**
	 * @param word
	 * @return sql String ���� like �������� �˻� �ǵ��� ����<br>
	 *         ��) '�ܾ�%'
	 */
	public static String getSqlStringLikeRight(String word) {
		word = null2str(word);
		word = replace(word, "'", "''");
		word = word.trim();
		return new String("'" + word + "%'");
	}

	/**
	 * ���� �°� ���ڸ� �ٲپ� �ش�.
	 * 
	 * @param src
	 *            source String
	 * @return ��ȯ�� String
	 */
	public static String toForm(String src) {
		String strBuffer = src;
		if (src == null)
			return EMPTY;
		strBuffer = replace(strBuffer, "<", "&lt;");
		strBuffer = replace(strBuffer, ">", "&gt;");
		strBuffer = replace(strBuffer, "\"", "&quot;");
		strBuffer = replace(strBuffer, "\'", "&#039;");

		// &#33324�� ���ڸ� ���� �ϱ� ���ؼ�
		strBuffer = replace(strBuffer, "&amp;#", "&#");
		return strBuffer;
	}

	/**
	 * s2 �迭���߿� s1�� ���� ������ �ִ��� �˻�
	 * 
	 * @param s1
	 *            ������
	 * @param s2
	 *            �񱳵�����
	 * @return ���Ե� ������ ������ true
	 */
	public static boolean isInclude(String s1, String[] s2) {
		return positionInclude(s1, s2) != -1;
	}

	/**
	 * @param word
	 *            ���ڿ�
	 * @return word�� null �̸� ���ڿ��� ��ȯ
	 */
	public static String null2str(String word) {
		return word == null ? "" : word;
	}

	/**
	 * @param word
	 *            ���ڿ�
	 * @param substitution
	 *            ��ü ���ڿ�
	 * @return word�� null �̸� ��ü ���ڿ��� ��ȯ
	 */
	public static String null2str(String word, String substitution) {
		return word == null ? substitution : word;
	}

	/**
	 * s2 �迭���߿� s1�� ���� ������ �ִ��� �˻�. ���� ������ �����ϴ� �迭��ġ ����
	 * 
	 * @param s1
	 *            ������
	 * @param s2
	 *            �񱳵�����
	 * @return �����ϴ� �迭�� ������ ��ġ ����/������ -1����
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
