package com.setvect.literatureboy.boot;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.setvect.common.log.LogPrinter;
import com.setvect.literatureboy.config.EnvirmentProperty;

/**
 * WAS�� ����Ǹ� ���ø����̼ǿ� �⺻���� ������, �α׼������� ���ش�. <br>
 * $Id$
 */
public class EnvirmentInit extends HttpServlet {
	public EnvirmentInit() {
	}

	public void init() throws ServletException {
		super.init();

		ServletConfig config = getServletConfig();

		ServletContext sc = config.getServletContext();
		/*
		 * OS���忡�� ����Ʈ ���丮
		 */
		String webBase = sc.getRealPath("/");
		String conf = config.getInitParameter("config_file");
		File configFile = new File(webBase, conf);
		EnvirmentProperty.init(configFile);

		File logFilePath = new File(webBase, EnvirmentProperty.getString("com.setvect.literatureboy.log.config"));
		LogPrinter.init(logFilePath);

	}

	public void destroy() {
		super.destroy();
	}
}