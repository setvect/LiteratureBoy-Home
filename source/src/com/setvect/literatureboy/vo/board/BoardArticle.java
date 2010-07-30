package com.setvect.literatureboy.vo.board;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * �Խù� VO
 * 
 * @version $Id$
 */
@Entity
@Table(name = "TBBB_BOARD_ARTICLE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoardArticle {
	/** */
	@Id
	@Column(name = "ARTICLE_SEQ")
	private int articleSeq;

	/** */
	@Column(name = "BOARD_CODE")
	private String boardCode;

	/** */
	@Column(name = "MEMBER_ID")
	private String memberId;

	/** */
	@Column(name = "IDX1")
	private int idx1;

	/** */
	@Column(name = "IDX2")
	private int idx2;

	/** */
	@Column(name = "IDX3")
	private int idx3;

	/** */
	@Column(name = "DEPTH_LEVEL")
	private int depthLevel;

	/** */
	@Column(name = "TITLE")
	private String title;

	/** */
	@Column(name = "NAME")
	private String name;

	/** */
	@Column(name = "EMAIL")
	private String email;

	/** */
	@Column(name = "PASSWD")
	private String passwd;

	/** */
	@Column(name = "CONTENT")
	private String content;

	/** */
	@Column(name = "IP")
	private String ip;

	/** ��ȸ�� */
	@Column(name = "HIT_COUNT")
	private int hitCount;

	/** ��ȣȭ�� �� ���� */
	@Column(name = "ENCODE_F")
	@Type(type = "yes_no")
	private boolean encodeF;

	/** */
	@Column(name = "REG_DATE")
	private Date regDate;

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
	 * @return the boardCode
	 */
	public String getBoardCode() {
		return boardCode;
	}

	/**
	 * @param boardCode
	 *            the boardCode to set
	 */
	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
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
	 * @return the idx1
	 */
	public int getIdx1() {
		return idx1;
	}

	/**
	 * @param idx1
	 *            the idx1 to set
	 */
	public void setIdx1(int idx1) {
		this.idx1 = idx1;
	}

	/**
	 * @return the idx2
	 */
	public int getIdx2() {
		return idx2;
	}

	/**
	 * @param idx2
	 *            the idx2 to set
	 */
	public void setIdx2(int idx2) {
		this.idx2 = idx2;
	}

	/**
	 * @return the idx3
	 */
	public int getIdx3() {
		return idx3;
	}

	/**
	 * @param idx3
	 *            the idx3 to set
	 */
	public void setIdx3(int idx3) {
		this.idx3 = idx3;
	}

	/**
	 * @return the depthLevel
	 */
	public int getDepthLevel() {
		return depthLevel;
	}

	/**
	 * @param depthLevel
	 *            the depthLevel to set
	 */
	public void setDepthLevel(int depthLevel) {
		this.depthLevel = depthLevel;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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

	/**
	 * @return the hitCount
	 */
	public int getHitCount() {
		return hitCount;
	}

	/**
	 * @param hitCount
	 *            the hitCount to set
	 */
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	/**
	 * @return the encodeF
	 */
	public boolean isEncodeF() {
		return encodeF;
	}

	/**
	 * @param encodeF
	 *            the encodeF to set
	 */
	public void setEncodeF(boolean encodeF) {
		this.encodeF = encodeF;
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