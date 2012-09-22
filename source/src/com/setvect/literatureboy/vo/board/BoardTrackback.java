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
 * 첨부파일
 * 
 * @version $Id$
 */
@Entity
@Table(name = "TBBE_BOARD_TRACKBACK")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoardTrackback {

	/** 키 */
	@Id
	@Column(name = "RELATION_SEQ")
	@GenericGenerator(name = "hibernate-increment", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate-increment")
	private int relationSeq;

	/** 게시물 번호 */
	@Column(name = "ARTICLE_SEQ")
	private int articleSeq;

	/** 게시물 제목 */
	@Column(name = "TITLE")
	private String title;

	/** 본문 요약 */
	@Column(name = "EXCERPT")
	private String excerpt;

	/** url */
	@Column(name = "URL")
	private String url;

	/** 블로그 이름 */
	@Column(name = "BLOG_NAME")
	private String blogName;

	/** 등록일 */
	@Column(name = "REG_DATE")
	private Date regDate;

	// ---- getter, setter
	/**
	 * @return Returns the articleSeq.
	 */
	public int getArticleSeq() {
		return articleSeq;
	}

	/**
	 * @param articleSeq
	 *            The articleSeq to set.
	 */
	public void setArticleSeq(int articleSeq) {
		this.articleSeq = articleSeq;
	}

	/**
	 * @return Returns the relationSeq.
	 */
	public int getRelationSeq() {
		return relationSeq;
	}

	/**
	 * @param relationSeq
	 *            The relationSeq to set.
	 */
	public void setRelationSeq(int relationSeq) {
		this.relationSeq = relationSeq;
	}

	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return Returns the excerpt.
	 */
	public String getExcerpt() {
		return excerpt;
	}

	/**
	 * @param excerpt
	 *            The excerpt to set.
	 */
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return Returns the blogName.
	 */
	public String getBlogName() {
		return blogName;
	}

	/**
	 * @param blogName
	 *            The blogName to set.
	 */
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}

	/**
	 * @return Returns the regDate.
	 */
	public Date getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate
	 *            The regDate to set.
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
