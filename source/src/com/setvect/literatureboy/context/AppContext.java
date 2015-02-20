package com.setvect.literatureboy.context;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.setvect.literatureboy.vo.Comment;
import com.setvect.literatureboy.vo.Memo;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;
import com.setvect.literatureboy.vo.board.BoardTrackback;
import com.setvect.literatureboy.vo.ctmemo.CtmemoVo;
import com.setvect.literatureboy.vo.user.Auth;
import com.setvect.literatureboy.vo.user.AuthMap;
import com.setvect.literatureboy.vo.user.AuthMapKey;
import com.setvect.literatureboy.vo.user.User;

@Configuration
@ImportResource({ "classpath:/config/applicationContext.xml" })
@ComponentScan(basePackages = "com.setvect.literatureboy", useDefaultFilters = false, includeFilters = { @Filter(type = ANNOTATION, value = Service.class) }, excludeFilters = @Filter(type = ANNOTATION, value = Controller.class))
@PropertySource("classpath:/config/config.properties")
public class AppContext {
	@Autowired
	private Environment env;

	@Autowired
	private AnnotationSessionFactoryBean sessionFactory;

	/**
	 * 메소드 없으면 아래와 같은 에러남<br>
	 * Error creating bean with name 'appContext': Injection of autowired dependencies failed; nested exception is
	 * org.springframework.beans.factory.BeanCreationException: Could not autowire field: private
	 * org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean
	 * com.setvect.literatureboy.context.AppContext.sessionFactory; nested exception is
	 * org.springframework.beans.factory.BeanCurrentlyInCreationException: Error creating bean with name
	 * 'sessionFactory': Requested bean is currently in creation: Is there an unresolvable circular reference?
	 * 
	 * @return
	 */
	@Bean
	public PathMatcher antPathMater() {
		return new AntPathMatcher();
	}

	@Bean
	public AnnotationSessionFactoryBean sessionFactory() {
		AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();

		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.connection.driver_class",env.getProperty("com.setvect.literatureboy.db.driver"));
		hibernateProperties.setProperty("hibernate.connection.url", env.getProperty("com.setvect.literatureboy.db.url"));
		hibernateProperties.setProperty("hibernate.connection.username", env.getProperty("com.setvect.literatureboy.db.user"));
		hibernateProperties.setProperty("hibernate.connection.password", env.getProperty("com.setvect.literatureboy.db.passwd"));
		hibernateProperties.setProperty("hibernate.connection.poolsize", env.getProperty("com.setvect.literatureboy.db.poolsize"));
		hibernateProperties.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.EhCacheProvider");
		hibernateProperties.setProperty("hibernate.cache.use_query_cache", "true");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		hibernateProperties.setProperty("current_session_context_class", "thread");
		hibernateProperties.setProperty("hibernate.show_sql", "false");
		hibernateProperties.setProperty("hibernate.format_sql", "true");
		hibernateProperties.setProperty("hibernate.connection.shutdown", "true");
		sessionFactory.setHibernateProperties(hibernateProperties);

		@SuppressWarnings("rawtypes")
		Class[] vo = new Class[] { Comment.class, Memo.class, Board.class, BoardArticle.class, BoardAttachFile.class,
				BoardComment.class, BoardTrackback.class, Auth.class, AuthMap.class, AuthMapKey.class, User.class, CtmemoVo.class };

		sessionFactory.setAnnotatedClasses(vo);

		return sessionFactory;
	}

	@Bean
	public HibernateTemplate hibernateTemplate() {
		HibernateTemplate bean = new HibernateTemplate();
		bean.setSessionFactory(sessionFactory.getObject());
		return bean;
	}

	@Bean
	public HibernateTransactionManager hibernateTxManager() {
		HibernateTransactionManager bean = new HibernateTransactionManager();
		bean.setSessionFactory(sessionFactory.getObject());
		return bean;
	}

}