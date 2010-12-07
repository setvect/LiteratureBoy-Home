package com.setvect.common.util.tree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.TreeSet;

/**
 * ī�װ�, �޴�, ���� ������ ĳ���Ѵ�.
 */
public class TreeManager<OBJ extends TreeItem> {
	/** ī�װ� ���� */
	private Hashtable<Object, OBJ> category = new Hashtable<Object, OBJ>();

	/** �ֻ�� ī�װ� ��Ʈ ���̵� */
	private final Object categoryRootID;

	/** toArray() �޼ҵ带 ����ϱ� ���ؼ� �迭 ���� �ν��Ͻ� */
	private final OBJ[] _obj_empty_array;

	/**
	 * @param itmes
	 *            ���� ������
	 * @param rootID
	 *            ī�װ� root ���̵�
	 */
	@SuppressWarnings("unchecked")
	public TreeManager(OBJ[] items, Object rootID) {
		if (items.length == 0) {
			throw new RuntimeException("items size zero!!.");
		}
		for (OBJ i : items) {
			category.put(i.getId(), i);
		}
		_obj_empty_array = (OBJ[]) Array.newInstance(items[0].getClass(), 0);
		categoryRootID = rootID;
	}

	/**
	 * @return ��Ʈ ī�װ� ���̵�
	 */
	public Object getCategoryRootID() {
		return categoryRootID;
	}

	/**
	 * ������ ����Ǿ� �ִ� ��ü�� �����, ������ ��ü ������ ����
	 * 
	 * @param items
	 */
	public void setTreeItem(OBJ[] items) {
		category.clear();
		for (OBJ i : items) {
			category.put(i.getId(), i);
		}
	}

	/**
	 * @param categoryID
	 *            ī�װ� ���̵�
	 * @return ī�װ� ����
	 */
	public OBJ getCategory(Object categoryID) {
		return category.get(categoryID);
	}

	/**
	 * 
	 * @param parentID
	 *            �θ� ī�װ�
	 * @return �ڽ� ��ü ����
	 */
	public OBJ[] getChildCategory(Object parentID) {
		Enumeration<OBJ> e = category.elements();
		TreeSet<OBJ> n = new TreeSet<OBJ>();
		OBJ mc = null;
		while (e.hasMoreElements()) {
			mc = (OBJ) e.nextElement();
			if (mc.getParent().equals(parentID) && !mc.getId().equals(categoryRootID)) {
				n.add(mc);
			}
		}
		return (OBJ[]) n.toArray(_obj_empty_array);
	}

	/**
	 * ��Ʈ ī�װ��� ���� ���� ī�װ����� ��θ� ���Ѵ�.
	 * 
	 * @param formCategoryID
	 *            ��Ʈ�� ���� formCategoryIDī�װ� ���� ��θ� ����
	 * @return ī�װ� ���
	 */
	public OBJ[] getCategoryPath(Object formCategoryID) {
		ArrayList<OBJ> saveCategory = new ArrayList<OBJ>();
		path(formCategoryID, saveCategory);

		// ���ϰ��� �ϴ� ī�װ� -> Root ī�װ� ������ �Ǿ� �ִ�.
		OBJ[] descCategory = (OBJ[]) saveCategory.toArray(_obj_empty_array);

		// ���� ������ ����
		// Root ī�װ� ->���ϰ��� �ϴ� ī�װ� ������ ����
		// �̷��� �ؾ��� ���α׷����� ������ ���Ⱑ ���� �̶� ���Ѵ�.
		@SuppressWarnings("unchecked")
		OBJ[] ascCategory = (OBJ[]) Array.newInstance(saveCategory.get(0).getClass(), descCategory.length);

		int c = 0;
		for (int i = descCategory.length - 1; i >= 0; i--) {
			ascCategory[c++] = descCategory[i];
		}
		return ascCategory;
	}

