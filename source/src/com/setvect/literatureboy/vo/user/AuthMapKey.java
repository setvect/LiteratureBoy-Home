package com.setvect.literatureboy.vo.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ����ڿ� ���� ����
 * 
 * @version $Id: ConnKey.java 590 2009-06-02 10:29:25Z setvect $
 */
@Embeddable
public class AuthMapKey implements Serializable {

	/** */
	private static final long serialVersionUID = -8519884056908412301L;

	/** ���� �Ϸ����� */
	@Column(name = "AUTH_SEQ")
	private int authSeq;

	/** ����� */
	@Column(name = "USER_ID")
	private String userId;

	/**
	 * @return the authSeq
	 */
	public int getAuthSeq() {
		return authSeq;
	}

	/**
	 * @param authSeq
	 *            the authSeq to set
	 */
	public void setAuthSeq(int authSeq) {
		this.authSeq = authSeq;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		else if (o == this) {
			return true;
		}
		else if (o.getClass() == AuthMapKey.class) {
			AuthMapKey other = (AuthMapKey) o;
			return other.authSeq == authSeq && other.userId.equals(userId);
		}
		else {
			return false;
		}
	}
}