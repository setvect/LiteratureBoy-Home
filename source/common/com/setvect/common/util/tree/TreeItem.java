package com.setvect.common.util.tree;

/**
 * tree구조를 나타내기 위한 기본 인터페이스 항목 정보<br>
 * 트리 구조를 표현하기 위해서는 본 인터페이스를 구현 해야 됨 .
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