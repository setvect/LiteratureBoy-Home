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
 * WAS�� ����Ǹ� ���ø����̼ǿ� �⺻���� ������, �α׼������� ���ش�. <br>
 * $Id$
 */
@SuppressWarnings("serial")
public class EnvirmentInit extends HttpServlet {
	private static final String CONFIG_SPRING = "classpath:config/applicationContext.xml";
	private static final String CONFIG_LOG4J_XML = "/config/log4j.xml";
	private static final String CONFIG_CONFIG_PROPERTIES = "/config/config.properties";

	/** �ʱ�ȭ ���� */
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
	 * config propertity, log4j, spring, hibernate ���� �ʱ�ȭ
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

		// Jetty ��뿡�� �߻��Ǵ� ���� �ذ�
		loadForSpringJarFile();

		springContext = new ClassPathXmlApplicationContext(new String[] { CONFIG_SPRING }, false);
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

	/**
	 * ���������� ���α׷� ���ۿ� �ƹ��� ������ ����<br>
	 * �ٸ� spring �ʱ�ȭ���� ���� jar ������ �ε��ϱ� ���� �������� ���<br>
	 * ���õ� ��Ű�� ������ �ִ� jar ���� �ε��� ���� �ʰ� xml parsing �߱� �����̴�. jetty�� jar ���Ͼȿ� �ִ�
	 * Ŭ������ �ѹ��̶� �ε� �ؾ��� jar�� ���� �� �� �ֳ� ����. (����) �׷��� ���������� jar������ �ε� �ϱ����� �Ʒ��� ����
	 * �ҽ��� �־���<br>
	 * �ش� �޼ҵ带 ������� ������ jetty������ �Ʒ��� ���� ������ ��Ÿ��<br>
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