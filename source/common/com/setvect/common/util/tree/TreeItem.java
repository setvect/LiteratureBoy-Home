package com.setvect.common.util.tree;

/**
 * tree������ ��Ÿ���� ���� �⺻ �������̽� �׸� ����<br>
 * Ʈ�� ������ ǥ���ϱ� ���ؼ��� �� �������̽��� ���� �ؾ� �� .
 */
public interface TreeItem<ID> extends Comparable<ID> {
	/**
	 * @return Returns the id.
	 */
	public ID getId();

	/**
	 * @return Returns the parent.
	 */
	public ID getParent();

	/**
	 * @param level
	 *            The level to set.
	 */
	public void setLevel(int level);
}