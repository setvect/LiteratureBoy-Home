package com.setvect.literatureboy.service.board;

import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.user.User;
import com.setvect.literatureboy.web.AccessRule;

/**
 * 게시물 접근 권한 체크
 * 
 * @version $Id$
 */
public class BoardAccessRule implements AccessRule {

	/** 패스워드 맞지 않음 */
	public static final int STATUS_PASSWD_NOT_MATCH = 1;
	public static final int STATUS_NONE = 0;

	private BoardArticle article;
	private User user;
	private String passwd;
	private boolean access;
	private int status = STATUS_NONE;

	/**
	 * @param article
	 *            게시물
	 * @param user
	 *            로그인 정보
	 */
	public BoardAccessRule(BoardArticle article, User user) {
		this.article = article;
		this.user = user;
		process();
	}

	/**
	 * @param article
	 *            게시물
	 * @param passwd
	 *            입력된 패스워드
	 */
	public BoardAccessRule(BoardArticle article, String passwd) {
		this.article = article;
		this.passwd = passwd;
		process();
	}

	/**
	 * 권한 처리
	 */
	private void process() {
		// 로그인해서 작성된 글이 아닐때 권한 검사
		if (user == null) {
			if (StringUtilAd.isEmpty(article.getUserId())) {
				if (article.getPasswd() != null && article.getPasswd().equals(passwd)) {
					access = true;
					return;
				}
				else {
					// 패스워드 입력 오류(새로 입력 요망)
					status = STATUS_PASSWD_NOT_MATCH;
				}
			}
			else {
				access = false;
				return;
			}
			access = false;
			return;
		}

		// 로그인했을때 작성한 글이면 해당 사용자 인지 판단
		access = user.getUserId().equals(article.getUserId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.web.AccessRule#isAccess()
	 */
	public boolean isAccess() {
		return access;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.web.AccessRule#getStatus()
	 */
	public int getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

}
