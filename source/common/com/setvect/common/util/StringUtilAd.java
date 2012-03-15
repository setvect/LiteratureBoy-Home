package com.setvect.common.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.setvect.common.log.LogPrinter;

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
	 * Html �ڵ带 �ؽ�Ʈ�� �ڵ�ȭ ��ȯ
	 * 
	 * @param src
	 *            html���ڿ�
	 * @return text�� ����� ���ڿ�
	 */
	public static String toText(String src) {
		String strBuffer = null2str(src, "");
		strBuffer = replace(strBuffer, "&", "&amp;");
		strBuffer = replace(strBuffer, "<", "&lt;");
		strBuffer = replace(strBuffer, ">", "&gt;");
		strBuffer = replace(strBuffer, "\"", "&quot;");
		strBuffer = replace(strBuffer, "\'", "&#039;");
		strBuffer = replace(strBuffer, " ", "&nbsp;");
		// strBuffer.replaceAll(" ", "&nbsp;");
		strBuffer = replace(strBuffer, "\n", "<br>");

		// &#33324�� ���ڸ� ���� �ϱ� ���ؼ�
		strBuffer = replace(strBuffer, "&amp;#", "&#");
		return strBuffer;
	}

	/**
	 * ���๮�ڸ� br �ױ׸� ����
	 * 
	 * @param src
	 *            source String
	 * @return ��ȯ�� String
	 */
	public static String toBr(String src) {
		if (src == null) {
			return StringUtilAd.EMPTY;
		}
		String strBuffer = src;
		return replace(strBuffer, "\n", "<br>");
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
		if (word == null || word.equals("")) {
			return substitution;
		}
		else {
			return word;
		}
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

	/**
	 * @param str
	 *            base64�� �� ���ڿ�
	 * @return ���ڵ� �� ���ڿ�
	 */
	public static String decodeString(String str) {
		BASE64Decoder dec = new BASE64Decoder();
		try {
			return new String(dec.decodeBuffer(str));
		} catch (IOException io) {
			throw new RuntimeException(io.getMessage(), io.getCause());
		}
	}

	/**
	 * @param str
	 *            ���ڵ� �� ���ڿ�
	 * @return base64 ���ڿ�
	 */
	public static String encodeString(String str) {
		BASE64Encoder encoder = new BASE64Encoder();
		return new String(encoder.encodeBuffer(str.getBytes())).trim();
	}

	/**
	 * ����Ʈ ���� ��Ʈ�� ���� (�ѱ� ���ڵ��� ���� �ȵ� ���� ����)
	 * 
	 * @param str
	 *            ��Ʈ��
	 * @param sz
	 *            �ڸ� ����Ʈ��
	 * @return �߶��� ��Ʈ��
	 */
	public static String substringByte(String str, int limit) {

		if (str == null)
			return str;

		int len = str.length();
		int cnt = 0, index = 0;

		while (index < len && cnt < limit) {
			if (str.charAt(index++) < 256) // 1����Ʈ ���ڶ��...
				cnt++; // ���� 1 ����
			else {
				cnt += 2; // ���� 2 ����
			}
		}

		if (index < len && limit >= cnt)
			str = str.substring(0, index);
		else if (index < len && limit < cnt)
			str = str.substring(0, index - 1);
		return str;
	}

	/**
	 * ���� ǥ���Ҷ� ��Ʈ�� ������ ǥ�� (...)<br>
	 * ����Ʈ ����
	 * 
	 * @param s
	 *            ������Ʈ��
	 * @param limit
	 *            ǥ�� �Ѱ�
	 * @return ���� ó���� ��Ʈ��
	 */
	public static String lessen(String s, int limit) {
		String t;
		// ... ���ڿ� ������
		t = substringByte(s, limit - 3);
		if (s == null || s.equals(t)) {
			return t;
		}
		else {
			return t + "...";
		}
	}

	/**
	 * html �ױ� ����
	 * 
	 * @param src
	 *            Html �ڵ尡 �� �ؽ�Ʈ ������
	 * @return ���� TEXT �����͸� ����
	 */
	public static String clearHtml(String src) {
		Pattern pa = Pattern.compile("<style>.+?</style>", Pattern.CASE_INSENSITIVE);
		Matcher ma = pa.matcher(src);
		String s = ma.replaceAll("");

		pa = Pattern.compile("<.+?>", Pattern.CASE_INSENSITIVE);
		ma = pa.matcher(s);
		// return src.replaceAll(
		// "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
		String clearStr = ma.replaceAll("").replaceAll("&nbsp;", " ").replaceAll("&quot;", "\"").replaceAll("&gt;", "")
				.replaceAll("&lt;", "").replaceAll("&amp;", "");
		// �߰� �κп� �����̽� ������ �ϳ��� ���
		// "bcde    feafg" ===> "bcde feafg"
		StringTokenizer st = new StringTokenizer(clearStr, " \r\n");
		String rtnValue = "";
		while (st.hasMoreTokens()) {
			rtnValue += st.nextToken() + " ";
		}
		return rtnValue;
	}

	/**
	 * Ư�� ���� ����
	 * 
	 * @param str
	 * @return
	 */
	public static String removeSpecialChar(String str) {
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		str = str.replaceAll(match, " ");
		return str;
	}
}
