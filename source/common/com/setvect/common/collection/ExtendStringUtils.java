package com.setvect.common.collection;

import org.apache.commons.lang.StringUtils;

/**
 * ���ڿ� ����
 * 
 * @version $Id$
 */
public class ExtendStringUtils extends StringUtils {
	/**
	 * oracle�� nvl(...)�� ������ ����� �ϴ� �޼ҵ�
	 * 
	 * @param src
	 *            String type�� ������
	 * @param ret
	 *            String type�� ���ϵǾ��� ������
	 * @return ��ȯ�� String
	 */
	public static String nvl(String src, String ret) {
		if (src == null || src.equals("null") || src.equals(""))
			return ret;
		else
			return src;
	}

	/**
	 * oracle�� nvl(...)�� ������ ����� �ϴ� �޼ҵ�
	 * 
	 * @param src
	 *            String type�� ������
	 * @param ret
	 *            int type�� ���ϵǾ��� ������
	 * @return ��ȯ�� int
	 */
	public static int nvl(String src, int ret) {
		if (src == null || src.equalsIgnoreCase("null"))
			return ret;
		else
			return Integer.parseInt(src);
	}

	/**
	 * src�� null�̸� ret��. �ƴϸ� nval�� ��ȯ�ϴ� �޼ҵ�
	 * 
	 * @param src
	 *            String type�� ������
	 * @param ret
	 *            String ���ϵǾ��� ������
	 * @param nval
	 *            String ���ϵǾ��� ������
	 * @return int ��ȯ�� ������
	 */
	public static String nvl(String src, String ret, String nval) {
		if (src == null || src.equalsIgnoreCase("null") || src.equals(""))
			return ret;
		else
			return nval;
	}

	/**
	 * src�� cfm�̸� y_str��. �ƴϸ� n_str�� ��ȯ�ϴ� �޼ҵ�
	 * 
	 * @param src
	 *            String type�� ������
	 * @param cfm
	 *            String type�� ���ǿ� ���� ������
	 * @param y_str
	 *            String type�� ���ϵ� ������
	 * @param n_str
	 *            String type�� ���ϵ� ������
	 * @return ��ȯ�� String
	 */
	public static String nvl(String src, String cfm, String y_str, String n_str) {
		src = ExtendStringUtils.nvl(src, "");
		if (src.equals(cfm))
			return y_str;
		else
			return n_str;
	}

	/**
	 * src�� cfm�̸� y_str��. �ƴϸ� n_str�� ��ȯ�ϴ� �޼ҵ�
	 * 
	 * @param src
	 *            String type�� ������
	 * @param cfm
	 *            String type�� ���ǿ� ���� ������
	 * @param y_str
	 *            String type�� ���ϵ� ������
	 * @param n_str
	 *            String type�� ���ϵ� ������
	 * @return ��ȯ�� String
	 */
	public static String nvl(int src, int cfm, String y_str, String n_str) {
		// if ( src.equalsIgnoreCase(cfm) )
		if (src == cfm)
			return y_str;
		else
			return n_str;
	}
}
