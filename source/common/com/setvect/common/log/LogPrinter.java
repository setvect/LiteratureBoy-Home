package com.setvect.common.log;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * log4j를 사용할때 일일이 인스턴스 안만들고 static하게 사용하기 애해서 이러첨 별도의 클래스를 만들었다.
 * 
 * @version $Id$
 */
public class LogPrinter {

	/** 로그 기록 */
	private static Logger out;

	/** 초기화 여부 */
	private static boolean init = false;

	/**
	 * 로그 설정을 초기화 함
	 * 
	 * @param logFilePath
	 *            로그 파일 설정 경로
	 */
	public synchronized static void init(File logFilePath) {
		if (init) {
			throw new RuntimeException("configuration already setting");
		}
		out = Logger.getLogger(LogPrinter.class);
		// Use a PropertyConfigurator to initialize from a property file.
		DOMConfigurator.configure(logFilePath.getPath());
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