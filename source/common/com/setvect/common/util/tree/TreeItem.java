package com.setvect.common.util.tree;

/**
 * tree������ ��Ÿ���� ���� �⺻ �������̽� �׸� ����<br>
 * Ʈ�� ������ ǥ���ϱ� ���ؼ��� �� �������̽��� ���� �ؾ� �� .
 */
public interface TreeItem extends Comparable<Object> {

	/**
	 * @return Returns the id.
	 */
	public Object getId();

	/**
	 * @return Returns the parent.
	 */
	public Object getParent();

	/**
	 * @param level
	 *            The level to set.
	 */
	public abstract void setLevel(int level);
}