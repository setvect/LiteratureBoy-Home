package com.setvect.literatureboy.vo.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.setvect.literatureboy.web.ConstraintWeb;

/**
 * 회원
 * 
 * @version $Id: Board.java 54 2010-08-09 14:25:54Z setvect@naver.com $
 */
@Entity
@Table(name = "TBAA_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable {
	/** */
	private static final long serialVersionUID = 4058914352122647610L;

	@Id
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "NAME")
	private String name;

	/**
	 * MD5로 암호화
	 * 
	 * @see ConstraintWeb#PASSWD_ALGORITHM
	 */
	@Column(name = "PASSWD")
	private String passwd;

	@Column(name = "EMAIL")
	private String email;

	/** 관리자 여부 */
	@Column(name = "ADMIN_F")
	@Type(type = "yes_no")
	private boolean adminF;

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
	 * @return the adminF
	 */
	public boolean isAdminF() {
		return adminF;
	}

	/**
	 * @param adminF
	 *            the adminF to set
	 */
	public void setAdminF(boolean adminF) {
		this.adminF = adminF;
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
