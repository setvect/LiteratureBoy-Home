package com.setvect.literatureboy.vo.board;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

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
	@GenericGenerator(name = "hibernate-increment", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate-increment")
	private int commentSeq;
	/** */
	@Column(name = "ARTICLE_SEQ")
	private int articleSeq;
	/** */
	@Column(name = "USER_ID")
	private String userId;
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
	 * @return the commentSeq
	 */
	public int getCommentSeq() {
		return commentSeq;
	}

	/**
	 * @param commentSeq
	 *            the commentSeq to set
	 */
	public void setCommentSeq(int commentSeq) {
		this.commentSeq = commentSeq;
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
	public String getUserId() {
		return userId;
	}

	/**
	 * @param memberId
	 *            the memberId to set
	 */
	public void setUserId(String memberId) {
		this.userId = memberId;
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

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

}
