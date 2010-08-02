package com.setvect.common.util;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 각종 검색 조건을 표현함
 */
public class PagingCondition implements Serializable {
	/** */
	private static final long serialVersionUID = 4784964094940288037L;

	/** 한페이지당 표시 항목 수 */
	private static final int DEFAULT_PAGE_VIEW_COUNT = 15;

	/** 페이지 묶음 */
	private static final int DEFAULT_PAGE_UNIT = 10;

	/** 현재 페이지 */
	private int currentPageNo;

	/** 한페이지당 표시 항목 수 */
	private int pagePerItemCount = DEFAULT_PAGE_VIEW_COUNT;

	/** 페이지 묶음 갯수 */
	private int pageUnit = DEFAULT_PAGE_UNIT;

	/** 검색 정보 키:값 형태로 구성 */
	private HashMap<Object, Object> searchData = new HashMap<Object, Object>();

	/**
	 * @param currentPageNo
	 *            현 페이지. 1부터 시작
	 */
	public PagingCondition(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	/**
	 * @param currentPageNo
	 *            현 페이지. 1부터 시작
	 * @param pagePerItemCount
	 *            한페이지당 표시 항목 수
	 */
	public PagingCondition(int currentPageNo, int pagePerItemCount) {
		this.currentPageNo = currentPageNo;
		this.pagePerItemCount = pagePerItemCount;
	}

	/**
	 * @param currentPageNo
	 *            현 페이지. 1부터 시작
	 * @param pagePerItemCount
	 *            한페이지당 표시 항목 수
	 * @param pageUnit
	 *            페이지 묶음 크기
	 * 
	 */
	public PagingCondition(int currentPageNo, int pagePerItemCount, int pageUnit) {
		this.currentPageNo = currentPageNo;
		this.pagePerItemCount = pagePerItemCount;
		this.pageUnit = pageUnit;
	}

	/**
	 * @return 현 페이지. 1부터 시작
	 */
	public int getCurrentPageNo() {
		return this.currentPageNo;
	}

	/**
	 * @return 한페이지당 표시 항목 수
	 */
	public int getPagePerItemCount() {
		return this.pagePerItemCount;
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
	 * 예를 들어 한 페이지당 10개의 항목을 표시 하고 있고, 현재 3번째 페이지를 봐야 된다면<br>
	 * 10 * 3 = 30<br>
	 * 즉 30번째 부터 항목을 가져와야 됨
	 * 
	 * @return 가져 와야 될 항목 시작 번호(0부터 시작)
	 */
	public int getStartNumber() {
		return (getCurrentPageNo() - 1) * getPagePerItemCount();
	}

	/**
	 * 검색 조건 등록
	 * 
	 * @param key
	 *            검색 key
	 * @param cond
	 *            검색 value
	 */
	public void addCondition(Object key, Object cond) {
		this.searchData.put(key, cond);
	}

	/**
	 * @param key
	 *            검색 key
	 * @return key에 대한 값
	 */
	public Object getCondition(Object key) {
		return this.searchData.get(key);
	}

	/**
	 * @param key
	 *            검색 key
	 * @return key에 대한 값에 toString()
	 */
	public String getConditionString(Object key) {
		Object o = this.searchData.get(key);
		if (o == null) {
			return null;
		}
		return o.toString();
	}

	/**
	 * @return 검색 맵 정보
	 */
	public HashMap<Object, Object> getCondition() {
		return this.searchData;
	}
}