package com.setvect.literatureboy.vo;

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
@Table(name = "TBZZ_MEMO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Memo {
	@Id
	@Column(name = "MEMO_NO")
	private int id;

	@Column(name = "TITLE")
	private String titile;

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
}