	/**
	 * @param currentCategoryID
	 *            ���� ī�װ� ���̵�
	 * @param saveCategory
	 *            ��� ȣ���� ���� ī�װ� ������ ��� ����
	 */
	private void path(Object currentCategoryID, ArrayList<OBJ> saveCategory) {
		OBJ mc = getCategory(currentCategoryID);
		saveCategory.add(mc);

		// ��Ʈ ī�װ��� �ƴϸ� ���� ī�װ��� �� ã��
		if (!currentCategoryID.equals(categoryRootID)) {
			path(mc.getParent(), saveCategory);
		}
	}

	/**
	 * Ʈ�� ǥ�� ������� ī�װ��� ���� �ؼ� ������
	 * 
	 * @return ī�װ� ��ü ����(��Ʈ ī�װ� ���� )
	 */
	public OBJ[] getCategoryTree() {
		return getCategoryTree(categoryRootID);
	}

	/**
	 * Ʈ�� ǥ�� ������� ī�װ��� ���� �ؼ� ������
	 * 
	 * @param rootCategory
	 *            ���۵Ǵ� ī�װ�
	 * @return ���ĵ� ī�װ� �迭
	 */
	public OBJ[] getCategoryTree(Object rootCategory) {
		return getCategoryTree(rootCategory, false);
	}

	/**
	 * Ʈ�� ǥ�� ������� ī�װ��� ���� �ؼ� ������
	 * 
	 * @param rootCategory
	 *            ���۵Ǵ� ī�װ�
	 * @param rootSave
	 *            ��Ʈ ī�װ� ���� ����
	 * @return ���ĵ� ī�װ� �迭
	 */
	public OBJ[] getCategoryTree(Object rootCategory, boolean rootSave) {
		return getCategoryTree(rootCategory, 0, true, rootSave);
	}

	/**
	 * @param rootCategory
	 *            ���� ī�װ�
	 * @param level
	 *            ���� ���� ī�װ� ����
	 * @param modifyLevel
	 *            ture ���� ���� ����, false �������� ���� *
	 * @param rootSave
	 *            ��Ʈ ī�װ� ���� ����
	 * @return ���ĵ� ī�װ� �迭
	 */
	private OBJ[] getCategoryTree(Object rootCategory, int level, boolean modifyLevel, boolean rootSave) {
		ArrayList<OBJ> saveCategory = new ArrayList<OBJ>();

		Enumeration<OBJ> e = category.elements();
		TreeSet<OBJ> map = new TreeSet<OBJ>();

		for (int i = 0; e.hasMoreElements(); i++) {
			map.add(e.nextElement());
		}

		// ��Ʈ ī�װ� ����
		OBJ root = getCategory(rootCategory);
		if (modifyLevel) {
			root.setLevel(level);
		}
		OBJ[] cat = (OBJ[]) map.toArray(_obj_empty_array);
		if (rootSave) {
			saveCategory.add(root);
		}

		recurrence(cat, rootCategory, saveCategory, level + 1, modifyLevel);
		return (OBJ[]) saveCategory.toArray(_obj_empty_array);
	}

	/**
	 * ��� ȣ���� ���� ī�װ� ���̵� ����
	 * 
	 * @param cat
	 *            ī�װ� ������
	 * 
	 * @param currentCategoryID
	 *            ���� ī�װ� ���̵�
	 * @param saveCategory
	 *            ��� ȣ���� ���� ī�װ� ������ ��� ����
	 * @param level
	 *            ���� ���� ī�װ� ����
	 * @param modifyLevel
	 *            ture ���� ���� ����, false �������� ����
	 */
	private void recurrence(OBJ[] cat, Object currentCategoryID, ArrayList<OBJ> saveCategory, int level,
			boolean modifyLevel) {
		for (int i = 0; i < cat.length; i++) {
			OBJ mc = cat[i];
			if (mc.getParent().equals(currentCategoryID) && !mc.getId().equals(categoryRootID)) {
				if (modifyLevel)
					mc.setLevel(level);
				saveCategory.add(mc);
				recurrence(cat, mc.getId(), saveCategory, level + 1, modifyLevel);
			}
		}
	}

}
