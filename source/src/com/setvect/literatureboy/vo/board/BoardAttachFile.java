package com.setvect.literatureboy.vo.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Ã·ºÎÆÄÀÏ
 * 
 * @version $Id$
 */
@Entity
@Table(name = "TBBD_BOARD_FILE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoardAttachFile {
	@Id
	@Column(name = "FILE_SEQ")
	private int fileSeq;

	@Column(name = "ARTICLE_SEQ")
	private int articleSeq;

	@Column(name = "ORIGINAL_NAME")
	private String originalName;

	@Column(name = "SAVE_NAME")
	private String saveName;

	@Column(name = "SIZE")
	private int size;

	/**
	 * @return the fileSeq
	 */
	public int getFileSeq() {
		return fileSeq;
	}

	/**
	 * @param fileSeq
	 *            the fileSeq to set
	 */
	public void setFileSeq(int fileSeq) {
		this.fileSeq = fileSeq;
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
	 * @return the originalName
	 */
	public String getOriginalName() {
		return originalName;
	}

	/**
	 * @param originalName
	 *            the originalName to set
	 */
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	/**
	 * @return the saveName
	 */
	public String getSaveName() {
		return saveName;
	}

	/**
	 * @param saveName
	 *            the saveName to set
	 */
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
}
