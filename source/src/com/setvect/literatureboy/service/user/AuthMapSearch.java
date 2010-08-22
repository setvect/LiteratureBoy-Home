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

	public AuthMapSearch(int currentPage) {
		super(currentPage);
	}

}
