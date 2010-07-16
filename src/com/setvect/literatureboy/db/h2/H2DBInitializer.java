package com.setvect.literatureboy.db.h2;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.setvect.common.db.HibernateUtil;
import com.setvect.common.db.TableCreateInfo;
import com.setvect.common.log.LogPrinter;
import com.setvect.literatureboy.config.EnvProperty;
import com.setvect.literatureboy.db.DBInitializer;

/**
 * H2 DB �ʱ�ȭ �ϴ� �Ͱ� ����.
 */

public class H2DBInitializer extends DBInitializer {

	/** ����� ���̺��� �ǹ��ϴ� Ÿ�� �� */
	private static final String DEFAULT_TABLE_TYPE = "TABLE";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipms.sfj.db.DBInitializer#init(java.io.File)
	 */
	@Override
	public void init() throws SQLException {
		if (System.getProperty("h2.baseDir") == null) {
			System.setProperty("h2.baseDir", EnvProperty.getString("com.setvect.literatureboy.db_path"));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipms.sfj.db.DBInitializer#makeTable()
	 */
	public void makeTable() {
		URL admScript = DBInitializer.class.getResource("adm-db-script.xml");
		makeAdminTable(admScript);
	}

	/**
	 * 
	 * @param url
	 *            Database sql script file path
	 */
	private void makeAdminTable(URL url) {
		List<TableCreateInfo> tableCreate = tableScript(url);
		// HibernateUtil.beginTransaction();

		HibernateUtil.beginTransaction();
		try {
			for (TableCreateInfo t : tableCreate) {
				createTable(t);
			}
			HibernateUtil.commitTransaction();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * ���̺�� ���̺� ���õ� ������ �� �������� ���<br>
	 * ���� ������ ���̺��� ��ϵǾ� ������ ��� ���� ����
	 * 
	 * @param tableInfo
	 *            ���̺� ���� ����
	 */
	private void createTable(TableCreateInfo tableInfo) {

		// Session session = HibernateUtil.getCurrentSession();
		Session session = HibernateUtil.getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("SELECT table_name FROM INFORMATION_SCHEMA.TABLES where table_name = ? and table_type = ?");
		query.setParameter(0, tableInfo.getTableName());
		query.setParameter(1, DEFAULT_TABLE_TYPE);
		List resultTable = query.list();

		if (resultTable.size() != 0) {
			// ���̺� ����
			return;
		}

		query = session.createSQLQuery(tableInfo.getScript());
		query.executeUpdate();

		// �⺻������ �� ��������
		String[] qs = tableInfo.getQuerise();
		for (String q : qs) {
			query = session.createSQLQuery(q);
			query.executeUpdate();
		}
		LogPrinter.info(tableInfo.getTableName() + " table created");
	}
}
