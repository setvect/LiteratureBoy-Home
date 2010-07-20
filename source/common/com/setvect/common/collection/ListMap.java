package com.setvect.common.collection;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 2���� ArrayList ������ ����, �ش� �׸��� Key:Value������ ���εǾ� ����<br>
 * �ʱ� �ο쿡 �� �÷��� ���� ����<br>
 * �⺻������ ���̺�� ���� 2���� �ڷ� ����<br>
 * 
 * @see ArrayKeySet
 * @author <a href="mailto:setvect@setvect.com">����ȣ </a>
 * @version $Id$
 */
@SuppressWarnings("serial")
public class ListMap extends ArrayList<HashMap<String, String>> {
	/** Ű ���� */
	final String[] keys;

	/** ���� Ŀ�� ��ġ ���� */
	private int currentPos = -1;

	/** �ϳ��� �ο� */
	private HashMap<String, String> record;

	/**
	 * @param keys
	 */
	public ListMap(String keys[]) {
		this.keys = keys;
	}

	/**
	 * ���ڵ忡 �÷� �׸��� �ִ´�.<br>
	 * �ʵ� ���� ���� �ߺ� �˻� ���� ����(����ϴ� �ʿ��� �ؾߵ�)
	 * 
	 * @param fieldID
	 *            �ʵ� ���̵�
	 * @param value
	 *            ��
	 */
	public void put(String fieldID, String value) {
		for (String key : keys) {
			if (key.equals(fieldID)) {
				record.put(fieldID, value);
				return;
			}
		}
		throw new RuntimeException("�ش� �ʵ�� ���� ���� �ʾҽ��ϴ�. ���� �ʵ�:" + fieldID);
	}

	/**
	 * 
	 * /** ���ο� ���ڵ带 ����
	 */
	public void newRecord() {
		record = new HashMap<String, String>();
	}

	/**
	 * ���� ���ڵ� ����
	 */
	public void saveRecord() {
		add(record);
	}

	/**
	 * @return Ű��
	 */
	public String[] getKeys() {
		return keys;
	}

	/**
	 * @param key
	 *            �ʵ� �̸�
	 * @return �÷��� ���� ��
	 */
	public String getValue(String key) {
		HashMap<String, String> r = (HashMap<String, String>) get(currentPos);
		return r.get(key);
	}

	/**
	 * @return ���� ���ڵ� �̵� ���� ���� true/false
	 */
	public boolean next() {
		if (size() <= currentPos + 1) {
			return false;
		}
		currentPos++;
		return true;
	}
}
