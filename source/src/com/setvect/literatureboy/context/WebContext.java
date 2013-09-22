package com.setvect.literatureboy.context;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.setvect.literatureboy.web.SessionCheckInterceptor;
import com.setvect.literatureboy.web.board.BoardTrackbackController;

@Configuration
@ImportResource({ "classpath:/config/applicationWeb.xml" })
@ComponentScan(basePackages = "com.setvect.literatureboy.web", useDefaultFilters = false, includeFilters = @Filter(type = ANNOTATION, value = Controller.class), excludeFilters = @Filter(type = ANNOTATION, value = Service.class))
public class WebContext {
	@Autowired
	private SessionCheckInterceptor sessionCheckerInterceptor;

	@Autowired
	private BoardTrackbackController boardTrackbackController;

	@Bean
	public SessionCheckInterceptor loginCheckInterceptor() {
		return new SessionCheckInterceptor();
	}

	@Bean
	public DefaultAnnotationHandlerMapping defaultAnnotationHandlerMapping() {
		DefaultAnnotationHandlerMapping bean = new DefaultAnnotationHandlerMapping();
		bean.setInterceptors(new Object[] { sessionCheckerInterceptor });
		return bean;
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/");
		bean.setSuffix(".jsp");
		return bean;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver bean = new CommonsMultipartResolver();
		bean.setMaxUploadSize(10000000);
		return bean;
	}

	@Bean
	public BoardTrackbackController trackbackController() {
		return new BoardTrackbackController();
	}

	@Bean
	public SimpleUrlHandlerMapping handlerMapping() {
		SimpleUrlHandlerMapping bean = new SimpleUrlHandlerMapping();
		bean.setOrder(1);
		bean.setAlwaysUseFullPath(true);
		Properties mappings = new Properties();
		mappings.put("/servlet/tb/*", boardTrackbackController);
		bean.setMappings(mappings);
		return bean;
	}

	@Bean
	public SimpleUrlHandlerMapping dwrUrlHandlerMapping() {
		SimpleUrlHandlerMapping bean = new SimpleUrlHandlerMapping();
		Properties mappings = new Properties();
		mappings.setProperty("/engine.js", "dwrController");
		mappings.setProperty("/util.js", "dwrController");
		mappings.setProperty("/interface/**", "dwrController");
		mappings.setProperty("/call/**", "dwrController");
		mappings.setProperty("/*", "dwrController");
		bean.setMappings(mappings);

		return bean;
	}
}