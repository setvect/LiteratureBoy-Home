/*
 * Copyright (c) DQ Co., Ltd 
 */
package com.setvect.common.util;

/**
 *  ���� ���� ��ȯ ���� Ŭ����
 * 
 * @author <a href="mailto:setvect@idq.co.kr">����ȣ </a>
 * @version $Id: NumberFormat.java,v 1.5 2006/06/28 06:28:23 setvect Exp $
 */
public class NumberFormat {

	/**
	 * @param pattern
	 *            ǥ�� ����
	 * @param number
	 *            ������ ���Ѵ� ����
	 * @return ������ ������ ����
	 */
	public static String getNumberString(String pattern, int number) {

		java.text.DecimalFormat format = new java.text.DecimalFormat(pattern);
		String result = format.format(number);

		return result;
	}

	/**
	 * @param pattern
	 *            ǥ�� ����
	 * @param number
	 *            ������ ���Ѵ� ����
	 * @return ������ ���Ѵ� ����
	 */
	public static String getNumberString(String pattern, long number) {

		java.text.DecimalFormat format = new java.text.DecimalFormat(pattern);
		String result = format.format(number);

		return result;
	}

	/**
	 * @param pattern
	 *            ǥ�� ����
	 * @param number
	 *            ������ ���Ѵ� ����
	 * @return ������ ���Ѵ� ����
	 */
	public static String getNumberString(String pattern, double number) {

		java.text.DecimalFormat format = new java.text.DecimalFormat(pattern);
		String result = format.format(number);

		return result;
	}

	/**
	 * @param pattern
	 *            ǥ�� ����
	 * @param number
	 *            ������ ���Ѵ� ����
	 * @return ������ ���Ѵ� ����
	 */
	public static String getNumberString(String pattern, float number) {

		java.text.DecimalFormat format = new java.text.DecimalFormat(pattern);
		String result = format.format(number);

		return result;
	}

}