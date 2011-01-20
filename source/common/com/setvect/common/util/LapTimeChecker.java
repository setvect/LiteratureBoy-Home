package com.setvect.common.util;

import java.io.PrintStream;

/**
 * �ð��� üũ �Ѵ�.<br>
 * ���α׷� �ڵ� ���� �ð��� üũ �Ҷ� ����Ѵ�.
 * 
 * @version $Id$
 */
public class LapTimeChecker {
	/** üũ ���� �ð� */
	private long startTime;

	/** ��� ��Ʈ�� */
	private PrintStream out;

	/** üũ ī��Ʈ */
	private int checkCount;

	/** �̸� */
	private String name;

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
		checkCount++;
		long cur = System.currentTimeMillis();
		out.println(name + ", " + NumberFormat.getNumberString("0000", checkCount) + ", "
				+ NumberFormat.getNumberString(",###", cur - startTime) + "ms, " + message);
	}
}
