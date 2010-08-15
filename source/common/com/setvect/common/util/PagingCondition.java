package com.setvect.common.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ���� �˻� ������ ǥ����
 */
public class PagingCondition implements Serializable {
	/** */
	private static final long serialVersionUID = 4784964094940288037L;

	/** ���������� ǥ�� �׸� �� */
	private static final int DEFAULT_PAGE_VIEW_COUNT = 15;

	/** ������ ���� */
	private static final int DEFAULT_PAGE_UNIT = 10;

	/** ���� ������ */
	private int currentPage;

	/** ���������� ǥ�� �׸� �� */
	private int pagePerItemCount = DEFAULT_PAGE_VIEW_COUNT;

	/** ������ ���� ���� */
	private int pageUnit = DEFAULT_PAGE_UNIT;

	/** �˻� ���� Ű:�� ���·� ���� */
	private HashMap<Object, Object> searchData = new HashMap<Object, Object>();

	/**
	 * @param currentPage
	 *            �� ������. 1���� ����
	 */
	public PagingCondition(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @param currentPage
	 *            �� ������. 1���� ����
	 * @param pagePerItemCount
	 *            ���������� ǥ�� �׸� ��
	 */
	public PagingCondition(int currentPage, int pagePerItemCount) {
		this.currentPage = currentPage;
		this.pagePerItemCount = pagePerItemCount;
	}

	/**
	 * @param currentPage
	 *            �� ������. 1���� ����
	 * @param pagePerItemCount
	 *            ���������� ǥ�� �׸� ��
	 * @param pageUnit
	 *            ������ ���� ũ��
	 * 
	 */
	public PagingCondition(int currentPage, int pagePerItemCount, int pageUnit) {
		this.currentPage = currentPage;
		this.pagePerItemCount = pagePerItemCount;
		this.pageUnit = pageUnit;
	}

	/**
	 * @return �� ������. 1���� ����
	 */
	public int getCurrentPage() {
		return this.currentPage;
	}

	/**
	 * @return ���������� ǥ�� �׸� ��
	 */
	public int getPagePerItemCount() {
		return this.pagePerItemCount;
	}

	/**
	 * ������ �������� ������ ������ ����<br>
	 * ��) �Ʒ��� ���� ��� ������ ũ��� 3<br>
	 * [����] 3, 4, 5 [����]<br>
	 * 
	 * @return the pageUnit
	 */
	public int getPageUnit() {
		return pageUnit;
	}

	/**
	 * ���� ��� �� �������� 10���� �׸��� ǥ�� �ϰ� �ְ�, ���� 3��° �������� ���� �ȴٸ�<br>
	 * 10 * 3 = 30<br>
	 * �� 30��° ���� �׸��� �����;� ��
	 * 
	 * @return ���� �;� �� �׸� ���� ��ȣ(0���� ����)
	 */
	public int getStartNumber() {
		return (getCurrentPage() - 1) * getPagePerItemCount();
	}

	/**
	 * �˻� ���� ���
	 * 
	 * @param key
	 *            �˻� key
	 * @param cond
	 *            �˻� value
	 */
	public void addCondition(Object key, Object cond) {
		this.searchData.put(key, cond);
	}

	/**
	 * @param key
	 *            �˻� key
	 * @return key�� ���� ��
	 */
	public Object getCondition(Object key) {
		return this.searchData.get(key);
	}

	/**
	 * @param key
	 *            �˻� key
	 * @return key�� ���� ���� toString()
	 */
	public String getConditionString(Object key) {
		Object o = this.searchData.get(key);
		if (o == null) {
			return null;
		}
		return o.toString();
	}

	/**
	 * @return �˻� �� ����
	 */
	public HashMap<Object, Object> getCondition() {
		return this.searchData;
	}

	/**
	 * ������ ���� �� �˻� ���� URL �Ķ����<br>
	 * �ĸ����� ���ڵ� Ÿ��: utf-8
	 * 
	 * @param keyMap
	 *            �׸� ���� �Ķ���� �̸�
	 * @return �Ķ���� ���� ����
	 * @throws UnsupportedEncodingException
	 */
	public Map<String, Object> getUrlParam(Map<Object, String> keyMap) throws UnsupportedEncodingException {
		Map<String, Object> param = new HashMap<String, Object>();

		// "currentPage" �̸��� ����. Html �󿡼��� �� ���� ����ؾ� ��
		param.put("currentPage", getCurrentPage());
		Set<Object> keys = keyMap.keySet();
		for (Object key : keys) {
			String paramName = keyMap.get(key);
			String c = getConditionString(key);
			if (c == null) {
				continue;
			}
			param.put(paramName, c);
		}
		return param;
	}

}