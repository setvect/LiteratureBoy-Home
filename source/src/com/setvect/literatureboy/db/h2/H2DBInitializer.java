package com.setvect.literatureboy.db.h2;

import java.net.URL;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.setvect.common.db.TableCreateInfo;
import com.setvect.common.log.LogPrinter;
import com.setvect.literatureboy.db.DBInitializer;

/**
 * H2 DB �ʱ�ȭ �ϴ� �Ͱ� ����.
 */
public class H2DBInitializer extends DBInitializer {
	@Resource
	private SessionFactory sessionFactory;

	/** ����� ���̺��� �ǹ��ϴ� Ÿ�� �� */
	private static final String DEFAULT_TABLE_TYPE = "TABLE";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipms.sfj.db.DBInitializer#init(java.io.File)
	 */
	@Override
	public void init() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipms.sfj.db.DBInitializer#makeTable()
	 */
	public void makeTable() {

		URL script = DBInitializer.class.getResource("db-script.xml");
		List<TableCreateInfo> tableCreate = tableScript(script);

		for (TableCreateInfo t : tableCreate) {
			createTable(t);
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
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("SELECT table_name FROM INFORMATION_SCHEMA.TABLES where table_name = ? and table_type = ?");
		query.setParameter(0, tableInfo.getTableName());
		query.setParameter(1, DEFAULT_TABLE_TYPE);

		@SuppressWarnings("rawtypes")
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
