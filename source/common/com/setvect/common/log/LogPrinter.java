package com.setvect.common.log;

import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * log4j�� ����Ҷ� ������ �ν��Ͻ� �ȸ���� static�ϰ� ����ϱ� ���ؼ� �̷�÷ ������ Ŭ������ �������.
 * 
 * @version $Id$
 */
public class LogPrinter {

	/** �α� ��� */
	public static Logger out;

	/** �ʱ�ȭ ���� */
	private static boolean init = false;

	/**
	 * �α� ������ �ʱ�ȭ ��
	 * 
	 * @param logFilePath
	 *            �α� ���� ���� ���
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
	 * �α� ������ �ʱ�ȭ ��
	 * 
	 * @param logFilePath
	 *            �α� ���� ���� ���
	 */
	public static void init(URL logFilePath) {
		if (init) {
			throw new RuntimeException("configuration already setting");
		}
		out = Logger.getLogger(LogPrinter.class);

		DOMConfigurator.configure(logFilePath);
	}

}