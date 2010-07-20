package com.setvect.common.collection;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 2차원 ArrayList 구조를 갖고, 해당 항목은 Key:Value값으로 매핑되어 관리<br>
 * 초기 로우에 들어갈 컬럼은 먼저 선언<br>
 * 기본적으로 테이블과 같은 2차원 자료 구조<br>
 * 
 * @see ArrayKeySet
 * @author <a href="mailto:setvect@setvect.com">장정호 </a>
 * @version $Id$
 */
@SuppressWarnings("serial")
public class ListMap extends ArrayList<HashMap<String, String>> {
	/** 키 정보 */
	final String[] keys;

	/** 현재 커서 위치 정보 */
	private int currentPos = -1;

	/** 하나의 로우 */
	private HashMap<String, String> record;

	/**
	 * @param keys
	 */
	public ListMap(String keys[]) {
		this.keys = keys;
	}

	/**
	 * 레코드에 컬럼 항목을 넣는다.<br>
	 * 필드 값에 대한 중복 검사 하지 않음(사용하는 쪽에서 해야됨)
	 * 
	 * @param fieldID
	 *            필드 아이디
	 * @param value
	 *            값
	 */
	public void put(String fieldID, String value) {
		for (String key : keys) {
			if (key.equals(fieldID)) {
				record.put(fieldID, value);
				return;
			}
		}
		throw new RuntimeException("해당 필드는 정의 되지 않았습니다. 없는 필드:" + fieldID);
	}

	/**
	 * 
	 * /** 새로운 레코드를 생성
	 */
	public void newRecord() {
		record = new HashMap<String, String>();
	}

	/**
	 * 현재 레코드 저장
	 */
	public void saveRecord() {
		add(record);
	}

	/**
	 * @return 키값
	 */
	public String[] getKeys() {
		return keys;
	}

	/**
	 * @param key
	 *            필드 이름
	 * @return 컬럼에 대한 값
	 */
	public String getValue(String key) {
		HashMap<String, String> r = (HashMap<String, String>) get(currentPos);
		return r.get(key);
	}

	/**
	 * @return 다음 레코드 이동 가능 여부 true/false
	 */
	public boolean next() {
		if (size() <= currentPos + 1) {
			return false;
		}
		currentPos++;
		return true;
	}
}
