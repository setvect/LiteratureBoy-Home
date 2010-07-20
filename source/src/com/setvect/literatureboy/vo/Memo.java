package com.setvect.literatureboy.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 테스트 임시
 * 
 * @version $Id$
 */
@Entity
@Table(name = "TBAA_AGENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Memo {
	@Id
	@Column(name = "AGENT_ID")
	private int id;

	@Column(name = "TITLE")
	private String titile;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "REG_DATE")
	private Date regDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitile() {
		return titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
