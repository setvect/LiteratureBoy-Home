package com.setvect.literatureboy.boot;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.setvect.common.log.LogPrinter;
import com.setvect.literatureboy.config.EnvirmentProperty;
import com.setvect.literatureboy.db.DBInitializer;
import com.setvect.literatureboy.service.user.UserService;

/**
 * WAS가 실행되면 어플리케이션에 기본적인 설정값, 로그설정등을 해준다. <br>
 * $Id$
 */
@SuppressWarnings("serial")
public class EnvirmentInit extends HttpServlet {
	/** 초기화 여부 */
	private static boolean initialize = false;
	private static ClassPathXmlApplicationContext springContext;

	public EnvirmentInit() {
	}

	public void init() throws ServletException {
		super.init();

		ServletConfig config = getServletConfig();

		ServletContext sc = config.getServletContext();
		/*
		 * OS입장에서 웹루트 디렉토리
		 */
		File webBase = new File(sc.getRealPath("/"));
		String conf = config.getInitParameter("config_file");
		bootUp(webBase, conf);
	}

	/**
	 * @return the springContext
	 */
	public static ClassPathXmlApplicationContext getSpringContext() {
		return springContext;
	}

	/**
	 * config propertity, log4j, spring, hibernate 설정 초기화
	 * 
	 * @param webBase
	 *            웹루트 경로
	 * @param configPath
	 *            웹루트를 기준으로한 config 파일 path 경로
	 */
	public static void bootUp(File webBase, String configPath) {

		if (initialize) {
			return;
			// throw new IllegalStateException("aready initialized!");
		}

		File configFile = new File(webBase, configPath);
		EnvirmentProperty.init(configFile);

		File logFilePath = new File(webBase, EnvirmentProperty.getString("com.setvect.literatureboy.log.config"));
		LogPrinter.init(logFilePath);
		LogPrinter.info("Log Manager Initialized");

		springContext = new ClassPathXmlApplicationContext(new String[] { "classpath:spring/applicationContext.xml" },
				false);
		springContext.refresh();

		LogPrinter.info("Spring Initialized");

		// DB init
		// H2 데이터 베이스 파일 생성 경로 지정. Spring Initialized 전에 해야됨
		if (System.getProperty("h2.baseDir") == null) {
			System.setProperty("h2.baseDir", EnvirmentProperty.getString("com.setvect.literatureboy.db.path"));
		}

		DBInitializer conn = (DBInitializer) springContext.getBean("db.initializer");
		conn.init();
		LogPrinter.info("DB Initialized");

		conn.makeTable();
		LogPrinter.info("DB Table Initialized completed");
		initialize = true;

		UserService user = (UserService) springContext.getBean("UserService");
		user.initAuth();
	}

	public void destroy() {
		super.destroy();
	}
}