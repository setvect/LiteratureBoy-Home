package com.setvect.literatureboy.service.user;

import com.setvect.common.util.SearchListVo;

/**
 * 게시물 관리 목록 페이징 및 검색 조건
 * 
 * @version $Id$
 */
public class AuthMapSearch extends SearchListVo {

	/** */
	private static final long serialVersionUID = 551115325294892376L;
	private String searchUserId;
	private int searchAuthSeq;

	public AuthMapSearch(int currentPage) {
		super(currentPage);
	}

	/**
	 * @return the searchUserId
	 */
	public String getSearchUserId() {
		return searchUserId;
	}

	/**
	 * @param searchUserId
	 *            the searchUserId to set
	 */
	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
	}

	/**
	 * @return the searchAuthSeq
	 */
	public int getSearchAuthSeq() {
		return searchAuthSeq;
	}

	/**
	 * @param searchAuthSeq
	 *            the searchAuthSeq to set
	 */
	public void setSearchAuthSeq(int searchAuthSeq) {
		this.searchAuthSeq = searchAuthSeq;
	}

}
