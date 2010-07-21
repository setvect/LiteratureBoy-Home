/*
 * setvect 
 */
package com.setvect.common.spring;

import java.io.File;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 단일 spring 설정일 경우 <br>
 * 하나의 spring설정을 사용을 할 경우를 지원 하고, 2개 이상의 설정을 제공 하지 않음.
 * 
 * @version $Id$
 */
public class SpringBeanFactory {
	private static BeanFactory generalFactory;

	/** 초기화 여부 */
	private static boolean init = false;

	/**
	 * @param configFile
	 *            설정 파일(xml)
	 */
	public synchronized static void init(File configFile) {
		if (init) {
			throw new RuntimeException("aready initialized");
		}
		generalFactory = (BeanFactory) new FileSystemXmlApplicationContext("file:" + configFile.getAbsolutePath());
		init = true;
	}

	/**
	 * @return 기본 Bean Factory
	 */
	public static BeanFactory getGeneralFactory() {
		return generalFactory;
	}
}