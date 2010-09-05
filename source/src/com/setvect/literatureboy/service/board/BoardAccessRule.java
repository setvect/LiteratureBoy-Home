package com.setvect.literatureboy.service.board;

import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.user.User;
import com.setvect.literatureboy.web.AccessRule;

/**
 * �Խù� ���� ���� üũ
 * 
 * @version $Id$
 */
public class BoardAccessRule implements AccessRule {

	/** �н����� ���� ���� */
	public static final int STATUS_PASSWD_NOT_MATCH = 1;
	public static final int STATUS_NONE = 0;

	private BoardArticle article;
	private User user;
	private String passwd;
	private boolean access;
	private int status = STATUS_NONE;

	/**
	 * @param article
	 *            �Խù�
	 * @param user
	 *            �α��� ����
	 */
	public BoardAccessRule(BoardArticle article, User user) {
		this.article = article;
		this.user = user;
		process();
	}

	/**
	 * @param article
	 *            �Խù�
	 * @param passwd
	 *            �Էµ� �н�����
	 */
	public BoardAccessRule(BoardArticle article, String passwd) {
		this.article = article;
		this.passwd = passwd;
		process();
	}

	/**
	 * ���� ó��
	 */
	private void process() {
		// �α����ؼ� �ۼ��� ���� �ƴҶ� ���� �˻�
		if (user == null) {
			if (StringUtilAd.isEmpty(article.getUserId())) {
				if (article.getPasswd() != null && article.getPasswd().equals(passwd)) {
					access = true;
					return;
				}
				else {
					// �н����� �Է� ����(���� �Է� ���)
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

		// �α��������� �ۼ��� ���̸� �ش� ����� ���� �Ǵ�
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
