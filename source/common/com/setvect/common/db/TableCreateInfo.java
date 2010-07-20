package com.setvect.common.db;

import java.util.ArrayList;
import java.util.List;

/**
 * ���̺� ���� ����
 * 
 * @version $Id$
 */
public class TableCreateInfo {
	/** ���̺� �̸� */
	private String tableName;
	/** ���� ��ũ��Ʈ */
	private String script;
	/** ���̺� ���� �� ����� ������(�⺻��, �������� ��) */
	private List<String> query = new ArrayList<String>();

	public TableCreateInfo(String tName) {
		tableName = tName;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @return the script
	 */
	public String getScript() {
		return script;
	}

	/**
	 * @param script
	 *            the script to set
	 */
	public void setScript(String script) {
		this.script = script;
	}

	/**
	 * @param q
	 *            ���� query
	 */
	public void addQuery(String q) {
		query.add(q);
	}

	/**
	 * @return ���� ���
	 */
	public String[] getQuerise() {
		return query.toArray(new String[0]);
	}

}
