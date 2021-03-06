package com.setvect.literatureboy.service.user;

import com.setvect.common.util.SearchListVo;
import com.setvect.common.util.StringUtilAd;

/**
 * 게시물 관리 목록 페이징 및 검색 조건
 * 
 * @version $Id$
 */
public class AuthSearch extends SearchListVo {

	/** */
	private static final long serialVersionUID = 551115325294892376L;

	private String searchUrl;
	private String searchName;

	/**
	 * @param currentPage
	 *            현 페이지. 1부터 시작
	 */
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
	 * @return 검색 단어중 값이 있는 하나를 반환. 아무도 없으면 null. 2개 이상 값이 있을 경우 어떤걸 반환 할지 모름
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
