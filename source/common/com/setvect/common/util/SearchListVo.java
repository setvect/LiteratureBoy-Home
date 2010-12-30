package com.setvect.common.util;

import java.io.Serializable;

/**
 * ���� �˻� ������ ǥ����
 */
public class SearchListVo implements Serializable {
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

	/**
	 * @param currentPage
	 *            �� ������. 1���� ����
	 */
	public SearchListVo(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return �� ������. 1���� ����
	 */
	public int getCurrentPage() {
		return this.currentPage;
	}

	/**
	 * @param currentPage
	 *            �� ������. 1���� ����
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return ���������� ǥ�� �׸� ��
	 */
	public int getPagePerItemCount() {
		return this.pagePerItemCount;
	}

	/**
	 * @param pagePerItemCount
	 *            ���������� ǥ�� �׸� ��
	 */
	public void setPagePerItemCount(int pagePerItemCount) {
		this.pagePerItemCount = pagePerItemCount;
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
	 * @param pageUnit
	 *            ������ ���� ����
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
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

}