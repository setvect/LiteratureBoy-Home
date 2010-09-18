/*
 * Copyright (c) DQ Co., Ltd
 */
package com.setvect.common.date;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * ��¥�� ������ ��Ÿ���� <br>
 * ��¥ ���� �˻��� �ʿ��� �Ķ���� ��Ȱ�� �� �� ����
 * 
 * @author <a href="mailto:setvect@idq.co.kr">����ȣ </a>
 * @version $Id: DateRange.java,v 1.6 2006/06/28 06:28:23 setvect Exp $
 */
public class DateRange implements Serializable {

	/** */
	private static final long serialVersionUID = -4338839600919085275L;

	/** ���� ��¥ */
	private Date start;

	/** ���� ��¥ */
	private Date end;

	/** �Ⱓ ���� ���� ��¥ ������ */
	public static final String UNLIMITE_DATE_START = "1990-01-01";

	/** �Ⱓ ���� ���� ��¥ ������ */
	public static final String UNLIMITE_DATE_END = "2100-12-31";

	/**
	 * @return 1990-01-01 ~ 2100-12-31 ��¥ ���� ����
	 * @see #UNLIMITE_DATE_START
	 * @see #UNLIMITE_DATE_END
	 */
	public static DateRange getMaxRange() {
		return new DateRange(UNLIMITE_DATE_START, UNLIMITE_DATE_END);
	}

	/**
	 * ���� ��¥�� �������� �ؼ� ���� ���� ���� �Ѵ�.
	 * 
	 * @param diff
	 */
	public DateRange(int diff) {
		String st;
		String ed;

		Calendar cal;

		// ���
		if (diff > 0) {
			cal = Calendar.getInstance();
			st = DateUtil.getCalendarDate(cal);
			cal.add(Calendar.DAY_OF_YEAR, diff);
			ed = DateUtil.getCalendarDate(cal);
		}
		// ����
		else {
			cal = Calendar.getInstance();
			ed = DateUtil.getCalendarDate(cal);
			cal.add(Calendar.DAY_OF_YEAR, diff);
			st = DateUtil.getCalendarDate(cal);
		}

		start = DateUtil.getDate(st, "yyyy-MM-dd");
		end = DateUtil.getDate(ed, "yyyy-MM-dd");

	}

	/**
	 * ��¥ ������ �ش� �⵵�� �޿� 1���� �״��� ���������� �Ѵ�.
	 * 
	 * @param year
	 *            �⵵
	 * @param ed
	 *            �� 0: 1����, 1: 2����, ..., 11: 12����
	 */
	public DateRange(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);

		// �ش� ���� �� ���� ��¥�� �������ؼ�
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		end = DateUtil.getDate(cal);

		cal.set(Calendar.DATE, 1);
		start = DateUtil.getDate(cal);
	}

	/**
	 * ��¥���� ��ü ����. �⺻ ��¥ ���� (yyyy-MM-dd)���� ��¥ ��ȯ
	 * 
	 * @param st
	 *            ���۳�¥
	 * @param ed
	 *            ���ᳯ¥
	 */
	public DateRange(String st, String ed) {
		this(st, ed, "yyyy-MM-dd");
	}

	/**
	 * ��¥���� ��ü ����. �⺻ ��¥ ���� (yyyy-MM-dd)���� ��¥ ��ȯ
	 * 
	 * @param st
	 *            ���۳�¥
	 * @param ed
	 *            ���ᳯ¥
	 * @param format
	 *            ��¥ ���� "yyyy, MM, dd, HH, mm, ss and more"
	 */
	public DateRange(String st, String ed, String format) {
		start = DateUtil.getDate(st, format);
		end = DateUtil.getDate(ed, format);
	}

	/**
	 * ��¥���� ��ü ����.
	 * 
	 * @param st
	 *            ������
	 * @param ed
	 *            ������
	 */
	public DateRange(Date st, Date ed) {
		start = st;
		end = ed;
	}

	/**
	 * @return ���ᳯ¥�� �����մϴ�.
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * @return ���۳�¥�� �����մϴ�.
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * @return ���ᳯ¥�� "yyyy-MM-dd" ���·� �����մϴ�.
	 */
	public String getEndString() {
		return DateUtil.getFormatString(end);
	}

	/**
	 * @return ���۳�¥�� "yyyy-MM-dd" ���·� �����մϴ�.
	 */
	public String getStartString() {
		return DateUtil.getFormatString(start);
	}

	/**
	 * @param format
	 *            ��¥ ���� "yyyy, MM, dd, HH, mm, ss and more"
	 * @return ���ᳯ¥�� ���� ���·� �����մϴ�.
	 */
	public String getEndString(String format) {
		return DateUtil.getFormatString(end, format);
	}

	/**
	 * @param format
	 *            ��¥ ���� "yyyy, MM, dd, HH, mm, ss and more"
	 * @return ���ᳯ¥�� ���� ���·� �����մϴ�.
	 */
	public String getStartString(String format) {
		return DateUtil.getFormatString(start, format);
	}

}