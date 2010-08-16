package com.setvect.common.util;

import java.io.Serializable;

/**
 * 각종 검색 조건을 표현함
 */
public class SearchListVo implements Serializable {
	/** */
	private static final long serialVersionUID = 4784964094940288037L;

	/** 한페이지당 표시 항목 수 */
	private static final int DEFAULT_PAGE_VIEW_COUNT = 15;

	/** 페이지 묶음 */
	private static final int DEFAULT_PAGE_UNIT = 10;

	/** 현재 페이지 */
	private int currentPage;

	/** 한페이지당 표시 항목 수 */
	private int pagePerItemCount = DEFAULT_PAGE_VIEW_COUNT;

	/** 페이지 묶음 갯수 */
	private int pageUnit = DEFAULT_PAGE_UNIT;

	/**
	 * @param currentPage
	 *            현 페이지. 1부터 시작
	 */
	public SearchListVo(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return 현 페이지. 1부터 시작
	 */
	public int getCurrentPage() {
		return this.currentPage;
	}

	/**
	 * @return 한페이지당 표시 항목 수
	 */
	public int getPagePerItemCount() {
		return this.pagePerItemCount;
	}

	/**
	 * @param pagePerItemCount
	 *            한페이지당 표시 항목 수
	 */
	public void setPagePerItemCount(int pagePerItemCount) {
		this.pagePerItemCount = pagePerItemCount;
	}

	/**
	 * 페이지 묶음에서 보여줄 페이지 갯수<br>
	 * 예) 아래와 같은 경우 묶음의 크기는 3<br>
	 * [이전] 3, 4, 5 [다음]<br>
	 * 
	 * @return the pageUnit
	 */
	public int getPageUnit() {
		return pageUnit;
	}

	/**
	 * @param pageUnit
	 *            페이지 묶음 갯수
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	/**
	 * 예를 들어 한 페이지당 10개의 항목을 표시 하고 있고, 현재 3번째 페이지를 봐야 된다면<br>
	 * 10 * 3 = 30<br>
	 * 즉 30번째 부터 항목을 가져와야 됨
	 * 
	 * @return 가져 와야 될 항목 시작 번호(0부터 시작)
	 */
	public int getStartNumber() {
		return (getCurrentPage() - 1) * getPagePerItemCount();
	}

}