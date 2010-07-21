package com.setvect.common.db;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.setvect.common.log.LogPrinter;

/**
 * 하이버네이트 관련 유틸<br>
 * 기본적으로 세션 생성 및 트랜잭선 처리를 간단하게<br>
 * 
 * 사용하기 전에 init()메소드를 이용해서 초기화 해야됨
 * 
 * @version $Id$
 */
public class HibernateUtil {

	private static SessionFactory sf;

	/**
	 * 하이버네이트 설정<br>
	 * 
	 * @param configFile
	 *            설정 정보 xml 파일
	 * @param annotation
	 *            어노테이션을 이용하여 entity와 class를 매핑 했다면 true, 아니면 false
	 */
	public static void init(File configFile, boolean annotation) {
		try {
			// 하이버네이트 새션 생성
			if (annotation) {
				sf = new AnnotationConfiguration().configure(configFile).buildSessionFactory();
			} else {
				sf = new Configuration().configure(configFile).buildSessionFactory();
			}

		} catch (Throwable ex) {
			LogPrinter.fatal("fail to initialize Hibernate SessionFactory", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * @return 하이버 네이트 세션팩토리
	 */
	public static SessionFactory getSessionFactory() {
		return sf;
	}

	/**
	 * @return 현재 사용 세션
	 */
	public static Session getCurrentSession() {
		return sf.getCurrentSession();
	}

	/**
	 * 현재 사용 세션 닫기
	 */
	public static void closeSession() {
		sf.getCurrentSession().close();
	}

	/**
	 * 현재 세션에 트랜잭션 시작
	 * 
	 * @return 트랜젝션
	 */
	public static Transaction beginTransaction() {
		Transaction t = sf.getCurrentSession().beginTransaction();
		Connection c = sf.getCurrentSession().connection();
		try {
			// 네트웍 일시 단절, DBMS 재시작 등에 문제로 인해 커넥션을 잃어 버릴 경우 다시 재 커넥션
			if (c.isClosed()) {
				LogPrinter.warn("[" + c + "] connection close");
				sf.getCurrentSession().reconnect(c);
				LogPrinter.info("reconnection...");
				t = sf.getCurrentSession().beginTransaction();
			}
		} catch (SQLException e) {
			LogPrinter.error(e.getMessage(), e);
			throw new HibernateException(e);
		}
		return t;
	}

	/**
	 * 현 세션의 트랙젝션 커밋
	 */
	public static void commitTransaction() {
		sf.getCurrentSession().getTransaction().commit();
	}

	/**
	 * 현 트랜젝션 롤백
	 */
	public static void rollbackTransaction() {
		if (sf.getCurrentSession().isOpen()) {
			Transaction tx = sf.getCurrentSession().getTransaction();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}
}
