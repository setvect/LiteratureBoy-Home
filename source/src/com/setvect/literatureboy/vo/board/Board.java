package com.setvect.literatureboy.vo.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * 게시판 설정
 * 
 * @version $Id$
 */
@Entity
@Table(name = "TBBA_BOARD_MANAGER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Board {
	@Id
	@Column(name = "BOARD_CODE")
	private String boardCode;

	@Column(name = "NAME")
	private String name;

	@Column(name = "HEAD")
	private String head;

	@Column(name = "TAIL")
	private String tail;

	/** 총 게시물 파일 업로드 제한 */
	@Column(name = "UPLOAD_LIMIT")
	private int uploadLimit;

	/** 한페이지당 표시 게시물 수 */
	@Column(name = "EXP_COUNT")
	private int expCount;

	@Column(name = "LEVEL_SET")
	private String levelSet;

	@Column(name = "REPLY_F")
	@Type(type = "yes_no")
	private boolean replyF;

	@Column(name = "COMMENT_F")
	@Type(type = "yes_no")
	private boolean commentF;

	@Column(name = "PDS_F")
	@Type(type = "yes_no")
	private boolean pdsF;

	/** 암호화 게시물 허용 여부 */
	@Column(name = "ENCODE_F")
	@Type(type = "yes_no")
	private boolean encodeF;

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
	 * @return the head
	 */
	public String getHead() {
		return head;
	}

	/**
	 * @param head
	 *            the head to set
	 */
	public void setHead(String head) {
		this.head = head;
	}

	/**
	 * @return the tail
	 */
	public String getTail() {
		return tail;
	}

	/**
	 * @param tail
	 *            the tail to set
	 */
	public void setTail(String tail) {
		this.tail = tail;
	}

	/**
	 * @return the uploadLimit
	 */
	public int getUploadLimit() {
		return uploadLimit;
	}

	/**
	 * @param uploadLimit
	 *            the uploadLimit to set
	 */
	public void setUploadLimit(int uploadLimit) {
		this.uploadLimit = uploadLimit;
	}

	/**
	 * @return the expCount
	 */
	public int getExpCount() {
		return expCount;
	}

	/**
	 * @param expCount
	 *            the expCount to set
	 */
	public void setExpCount(int expCount) {
		this.expCount = expCount;
	}

	/**
	 * @return the levelSet
	 */
	public String getLevelSet() {
		return levelSet;
	}

	/**
	 * @param levelSet
	 *            the levelSet to set
	 */
	public void setLevelSet(String levelSet) {
		this.levelSet = levelSet;
	}

	/**
	 * @return the replyF
	 */
	public boolean isReplyF() {
		return replyF;
	}

	/**
	 * @param replyF
	 *            the replyF to set
	 */
	public void setReplyF(boolean replyF) {
		this.replyF = replyF;
	}

	/**
	 * @return the commentF
	 */
	public boolean isCommentF() {
		return commentF;
	}

	/**
	 * @param commentF
	 *            the commentF to set
	 */
	public void setCommentF(boolean commentF) {
		this.commentF = commentF;
	}

	/**
	 * @return the pdsF
	 */
	public boolean isPdsF() {
		return pdsF;
	}

	/**
	 * @param pdsF
	 *            the pdsF to set
	 */
	public void setPdsF(boolean pdsF) {
		this.pdsF = pdsF;
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

}
