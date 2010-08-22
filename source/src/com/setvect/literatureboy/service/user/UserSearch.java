package com.setvect.literatureboy.service.user;

import com.setvect.common.util.SearchListVo;
import com.setvect.common.util.StringUtilAd;

/**
 * �Խù� ���� ��� ����¡ �� �˻� ����
 * 
 * @version $Id$
 */
public class UserSearch extends SearchListVo {

	/** */
	private static final long serialVersionUID = -4496364103835682025L;
	private String searchName;
	private String searchId;

	public UserSearch(int currentPage) {
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
	public String getSearchId() {
		return searchId;
	}

	/**
	 * @param searchId
	 *            the searchCode to set
	 */
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	/**
	 * @return �˻� �ܾ��� ���� �ִ� �ϳ��� ��ȯ. �ƹ��� ������ null. 2�� �̻� ���� ���� ��� ��� ��ȯ ���� ��
	 */
	public String getWord() {
		if (!StringUtilAd.isEmpty(searchId)) {
			return searchId;
		}
		if (!StringUtilAd.isEmpty(searchName)) {
			return searchName;
		}
		return null;
	}

}
