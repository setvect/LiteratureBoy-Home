package com.setvect.literatureboy.service.board;

import com.setvect.common.util.SearchListVo;
import com.setvect.common.util.StringUtilAd;

/**
 * �Խù� ���� ��� ����¡ �� �˻� ����
 * 
 * @version $Id$
 */
public class BoardManagerSearch extends SearchListVo {

	/** */
	private static final long serialVersionUID = -4496364103835682025L;
	private String searchName;
	private String searchCode;

	public BoardManagerSearch(int currentPage) {
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
	 * @return �˻� �ܾ��� ���� �ִ� �ϳ��� ��ȯ. �ƹ��� ������ null. 2�� �̻� ���� ���� ��� ��� ��ȯ ���� ��
	 */
	public String getWord() {
		if (!StringUtilAd.isEmpty(searchCode)) {
			return searchCode;
		}
		if (!StringUtilAd.isEmpty(searchName)) {
			return searchName;
		}
		return null;
	}

}
