package com.setvect.common.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ��¥, �ð����õ� Ŭ��
 * 
 */
public abstract class DateUtil {

	/**
	 * Don't let anyone instantiate this class
	 */
	private DateUtil() {
	}

	/**
	 * check date string validation with the default format "yyyy-MM-dd".
	 * 
	 * @param s
	 *            date string you want to check with default format
	 *            "yyyy-MM-dd".
	 */
	public static void check(String s) throws Exception {
		DateUtil.check(s, "yyyy-MM-dd");
	}

	/**
	 * check date string validation with an user defined format.
	 * 
	 * @param s
	 *            date string you want to check.
	 * @param format
	 *            string representation of the date format. For example,
	 *            "yyyy-MM-dd".
	 */
	public static void check(String s, String format) throws java.text.ParseException {
		if (s == null)
			throw new NullPointerException("date string to check is null");
		if (format == null)
			throw new NullPointerException("format string to check date is null");

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
		java.util.Date date = null;
		try {
			date = formatter.parse(s);
		} catch (java.text.ParseException e) {
			throw new java.text.ParseException(e.getMessage() + " with format \"" + format + "\"", e.getErrorOffset());
		}

		if (!formatter.format(date).equals(s))
			throw new java.text.ParseException("Out of bound date:\"" + s + "\" with format \"" + format + "\"", 0);
	}

	/**
	 * ��¥ ������ �˻���
	 * 
	 * @param s
	 *            ��¥ ���ڿ�
	 * @param format
	 *            �˻� ���� ���� <br>
	 *            ��: "yyyy-MM-dd"
	 */
	public static boolean isDatePatten(String s, String format) {
		if (s == null || format == null) {
			return false;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = formatter.parse(s);
		} catch (java.text.ParseException e) {
			return false;
		}

		if (!formatter.format(date).equals(s)) {
			return false;
		}
		return true;
	}

	/**
	 * @return DD ������ ��¥
	 */
	public static int getDay() {
		return getNumberByPattern("dd");
	}

	/**
	 * For example, String time = DateTime.getFormatString("yyyy-MM-dd
	 * HH:mm:ss");
	 * 
	 * @param pattern
	 *            "yyyy, MM, dd, HH, mm, ss and more"
	 * @return formatted string representation of current day and time with your
	 *         pattern.
	 */
	public static String getFormatString(String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String dateString = formatter.format(new java.util.Date());
		return dateString;
	}

