package com.setvect.common.util;

import java.util.Collection;

/**
 * Page���� �˻��� ��ü���� Ÿ���� ������
 */
public class GenericPage<T> extends Page {

	/** */
	private static final long serialVersionUID = -5941168763829250384L;

	/**
	 * @param objects
	 *            ����Ʈ
	 * @param currentPage
	 *            ���� ������
	 * @param totalCount
	 *            ��ü �׸� ��
	 * @param pageunit
	 *            ���� ������ ����
	 * @param pagesize
	 *            �������� ǥ�� ����
	 */
	public GenericPage(Collection<T> objects, int currentPage, int totalCount, int pageunit, int pagesize) {
		super(objects, currentPage, totalCount, pageunit, pagesize);
	}

	@SuppressWarnings("unchecked")
	public Collection<T> getList() {
		return super.getList();
	}

	/**
	 * @return ���� �������� ù��° �׸��� ���ȣ.<br>
	 *         1���� ����
	 */
	public int getRowNum() {
		return getPagesize() * (getCurrentPage() - 1) + 1;
	}

	/**
	 * @return ���� �������� ù��° �׸��� ���ȣ. ����<br>
	 *         ���� : total() ~ 1
	 */
	public int getRowNumDesc() {
		return getTotal() - (getPagesize() * (getCurrentPage() - 1));
	}

}