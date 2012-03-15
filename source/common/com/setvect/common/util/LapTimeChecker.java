package com.setvect.common.util;

import java.io.PrintStream;
import java.text.DecimalFormat;

/**
 * �ð��� üũ �Ѵ�.<br>
 * ���α׷� �ڵ� ���� �ð��� üũ �Ҷ� ����Ѵ�.
 * 
 * @version $Id$
 */
public class LapTimeChecker {

	/** 1�ʸ� �и�������� */
	private static final int SEC_FOR_MS = 1000;
	/** 1���� �и�������� */
	private static final int MIN_FOR_MS = SEC_FOR_MS * 60;
	/** 1�ð��� �и�������� */
	private static final int HOUR_FOR_MS = MIN_FOR_MS * 60;
	/** �Ϸ縦 �и�������� */
	private static final int DAY_FOR_MS = HOUR_FOR_MS * 24;

	private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("0000");
	private static final DecimalFormat NUMBER_COMMA_FORMAT = new DecimalFormat(",###");
	private static final DecimalFormat NUMBER_TIME_FORMAT = new DecimalFormat("00");

	/** üũ ���� �ð� */
	private long startTime;

	/** ��� ��Ʈ�� */
	private PrintStream out;

	/** üũ ī��Ʈ */
	private int checkCount;

	/** �̸� */
	private String name;

	/** ��ü ī��Ʈ */
	private int totalCount;

	/**
	 * �⺻ ������<br>
	 * �޼��� ����� System.out ���� ��
	 */
	public LapTimeChecker(String name) {
		this(System.out, name);
	}

	/**
	 * @param out
	 *            ��� ��Ʈ��
	 */
	public LapTimeChecker(PrintStream out, String name) {
		this.name = name;
		this.out = out;
		startTime = System.currentTimeMillis();
		this.out.println(name + " start [" + startTime + "]");
	}

	/**
	 * �ð� üũ
	 */
	public void check() {
		check("");
	}

	/**
	 * �ð� üũ
	 * 
	 * @param message
	 *            ��� �޽���
	 */
	public void check(String message) {
		out.print(getCheckMessage(message));
	}

	/**
	 * �ð� üũ ��� �޼����� ���ڿ��� ��ȯ
	 * 
	 * @param message
	 *            ��� �޽���
	 * @return
	 */
	public String getCheckMessage(String message) {
		checkCount++;
		long cur = System.currentTimeMillis();
		String msg = name + ", " + NUMBER_FORMAT.format(checkCount) + ", "
				+ NUMBER_COMMA_FORMAT.format(cur - startTime) + "ms, " + message;
		return msg;
	}

	/**
	 * @param processCount
	 *            ó���� �Ǽ�
	 * @return ���� ���� �ð��� ms ������ ����
	 */
	public long getRemainTime(int processCount) {
		long running = System.currentTimeMillis() - startTime;
		return (running * totalCount / processCount) - running;
	}

	/**
	 * @param processCount
	 *            ó���� �Ǽ�
	 * @param pattern
	 *            ��� ����, ��¥ ����
	 * @return ���� ���� �ð��� ������ ���˿� ���� ���ڿ��� ����
	 */
	public String getRemainTimeFormat(int processCount) {
		long time = getRemainTime(processCount);
		return getTimeToDayFormat(time);
	}

	/**
	 * @return ���� �ð��� ��¥/�ð� ��������
	 */
	public String getRunningTimeFormat() {
		long runningTime = System.currentTimeMillis() - startTime;
		return getTimeToDayFormat(runningTime);
	}

	private String getTimeToDayFormat(long time) {
		long day = time / DAY_FOR_MS;
		time = time % DAY_FOR_MS;

		long hour = time / HOUR_FOR_MS;
		time = time % HOUR_FOR_MS;

		long min = time / MIN_FOR_MS;
		time = time % MIN_FOR_MS;

		long sec = time / SEC_FOR_MS;

		StringBuffer rtnValue = new StringBuffer();
		if (day != 0) {
			rtnValue.append(NUMBER_TIME_FORMAT.format(day) + "day(s) ");
		}
		rtnValue.append(NUMBER_TIME_FORMAT.format(hour) + ":");
		rtnValue.append(NUMBER_TIME_FORMAT.format(min) + ":");
		rtnValue.append(NUMBER_TIME_FORMAT.format(sec));
		return rtnValue.toString();
	}

	/**
	 * ���� ���� �ð� ��� ��ü ī��Ʈ�� ����Ͽ� �� ���� �ð��� ����
	 * 
	 * @param totalCount
	 *            �� �۾� �Ǽ�
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}