package com.setvect.common.util;

import java.io.Serializable;
import java.util.HashMap;

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
	private int currentPageNo = DEFAULT_PAGE_VIEW_COUNT;

	/** ���������� ǥ�� �׸� �� */
	private int pagePerItemCount;

	/** ������ ���� ���� */
	private int pageUnit = DEFAULT_PAGE_UNIT;

	/** �˻� ���� Ű:�� ���·� ���� */
	private HashMap<Object, Object> searchData = new HashMap<Object, Object>();

	/**
	 * @param currentPageNo
	 *            �� ������. 1���� ����
	 */
	public PagingCondition(int currentPageNo) {
		this.pagePerItemCount = currentPageNo;
	}

	/**
	 * @param currentPageNo
	 *            �� ������. 1���� ����
	 * @param pagePerItemCount
	 *            ���������� ǥ�� �׸� ��
	 */
	public PagingCondition(int currentPageNo, int pagePerItemCount) {
		this.currentPageNo = currentPageNo;
		this.pagePerItemCount = pagePerItemCount;
	}

	/**
	 * @param currentPageNo
	 *            �� ������. 1���� ����
	 * @param pagePerItemCount
	 *            ���������� ǥ�� �׸� ��
	 * @param pageUnit
	 *            ������ ���� ũ��
	 * 
	 */
	public PagingCondition(int currentPageNo, int pagePerItemCount, int pageUnit) {
		this.currentPageNo = currentPageNo;
		this.pagePerItemCount = pagePerItemCount;
		this.pageUnit = pageUnit;
	}

	/**
	 * @return �� ������. 1���� ����
	 */
	public int getCurrentPageNo() {
		return this.currentPageNo;
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
		return (getCurrentPageNo() - 1) * getPagePerItemCount();
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
}