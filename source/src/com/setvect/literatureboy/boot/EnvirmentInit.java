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
 * WAS�� ����Ǹ� ���ø����̼ǿ� �⺻���� ������, �α׼������� ���ش�. <br>
 * $Id$
 */
@SuppressWarnings("serial")
public class EnvirmentInit extends HttpServlet {
	/** �ʱ�ȭ ���� */
	private static boolean initialize = false;
	private static ClassPathXmlApplicationContext springContext;

	public EnvirmentInit() {
	}

	public void init() throws ServletException {
		super.init();

		ServletConfig config = getServletConfig();

		ServletContext sc = config.getServletContext();
		/*
		 * OS���忡�� ����Ʈ ���丮
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
	 * config propertity, log4j, spring, hibernate ���� �ʱ�ȭ
	 * 
	 * @param webBase
	 *            ����Ʈ ���
	 * @param configPath
	 *            ����Ʈ�� ���������� config ���� path ���
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
		LogPrinter.out.info("Log Manager Initialized");

		springContext = new ClassPathXmlApplicationContext(new String[] { "classpath:spring/applicationContext.xml" },
				false);
		springContext.refresh();

		LogPrinter.out.info("Spring Initialized");

		// DB init
		// H2 ������ ���̽� ���� ���� ��� ����. Spring Initialized ���� �ؾߵ�
		if (System.getProperty("h2.baseDir") == null) {
			System.setProperty("h2.baseDir", EnvirmentProperty.getString("com.setvect.literatureboy.db.path"));
		}

		DBInitializer conn = (DBInitializer) springContext.getBean("db.initializer");
		conn.init();
		LogPrinter.out.info("DB Initialized");

		conn.makeTable();
		LogPrinter.out.info("DB Table Initialized completed");
		initialize = true;

		// ���� ���� ������ �о� �帲
		UserService user = (UserService) springContext.getBean("UserService");
		user.initAuth();
	}

	public void destroy() {
		super.destroy();
	}
}