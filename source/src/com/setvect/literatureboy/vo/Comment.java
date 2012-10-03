package com.setvect.literatureboy.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.boot.EnvirmentInit;
import com.setvect.literatureboy.service.comment.CommentModule;
import com.setvect.literatureboy.service.user.UserService;
import com.setvect.literatureboy.util.DateDiff;
import com.setvect.literatureboy.vo.user.User;

/**
 * 첨부파일
 */
@Entity
@Table(name = "TBGA_COMMENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Comment {

	/** 일련번호 */
	@Id
	@Column(name = "COMMENT_SEQ")
	@GenericGenerator(name = "hibernate-increment", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate-increment")
	private int commentSeq;

	/** 모듈이름 */
	@Column(name = "MODULE_NAME")
	@Enumerated(EnumType.STRING)
	private CommentModule moduleName;

	/** 모듈 아이디 */
	@Column(name = "MODULE_ID")
	private String moduleId;

	/** 아이디 */
	@Column(name = "USER_ID")
	private String userId;

	/** 내용 */
	@Column(name = "CONTENT")
	private String content;

	/** 등록일 */
	@Column(name = "REG_DATE")
	private Date regDate;

	public int getCommentSeq() {
		return commentSeq;
	}

	public void setCommentSeq(int commentSeq) {
		this.commentSeq = commentSeq;
	}

	public CommentModule getModuleName() {
		return moduleName;
	}

	public void setModuleName(CommentModule moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getRegDateDiff() {
		return DateDiff.diff(new Date(), regDate);
	}

	/**
	 * @return 등록자
	 */
	public User getUser() {
		if (StringUtilAd.isEmpty(userId)) {
			return null;
		}
		UserService service = (UserService) EnvirmentInit.getSpringContext().getBean("UserService");
		return service.getUser(userId);
	}
}
