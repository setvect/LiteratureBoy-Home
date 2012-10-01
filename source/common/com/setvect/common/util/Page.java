package com.setvect.common.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Page<T> implements Serializable {
	/** */
	private static final long serialVersionUID = 444871409440367735L;
	public static final Page<Object> EMPTY_PAGE = new Page<Object>(Collections.emptyList(), 1, 0, "", "");
	private List<T> objects;
	private int currentPage;
	private int totalCount;
	private int pageunit;
	private int pagesize;
	private int maxPage;
	private int beginUnitPage;
	private int endUnitPage;
	private String search;
	private String condition;

	public Page() {
		this.pageunit = 10;

		this.pagesize = 10;

		this.search = "";

		this.condition = "";
	}

	public Page(List<T> objects, int currentPage, int totalCount) {
		this.pageunit = 10;

		this.pagesize = 10;

		this.search = "";

		this.condition = "";

		this.objects = objects;
		this.totalCount = totalCount;
		this.maxPage = ((this.pagesize == 0) ? this.totalCount : (totalCount - 1) / this.pagesize + 1);

		this.currentPage = ((currentPage > this.maxPage) ? this.maxPage : currentPage);
		this.beginUnitPage = ((currentPage - 1) / this.pageunit * this.pageunit + 1);
		this.endUnitPage = (this.beginUnitPage + this.pageunit - 1);
	}

	public Page(List<T> objects, int currentPage, int totalCount, String condition, String search) {
		this(objects, currentPage, totalCount);
		this.condition = condition;
		this.search = search;
	}

	public Page(List<T> objects, int currentPage, int totalCount, int pageunit, int pagesize) {
		this.pageunit = 10;

		this.pagesize = 10;

		this.search = "";

		this.condition = "";

		if ((pageunit <= 0) || (pagesize <= 0)) {
			throw new RuntimeException("Page unit or page size should be over 0.");
		}
		this.pageunit = pageunit;
		this.pagesize = pagesize;
		this.objects = objects;
		this.totalCount = totalCount;
		this.maxPage = ((pagesize == 0) ? this.totalCount : (totalCount - 1) / pagesize + 1);

		this.currentPage = ((currentPage > this.maxPage) ? this.maxPage : currentPage);
		this.beginUnitPage = ((currentPage - 1) / pageunit * pageunit + 1);
		this.endUnitPage = (this.beginUnitPage + pageunit - 1);
	}

	public List<T> getList() {
		return this.objects;
	}

	public boolean hasNextPage() {
		return (this.currentPage < this.maxPage);
	}

	public boolean hasPreviousPage() {
		return (this.currentPage > 1);
	}

	public int getNextPage() {
		return (this.currentPage + 1);
	}

	public void setNextPage(int val) {
	}

	public int getPreviousPage() {
		return (this.currentPage - 1);
	}

	public void setPreviousPage(int val) {
	}

	public boolean hasNextPageUnit() {
		return (this.endUnitPage < this.maxPage);
	}

	public boolean hasPreviousPageUnit() {
		return (this.currentPage >= this.pageunit + 1);
	}

	public int getStartOfNextPageUnit() {
		return (this.endUnitPage + 1);
	}

	public int getStartOfPreviousPageUnit() {
		return (this.beginUnitPage - 1);
	}

	public int getPageOfNextPageUnit() {
		return ((this.currentPage + this.pageunit < this.maxPage) ? this.currentPage + this.pageunit : this.maxPage);
	}

	public int getPageOfPreviousPageUnit() {
		return ((this.currentPage - this.pageunit > 1) ? this.currentPage - this.pageunit : 1);
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public int getBeginUnitPage() {
		return this.beginUnitPage;
	}

	public int getEndListPage() {
		return ((this.endUnitPage > this.maxPage) ? this.maxPage : this.endUnitPage);
	}

	public void setEndListPage(int val) {
	}

	public int getSize() {
		return this.objects.size();
	}

	public boolean isEmptyPage() {
		return ((this.objects == null) || (getSize() == 0));
	}

	public void setEmptyPage(boolean val) {
	}

	public String getCurrentPageStr() {
		return new Integer(this.currentPage).toString();
	}

	public void setCurrentPageStr(String str) {
	}

	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getSearch() {
		return this.search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getPagesize() {
		return this.pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageunit() {
		return this.pageunit;
	}

	public void setPageunit(int pageunit) {
		this.pageunit = pageunit;
	}

	public int getMaxPage() {
		return this.maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getEndUnitPage() {
		return this.endUnitPage;
	}

	public void setEndUnitPage(int endUnitPage) {
		this.endUnitPage = endUnitPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setBeginUnitPage(int beginUnitPage) {
		this.beginUnitPage = beginUnitPage;
	}
}