package com.setvect.literatureboy.boot;

import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.aop.Advisor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.TransactionStatus;

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
	private static final String CONFIG_SPRING = "classpath:config/applicationContext.xml";
	private static final String CONFIG_LOG4J_XML = "/config/log4j.xml";
	private static final String CONFIG_CONFIG_PROPERTIES = "/config/config.properties";

	/** 초기화 여부 */
	private static boolean initialize = false;
	private static ClassPathXmlApplicationContext springContext;

	public EnvirmentInit() {
	}

	public void init() throws ServletException {
		super.init();
		bootUp();
	}

	/**
	 * @return the springContext
	 */
	public static ClassPathXmlApplicationContext getSpringContext() {
		return springContext;
	}

	/**
	 * config propertity, log4j, spring, hibernate 설정 초기화
	 */
	public static void bootUp() {

		if (initialize) {
			return;
			// throw new IllegalStateException("aready initialized!");
		}

		URL configUrl = EnvirmentInit.class.getResource(CONFIG_CONFIG_PROPERTIES);
		EnvirmentProperty.init(configUrl);

		URL log4j = EnvirmentInit.class.getResource(CONFIG_LOG4J_XML);
		LogPrinter.init(log4j);
		LogPrinter.out.info("Log Manager Initialized");

		// Jetty 사용에서 발생되는 오류 해결
		loadForSpringJarFile();

		springContext = new ClassPathXmlApplicationContext(new String[] { CONFIG_SPRING }, false);
		springContext.refresh();

		LogPrinter.out.info("Spring Initialized");
		
		// DB init
		// H2 데이터 베이스 파일 생성 경로 지정. Spring Initialized 전에 해야됨
		if (System.getProperty("h2.baseDir") == null) {
			System.setProperty("h2.baseDir", EnvirmentProperty.getString("com.setvect.literatureboy.db.path"));
		}

		DBInitializer conn = (DBInitializer) springContext.getBean("db.initializer");
		conn.init();
		LogPrinter.out.info("DB Initialized");

		conn.makeTable();
		LogPrinter.out.info("DB Table Initialized completed");
		initialize = true;

		// 권한 매핑 정보를 읽어 드림
		UserService user = (UserService) springContext.getBean("UserService");
		user.initAuth();
	}

	/**
	 * 직접적으로 프로그램 동작에 아무련 영향이 없음<br>
	 * 다만 spring 초기화전에 관련 jar 파일을 로딩하기 위한 목적으로 사용<br>
	 * 관련된 스키마 정보가 있는 jar 파일 로딩을 하지 않고 xml parsing 했기 때문이다. jetty는 jar 파일안에 있는
	 * 클래스를 한번이라도 로딩 해야지 jar을 접근 할 수 있나 보다. (추측) 그래서 강제적으로 jar파일을 로딩 하기위해 아래와 같이
	 * 소스를 넣었음<br>
	 * 해당 메소드를 사용하지 않으면 jetty에서는 아래와 같이 오류가 나타남<br>
	 * <br>
	 * org.springframework.beans.factory.parsing.BeanDefinitionParsingException:
	 * Configuration problem: Unable to locate Spring NamespaceHandler for XML
	 * schema namespace [http://www.springframework.org/schema/aop]
	 */
	private static void loadForSpringJarFile() {
		Class<Advisor> aop = Advisor.class;
		Class<TransactionStatus> tx = TransactionStatus.class;
	}

	public void destroy() {
		super.destroy();
	}
}