package com.setvect.literatureboy.service.user;

import com.setvect.common.util.SearchListVo;
import com.setvect.common.util.StringUtilAd;

/**
 * �Խù� ���� ��� ����¡ �� �˻� ����
 * 
 * @version $Id$
 */
public class AuthSearch extends SearchListVo {

	/** */
	private static final long serialVersionUID = 551115325294892376L;

	private String searchUrl;
	private String searchName;

	public AuthSearch(int currentPage) {
		super(currentPage);
	}

	/**
	 * @return the searchUrl
	 */
	public String getSearchUrl() {
		return searchUrl;
	}

	/**
	 * @param searchUrl
	 *            the searchUrl to set
	 */
	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
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
	 * @return �˻� �ܾ��� ���� �ִ� �ϳ��� ��ȯ. �ƹ��� ������ null. 2�� �̻� ���� ���� ��� ��� ��ȯ ���� ��
	 */
	public String getWord() {
		if (!StringUtilAd.isEmpty(searchUrl)) {
			return searchUrl;
		}
		if (!StringUtilAd.isEmpty(searchName)) {
			return searchName;
		}
		return null;
	}
}