	/**
	 * ��¥�� ���ϴ� �������� ����
	 * 
	 * @param dd
	 *            ��¥��
	 * @param pattern
	 *            "yyyy, MM, dd, HH, mm, ss and more"
	 * 
	 * @return ��ȯ�� ��¥ ��Ʈ��
	 */
	public static String getFormatString(Date dd, String pattern) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern);
		String dateString = formatter.format(dd);
		return dateString;
	}

	/**
	 * ��¥�� �⺻ ���� (yyyy-MM-dd)�� ����
	 * 
	 * @param dd
	 *            ��¥��
	 * @return ��ȯ�� ��¥ ��Ʈ��
	 */
	public static String getFormatString(Date dd) {
		return getFormatString(dd, "yyyy-MM-dd");
	}

	/**
	 * ��¥�� �⺻ ���� (yyyy-MM-dd HH:mm:ss)�� ����
	 * 
	 * @param dd
	 *            ��¥��
	 * @return ��ȯ�� ��¥ ��Ʈ��
	 */
	public static String getFormatStringDateTime(Date dd) {
		return getFormatString(dd, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @return ���� ��(Month)
	 */

	public static int getMonth() {
		return getNumberByPattern("MM");
	}

	/**
	 * ���� �ð��� ������ �������� ��ȯ <br>
	 * For example, String time = DateTime.getFormatString("yyyy-MM-dd
	 * HH:mm:ss");
	 * 
	 * @param pattern
	 *            "yyyy, MM, dd, HH, mm, ss and more"
	 * @return formatted string representation of current day and time with your
	 *         pattern.
	 */
	public static int getNumberByPattern(String pattern) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern);
		String dateString = formatter.format(new java.util.Date());
		return Integer.parseInt(dateString);
	}

	/**
	 * @return formatted string representation of current day with "yyyyMMdd".
	 */
	public static String getShortDateString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}

	/**
	 * @return formatted string representation of current time with "HHmmss".
	 */
	public static String getShortTimeString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("HHmmss", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}

	/**
	 * @return formatted string representation of current time with
	 *         "yyyy-MM-dd-HH:mm:ss".
	 */
	public static String getTimeStampString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd-HH:mm:ss:SSS",
				java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}

	/**
	 * @return formatted string representation of current time with "HH:mm:ss".
	 */
	public static String getTimeString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}

	/**
	 * @return ���� �⵵
	 */
	public static int getYear() {
		return getNumberByPattern("yyyy");
	}

	/**
	 * @return ���� �ð�
	 */
	public static int getHour() {
		return getNumberByPattern("HH");
	}

	/**
	 * @return ���� ��
	 */
	public static int getMinute() {
		return getNumberByPattern("mm");
	}

	/**
	 * @return ���� ��
	 */
	public static int getSecond() {
		return getNumberByPattern("ss");
	}

	/**
	 * @return ����ð��� YYYY-MM-DD���� ����
	 */
	public static String getSysDate() {
		// DateŬ�������� ������ ��¥ �ð��� ������
		java.util.Date datetime = new java.util.Date();
		java.text.SimpleDateFormat formattime = new SimpleDateFormat("yyyy-MM-dd");
		String texttime = formattime.format(datetime);

		return texttime;
	}

	/**
	 * ����ð��� �־��� ���� �������� ��ȯ
	 * 
	 * @param strFormat
	 *            �־��� ��������
	 * @return ��¥ ��Ʈ��
	 */
	public static String getSysDate(String strFormat) {
		// DateŬ�������� ������ ��¥ �ð��� ������
		java.util.Date datetime = new java.util.Date();
		java.text.SimpleDateFormat formattime = new SimpleDateFormat(strFormat);
		String texttime = formattime.format(datetime);

		return texttime;
	}

	/**
	 * @return �����ð��� 'yyyy-MM-dd HH:mm:ss' �̷� ������ ����
	 */
	public static String getSysDateTime() {
		// DateŬ�������� ������ ��¥ �ð��� ������
		java.util.Date datetime = new java.util.Date();
		java.text.SimpleDateFormat formattime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String texttime = formattime.format(datetime);

		return texttime;
	}

	/**
	 * @param t
	 *            Ÿ�� ������ ������ �ð�
	 * @param pattern
	 *            ǥ���� ����
	 * @return ��ȯ�� ���� ��Ʈ��
	 */
	public static String getFormatString(long t, String pattern) {
		// DateŬ�������� ������ ��¥ �ð��� ������
		java.util.Date datetime = new java.util.Date(t);
		java.text.SimpleDateFormat formattime = new SimpleDateFormat(pattern);
		String texttime = formattime.format(datetime);

		return texttime;
	}

	/**
	 * @param cal
	 *            �ð���
	 * @return YYYY-MM-DD���� ����
	 */
	public static String getFormatString(Calendar cal) {
		java.text.SimpleDateFormat formattime = new SimpleDateFormat("yyyy-MM-dd");
		return formattime.format(cal.getTime());
	}

	/**
	 * @param cal
	 *            �ð���
	 * @param pattern
	 *            ǥ���� ����
	 * @return ���ϴ�� ����
	 */
	public static String getFormatString(Calendar cal, String pattern) {
		java.text.SimpleDateFormat formattime = new SimpleDateFormat(pattern);
		return formattime.format(cal.getTime());
	}

	/**
	 * ��¥�� �⺻ ���� (yyyy-MM-dd HH:mm:ss)�� ����
	 * 
	 * @param dd
	 *            ��¥��
	 * @return ��ȯ�� ��¥ ��Ʈ��
	 */
	public static String getFormatStringDateTime(Calendar cal) {
		// TODO Auto-generated method stub
		return getFormatString(cal, "yyyy-MM-dd HH:mm:ssa");
	}

	/**
	 * �޼ҵ� ������ �����Ͻʽÿ�.
	 * 
	 * @param lDate
	 *            timestamp ����
	 * @return 'yyyy-MM-dd' �������� ��¥ ��Ʈ��
	 */
	public static String getDate(long lDate) {
		// DateŬ�������� ������ ��¥ �ð��� ������
		java.util.Date datetime = new java.util.Date(lDate);
		java.text.SimpleDateFormat formattime = new SimpleDateFormat("yyyy-MM-dd");
		String texttime = formattime.format(datetime);

		return texttime;
	}

	/**
	 * @param cal
	 * @return
	 */
	public static Date getDate(Calendar cal) {
		String d = getFormatString(cal);
		return getDate(d);
	}

	/**
	 * �����ϰ� ������ ���̿� cod�� ������ true �ƴϸ� false ��¥ ������ yyyy-MM-dd�� �Ѵ�.
	 * 
	 * @param cod
	 *            ��
	 * @param start
	 *            ������
	 * @param end
	 *            ������
	 * @return ��¥���̿� cond�� ������ true ������ false
	 */
	public static boolean isBetween(String cod, String start, String end) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();
		Calendar s = Calendar.getInstance();
		Calendar e = Calendar.getInstance();
		c.setTime(df.parse(cod));
		s.setTime(df.parse(start));
		e.setTime(df.parse(end));

		if ((c.after(s) || c.equals(s)) && (c.before(e) || c.equals(e))) {
			return true;
		}
		else
			return false;
	}

	/**
	 * �γ�¥ ���� ���ϱ�
	 * 
	 * @param startDate
	 *            ��¥ ����
	 * @param format
	 *            ��¥ ����
	 * @return �� ��¥�� ���� (Ÿ�ӽ�����)
	 * @throws Exception
	 */
	public static long dayDiff(String startDate, String format) throws Exception {
		return dayDiff(startDate, getFormatString("yyyy-MM-dd HH:mm:ss"), format);
	}

	/**
	 * �� ��¥ ������ ����
	 * 
	 * @param startDate
	 *            ���� ��¥
	 * @param endDate
	 *            ���� ��¥
	 * @param format
	 *            ��¥ ���� �⺻��: yyyy-MM-dd HH:mm:ss.SSS
	 * @return long ��¥ ���� �ʴ���
	 */
	public static long dayDiff(String startDate, String endDate, String format) throws Exception {
		if (format == null)
			format = "yyyy-MM-dd HH:mm:ss.SSS";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date sDate;
		Date eDate;
		long day2day = 0;
		try {
			sDate = sdf.parse(startDate);
			eDate = sdf.parse(endDate);
			day2day = (eDate.getTime() - sDate.getTime()) / 1000;
		} catch (Exception e) {
			throw new Exception("wrong format string");
		}

		return day2day;
	}

	/**
	 * @param startDate
	 *            ���۳�¥
	 * @param endDate
	 *            ���� ��¥
	 * @param format
	 *            ��¥ ���� (ex: yyyy-MM-dd HH:mm:ss.SSS)
	 * @return �� ��¥�� ���� �ϼ�
	 */
	public static int dayDiffDate(String startDate, String endDate, String format) {
		return dayDiffDate(getDate(startDate, format), getDate(endDate, format));
	}

	/**
	 * @param startDate
	 *            ���۳�¥
	 * @param endDate
	 *            ���� ����
	 * @return �� ��¥�� ���� �ϼ�
	 */
	public static int dayDiffDate(Date startDate, Date endDate) {
		long diff = (endDate.getTime() - startDate.getTime()) / 1000;
		return (int) (diff / (60 * 60 * 24));
	}

	/**
	 * ���ڿ��� ��¥�� Date Ÿ������ ��ȯ �⺻ ���� ���� "yyyy-MM-dd"
	 * 
	 * @param dateString
	 *            ���ڿ� ��¥
	 * @return Date
	 */
	public static Date getDate(String dateString) {
		return getDate(dateString, "yyyy-MM-dd");
	}

	/**
	 * ���ڿ��� ��¥�� Date Ÿ������ ��ȯ �⺻ ���� ���� "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param dateString
	 *            ���ڿ� ��¥
	 * @return Date
	 */
	public static Date getDateTime(String dateString) {
		return getDate(dateString, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * ���ڿ��� �ð��� Date Ÿ������ ��ȯ �⺻ ���� ���� "HH:mm:ss"
	 * 
	 * @param dateString
	 *            ���ڿ� �ð�
	 * @return Date
	 */
	public static Date getTime(String dateString) {
		return getDate(dateString, "HH:mm:ss");
	}

	/**
	 * ���ڿ��� ��¥�� Date Ÿ������ ��ȯ
	 * 
	 * @param dateString
	 *            ������ ��¥
	 * @param dateFormat
	 *            ������ ����
	 * @return Date
	 */
	public static Date getDate(String dateString, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date rtnValue = null;
		try {
			rtnValue = sdf.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return rtnValue;
	}

	/**
	 * ���ڿ��� ��¥�� Calendar Ÿ������ ��ȯ
	 * 
	 * @param dateString
	 *            ������ ��¥
	 * @param dateFormat
	 *            ������ ����
	 * @return Date
	 */
	public static Calendar getCalendar(String dateString, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date rtnValue = null;
		try {
			rtnValue = sdf.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		long l = rtnValue.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(l);
		return cal;
	}

	/**
	 * ���ڿ��ε� ��¥�� Ư�� ���˿� �°� ��Ʈ�� ���·� ��ȯ�� ����
	 * 
	 * @param dateString
	 *            ��¥�ε� ���ڿ�
	 * @param paramF
	 *            ���޵ɴ� ���ڿ� ����
	 * @param retF
	 *            ���ϵ� ����
	 * @return ���ϵ� ����
	 */
	public static String getDatePart(String dateString, String paramF, String retF) {
		Date d = getDate(dateString, paramF);
		return getFormatString(d, retF);
	}

	/**
	 * ���� �Ͽ��� diff�� ���� ���� ����
	 * 
	 * @param diff
	 *            ��¥ ���̰�(�ϴ���)
	 * @return
	 */
	public static String getDiffDay(int diff) {
		Calendar cal;
		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, diff);

		return getFormatString(cal);
	}

	/**
	 * ���� �Ͽ��� diff�� ���� ���� ����
	 * 
	 * @param diff
	 *            ��¥ ���̰�(�ϴ���)
	 * @return
	 */
	public static String getDiffMonth(int diff) {
		Calendar cal;
		cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, diff);

		return getFormatString(cal);
	}

	/**
	 * �ش� ��¥�� ���Ե� ���� ù���� �̵�.<br>
	 * ��) <br>
	 * 2011-01-01(�����), �� ����: �Ͽ��� = > 2010-12-26<br>
	 * 2012-02-08(������), �� ����: ������ = > 2012-02-06<br>
	 * 
	 * @param cal
	 *            ��¥
	 * @param fristWeek
	 *            ��(week)�� ���� ����. Calendar Ŭ���� ����� �̿�
	 * @return ���� ù���� �̵��� ��¥. �ð������ʹ� ������� ����.
	 */
	public static Calendar getFirstWeekDate(Calendar cal, int fristWeek) {
		Calendar rtnValue = (Calendar) cal.clone();
		rtnValue.setFirstDayOfWeek(fristWeek);
		rtnValue.set(Calendar.DAY_OF_WEEK, fristWeek);
		return rtnValue;
	}
}