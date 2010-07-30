package com.setvect.common.util;

import java.util.Collection;

import anyframe.common.Page;

/**
 * Page에서 검색된 객체들의 타입을 지정함
 */
public class GenericPage<T> extends Page {

	/** */
	private static final long serialVersionUID = -5941168763829250384L;

	public GenericPage(Collection<T> objects, int currentPage, int totalCount, int pageunit, int pagesize) {
		super(objects, currentPage, totalCount, pageunit, pagesize);
	}

	@SuppressWarnings("unchecked")
	public Collection<T> getList() {
		return super.getList();
	}

}