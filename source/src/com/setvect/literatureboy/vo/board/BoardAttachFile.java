package com.setvect.literatureboy.vo.board;

import javax.persistence.Entity;
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
	private int fileSeq;
	private int articleSeq;
	private String originalName;
	private String saveName;
	private int size;
}
