package com.setvect.literatureboy.vo.board;

import java.io.File;

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

import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.config.EnvirmentProperty;
import com.setvect.literatureboy.service.board.BoardService;

/**
 * 첨부파일
 * 
 * @version $Id$
 */
@Entity
@Table(name = "TBBD_BOARD_FILE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoardAttachFile {

	/** 업로드 기준 URL */
	private final static String ATTACH_UPLOAD_URL = EnvirmentProperty
			.getString("com.setvect.literatureboy.board.file_upload_url");

	@Id
	@Column(name = "FILE_SEQ")
	@GenericGenerator(name = "hibernate-increment", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate-increment")
	private int fileSeq;

	@Column(name = "ARTICLE_SEQ")
	private int articleSeq;

	@Column(name = "ORIGINAL_NAME")
	private String originalName;

	@Column(name = "SAVE_NAME")
	private String saveName;

	@Column(name = "SIZE")
	private int size;

	@Transient
	/** 연관된 게시물 */
	private BoardArticle article;

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
	 * @return Base64로 변환 된 첨부파일 실제 이름
	 */
	public String getOriginalNameEncode() {
		return StringUtilAd.encodeString(originalName);
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
	 * @return 웹루트를 기준으로 첨부파일 경로.(파일명 포함)
	 */
	public String getSavePath() {
		File basePath = new File(BoardService.SAVE_PATH, article.getBoardCode());
		File f = new File(basePath, saveName);
		return f.getPath();
	}

	/**
	 * @return Base64로 변환 된 첨부파일 경로
	 */
	public String getSavePathEncode() {
		return StringUtilAd.encodeString(getSavePath());
	}

	/**
	 * @return 파일 URL 경로
	 */
	public String getUrl() {
		return "/" + ATTACH_UPLOAD_URL + "/" + article.getBoardCode() + "/" + saveName;
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

	/**
	 * @param article
	 *            the article to set
	 */
	public void setArticle(BoardArticle article) {
		this.article = article;
	}

}
