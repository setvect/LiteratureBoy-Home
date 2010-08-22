package com.setvect.literatureboy.vo.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * 권한 정보
 * 
 * @version $Id: Board.java 54 2010-08-09 14:25:54Z setvect@naver.com $
 */
@Entity
@Table(name = "TBAB_AUTH")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Auth {
	@Id
	@Column(name = "AUTH_SEQ")
	private int authSeq;

	/** 권한 구분 이름 */
	@Column(name = "NAME")
	private String name;

	@Column(name = "URL")
	private String url;

	/** 총 게시물 파일 업로드 제한 */
	@Column(name = "PARAMETER")
	private int parameter;

	/** 게시판 삭제 여부 */
	@Column(name = "DELETE_F")
	@Type(type = "yes_no")
	private boolean deleteF;

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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the parameter
	 */
	public int getParameter() {
		return parameter;
	}

	/**
	 * @param parameter
	 *            the parameter to set
	 */
	public void setParameter(int parameter) {
		this.parameter = parameter;
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
