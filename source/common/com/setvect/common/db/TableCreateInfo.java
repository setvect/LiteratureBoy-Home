package com.setvect.common.db;

import java.util.ArrayList;
import java.util.List;

/**
 * 테이블 생성 정보
 * 
 * @version $Id$
 */
public class TableCreateInfo {
	/** 테이블 이름 */
	private String tableName;
	/** 생성 스크립트 */
	private String script;
	/** 테이블 생성 후 실행될 쿼리문(기본값, 제약조건 등) */
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
	 *            실행 query
	 */
	public void addQuery(String q) {
		query.add(q);
	}

	/**
	 * @return 쿼리 목록
	 */
	public String[] getQuerise() {
		return query.toArray(new String[0]);
	}

}
