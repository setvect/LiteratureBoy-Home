package com.setvect.literatureboy.boot;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.setvect.common.db.HibernateUtil;
import com.setvect.common.log.LogPrinter;
import com.setvect.common.spring.SpringBeanFactory;
import com.setvect.literatureboy.config.EnvirmentProperty;
import com.setvect.literatureboy.db.DBInitializer;

/**
 * WAS가 실행되면 어플리케이션에 기본적인 설정값, 로그설정등을 해준다. <br>
 * $Id$
 */
@SuppressWarnings("serial")
public class EnvirmentInit extends HttpServlet {
	public EnvirmentInit() {
	}

	public void init() throws ServletException {
		super.init();

		ServletConfig config = getServletConfig();

		ServletContext sc = config.getServletContext();
		/*
		 * OS입장에서 웹루트 디렉토리
		 */
		String webBase = sc.getRealPath("/");
		String conf = config.getInitParameter("config_file");
		File configFile = new File(webBase, conf);
		EnvirmentProperty.init(configFile);

		File logFilePath = new File(webBase, EnvirmentProperty.getString("com.setvect.literatureboy.log.config"));
		LogPrinter.init(logFilePath);
		LogPrinter.info("Log Manager Initialized");

		File springFilePath = new File(webBase, EnvirmentProperty.getString("com.setvect.literatureboy.spring.config"));
		SpringBeanFactory.init(springFilePath);
		LogPrinter.info("Spring Initialized");

		// DB init
		DBInitializer conn = (DBInitializer) SpringBeanFactory.getGeneralFactory().getBean("db.initializer");
		conn.init();
		LogPrinter.info("DB Initialized");

		File hibernatePath = new File(webBase,
				EnvirmentProperty.getString("com.setvect.literatureboy.db.hibernate.config"));
		HibernateUtil.init(hibernatePath, true);

		conn.makeTable();
		LogPrinter.info("DB Table Initialized completed");
	}

	public void destroy() {
		super.destroy();
	}
}