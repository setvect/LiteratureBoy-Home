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
 * ���̹�����Ʈ ���� ��ƿ<br>
 * �⺻������ ���� ���� �� Ʈ���輱 ó���� �����ϰ�<br>
 * 
 * ����ϱ� ���� init()�޼ҵ带 �̿��ؼ� �ʱ�ȭ �ؾߵ�
 * 
 * @version $Id$
 */
public class HibernateUtil {

	private static SessionFactory sf;

	/**
	 * ���̹�����Ʈ ����<br>
	 * 
	 * @param configFile
	 *            ���� ���� xml ����
	 * @param annotation
	 *            ������̼��� �̿��Ͽ� entity�� class�� ���� �ߴٸ� true, �ƴϸ� false
	 */
	public static void init(File configFile, boolean annotation) {
		try {
			// ���̹�����Ʈ ���� ����
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
	 * @return ���̹� ����Ʈ �������丮
	 */
	public static SessionFactory getSessionFactory() {
		return sf;
	}

	/**
	 * @return ���� ��� ����
	 */
	public static Session getCurrentSession() {
		return sf.getCurrentSession();
	}

	/**
	 * ���� ��� ���� �ݱ�
	 */
	public static void closeSession() {
		sf.getCurrentSession().close();
	}

	/**
	 * ���� ���ǿ� Ʈ����� ����
	 * 
	 * @return Ʈ������
	 */
	public static Transaction beginTransaction() {
		Transaction t = sf.getCurrentSession().beginTransaction();
		Connection c = sf.getCurrentSession().connection();
		try {
			// ��Ʈ�� �Ͻ� ����, DBMS ����� � ������ ���� Ŀ�ؼ��� �Ҿ� ���� ��� �ٽ� �� Ŀ�ؼ�
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
	 * �� ������ Ʈ������ Ŀ��
	 */
	public static void commitTransaction() {
		sf.getCurrentSession().getTransaction().commit();
	}

	/**
	 * �� Ʈ������ �ѹ�
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
