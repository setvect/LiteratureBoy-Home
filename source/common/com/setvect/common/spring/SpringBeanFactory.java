/*
 * setvect 
 */
package com.setvect.common.spring;

import java.io.File;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * ���� spring ������ ��� <br>
 * �ϳ��� spring������ ����� �� ��츦 ���� �ϰ�, 2�� �̻��� ������ ���� ���� ����.
 * 
 * @version $Id$
 */
public class SpringBeanFactory {
	private static BeanFactory generalFactory;

	/** �ʱ�ȭ ���� */
	private static boolean init = false;

	/**
	 * @param configFile
	 *            ���� ����(xml)
	 */
	public synchronized static void init(File configFile) {
		if (init) {
			throw new RuntimeException("aready initialized");
		}
		generalFactory = (BeanFactory) new FileSystemXmlApplicationContext("file:" + configFile.getAbsolutePath());
		init = true;
	}

	/**
	 * @return �⺻ Bean Factory
	 */
	public static BeanFactory getGeneralFactory() {
		return generalFactory;
	}
}