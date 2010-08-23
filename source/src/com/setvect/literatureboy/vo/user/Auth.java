package com.setvect.literatureboy.vo.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * ���� ����
 * 
 * @version $Id: Board.java 54 2010-08-09 14:25:54Z setvect@naver.com $
 */
@Entity
@Table(name = "TBAB_AUTH")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Auth {
	@Id
	@Column(name = "AUTH_SEQ")
	@GenericGenerator(name = "hibernate-increment", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate-increment")
	private int authSeq;

	/** ���� ���� �̸� */
	@Column(name = "NAME")
	private String name;

	@Column(name = "URL")
	private String url;

	/** �� �Խù� ���� ���ε� ���� */
	@Column(name = "PARAMETER")
	private String parameter;
	/** ���� �ο� ���� */
	@Transient
	private boolean authHave;

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
	public String getParameter() {
		return parameter;
	}

	/**
	 * @param parameter
	 *            the parameter to set
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/**
	 * @return the have
	 */
	public boolean isAuthHave() {
		return authHave;
	}

	/**
	 * @param have
	 *            the have to set
	 */
	public void setAuthHave(boolean have) {
		this.authHave = have;
	}

}
