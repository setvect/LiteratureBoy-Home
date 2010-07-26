package com.setvect.literatureboy.vo.board;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 게시물 코멘트
 * 
 * @version $Id$
 */
@Entity
@Table(name = "TBBC_BOARD_COMMENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoardComment {
	/** */
	@Id
	@Column(name = "COMMENT_SEQ")
	private int commectSeq;
	/** */
	@Column(name = "ARTICLE_SEQ")
	private int articleSeq;
	/** */
	@Column(name = "MEMBER_ID")
	private String memberId;
	/** */
	@Column(name = "NAME")
	private String name;
	/** */
	@Column(name = "PASSWD")
	private String passwd;
	/** */
	@Column(name = "CONTENT")
	private String content;
	/** */
	@Column(name = "IP")
	private String ip;
	/** */
	@Column(name = "REG_DATE")
	private Date regDate;

	/**
	 * @return the commectSeq
	 */
	public int getCommectSeq() {
		return commectSeq;
	}

	/**
	 * @param commectSeq
	 *            the commectSeq to set
	 */
	public void setCommectSeq(int commectSeq) {
		this.commectSeq = commectSeq;
	}

	/**
	 * @return the articleSeq
	 */
	public int getArticleSeq() {
		return articleSeq;
	}

	/**
	 * @param articleSeq
	 *            the articleSeq to set
	 */
	public void setArticleSeq(int articleSeq) {
		this.articleSeq = articleSeq;
	}

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId
	 *            the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the regDate
	 */
	public Date getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate
	 *            the regDate to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
