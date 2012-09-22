package com.setvect.common.log;

import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * log4j를 사용할때 일일이 인스턴스 안만들고 static하게 사용하기 애해서 이러첨 별도의 클래스를 만들었다.
 * 
 * @version $Id$
 */
public class LogPrinter {

	/** 로그 기록 */
	public static Logger out;

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

	/**
	 * 로그 설정을 초기화 함
	 * 
	 * @param logFilePath
	 *            로그 파일 설정 경로
	 */
	public static void init(URL logFilePath) {
		if (init) {
			throw new RuntimeException("configuration already setting");
		}
		out = Logger.getLogger(LogPrinter.class);

		DOMConfigurator.configure(logFilePath);
	}

}