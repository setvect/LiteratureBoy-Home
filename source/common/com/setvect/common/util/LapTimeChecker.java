package com.setvect.common.util;

import java.io.PrintStream;

/**
 * 시간을 체크 한다.<br>
 * 프로그램 코드 진행 시간을 체크 할때 사용한다.
 * 
 * @version $Id$
 */
public class LapTimeChecker {
	/** 체크 시작 시간 */
	private long startTime;

	/** 출력 스트림 */
	private PrintStream out;

	/** 체크 카운트 */
	private int checkCount;

	/** 이름 */
	private String name;

	/**
	 * 기본 생성자<br>
	 * 메세지 출력은 System.out 에서 함
	 */
	public LapTimeChecker(String name) {
		this(System.out, name);
	}

	/**
	 * @param out
	 *            출력 스트림
	 */
	public LapTimeChecker(PrintStream out, String name) {
		this.name = name;
		this.out = out;
		startTime = System.currentTimeMillis();
		this.out.println(name + " start [" + startTime + "]");
	}

	/**
	 * 시간 체크
	 */
	public void check() {
		check("");
	}

	/**
	 * 시간 체크
	 * 
	 * @param message
	 *            출력 메시지
	 */
	public void check(String message) {
		checkCount++;
		long cur = System.currentTimeMillis();
		out.println(name + ", " + NumberFormat.getNumberString("0000", checkCount) + ", " + (cur - startTime) + "ms, "
				+ message);
	}
}
