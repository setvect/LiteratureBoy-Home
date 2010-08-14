package com.setvect.common.util;

import java.util.Collection;

import anyframe.common.Page;

/**
 * Page에서 검색된 객체들의 타입을 지정함
 */
public class GenericPage<T> extends Page {

	/** */
	private static final long serialVersionUID = -5941168763829250384L;

	/**
	 * @param objects
	 *            리스트
	 * @param currentPage
	 *            현재 페이지
	 * @param totalCount
	 *            전체 항목 수
	 * @param pageunit
	 *            묶음 페이지 갯수
	 * @param pagesize
	 *            페이지당 표시 갯수
	 */
	public GenericPage(Collection<T> objects, int currentPage, int totalCount, int pageunit, int pagesize) {
		super(objects, currentPage, totalCount, pageunit, pagesize);
	}

	@SuppressWarnings("unchecked")
	public Collection<T> getList() {
		return super.getList();
	}

	/**
	 * @return 현재 페이지의 첫번째 항목의 행번호.<br>
	 *         1부터 시작
	 */
	public int getRowNum() {
		return getPagesize() * (getCurrentPage() - 1) + 1;
	}

	/**
	 * @return 현재 페이지의 첫번째 항목의 행번호. 역순<br>
	 *         범위 : total() ~ 1
	 */
	public int getRowNumDesc() {
		return getTotal() - (getPagesize() * (getCurrentPage() - 1));
	}

}