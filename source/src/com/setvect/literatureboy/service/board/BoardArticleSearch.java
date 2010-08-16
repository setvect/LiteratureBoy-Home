package com.setvect.literatureboy.service.board;

import com.setvect.common.util.SearchListVo;
import com.setvect.common.util.StringUtilAd;

/**
 * �Խù� ���� ��� ����¡ �� �˻� ����
 * 
 * @version $Id$
 */
public class BoardArticleSearch extends SearchListVo {

	/** */
	private static final long serialVersionUID = 6832353125378512460L;

	private String searchName;
	private String searchCode;
	private String searchTitle;
	private String searchContent;

	public BoardArticleSearch(int currentPage) {
		super(currentPage);
	}

	/**
	 * @return the searchName
	 */
	public String getSearchName() {
		return searchName;
	}

	/**
	 * @param searchName
	 *            the searchName to set
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * @return the searchCode
	 */
	public String getSearchCode() {
		return searchCode;
	}

	/**
	 * @param searchCode
	 *            the searchCode to set
	 */
	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}

	/**
	 * @return the searchTitle
	 */
	public String getSearchTitle() {
		return searchTitle;
	}

	/**
	 * @param searchTitle
	 *            the searchTitle to set
	 */
	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	/**
	 * @return the searchContent
	 */
	public String getSearchContent() {
		return searchContent;
	}

	/**
	 * @param searchContent
	 *            the searchContent to set
	 */
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	/**
	 * @return �˻� �ܾ��� ���� �ִ� �ϳ��� ��ȯ. �ƹ��� ������ null. 2�� �̻� ���� ���� ��� ��� ��ȯ ���� ��
	 */
	public String getWord() {

		if (!StringUtilAd.isEmpty(searchName)) {
			return searchName;
		}

		if (!StringUtilAd.isEmpty(searchTitle)) {
			return searchTitle;
		}

		if (!StringUtilAd.isEmpty(searchContent)) {
			return searchContent;
		}
		return null;
	}

}
