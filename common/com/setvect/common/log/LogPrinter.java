package com.setvect.common.log;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * log4j�� ����Ҷ� ������ �ν��Ͻ� �ȸ���� static�ϰ� ����ϱ� ���ؼ� �̷�÷ ������ Ŭ������ �������.
 * 
 * @version $Id$
 */
public class LogPrinter {

	/** �α� ��� */
	private static Logger out;

	/** �ʱ�ȭ ���� */
	private static boolean init = false;

	/**
	 * �α� ������ �ʱ�ȭ ��
	 * 
	 * @param logFilePath
	 *            �α� ���� ���� ���
	 */
	public synchronized static void init(String logFilePath) {
		if (init) {
			throw new RuntimeException("configuration already setting");
		}
		out = Logger.getLogger(LogPrinter.class);
		// Use a PropertyConfigurator to initialize from a property file.
		DOMConfigurator.configure(logFilePath);
	}

	// Delegate
	public static void debug(Object message, Throwable t) {
		out.debug(message, t);
	}

	public static void debug(Object message) {
		out.debug(message);
	}

	public static void error(Object message, Throwable t) {
		out.error(message, t);
	}

	public static void error(Object message) {
		out.error(message);
	}

	public static void fatal(Object message, Throwable t) {
		out.fatal(message, t);
	}

	public static void fatal(Object message) {
		out.fatal(message);
	}

	public static void info(Object message, Throwable t) {
		out.info(message, t);
	}

	public static void info(Object message) {
		out.info(message);
	}

	public static void trace(Object message, Throwable t) {
		out.trace(message, t);
	}

	public static void trace(Object message) {
		out.trace(message);
	}

	public static void warn(Object message, Throwable t) {
		out.warn(message, t);
	}

	public static void warn(Object message) {
		out.warn(message);
	}

}