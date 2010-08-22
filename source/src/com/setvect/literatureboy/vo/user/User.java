package com.setvect.literatureboy.vo.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * 회원
 * 
 * @version $Id: Board.java 54 2010-08-09 14:25:54Z setvect@naver.com $
 */
@Entity
@Table(name = "TBAA_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {
	@Id
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "NAME")
	private String name;

	/** 총 게시물 파일 업로드 제한 */
	@Column(name = "PASSWD")
	private String passwd;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "DELETE_F")
	@Type(type = "yes_no")
	private boolean deleteF;

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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd
	 *            the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the deleteF
	 */
	public boolean isDeleteF() {
		return deleteF;
	}

	/**
	 * @param deleteF
	 *            the deleteF to set
	 */
	public void setDeleteF(boolean deleteF) {
		this.deleteF = deleteF;
	}

}
