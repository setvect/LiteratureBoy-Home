package com.setvect.literatureboy.db;

import java.util.List;

import com.setvect.literatureboy.service.common.CommentModule;
import com.setvect.literatureboy.vo.Comment;

/**
 * 코멘트
 * 
 * @version $Id$
 */
public interface CommentDao {
	/**
	 * @param commentSeq
	 *            일련번호
	 * @return 코멘트
	 */
	public Comment getComment(int commentSeq);

	/**
	 * @param moduleName
	 *            모듈 이름
	 * @param moduleItemId
	 *            모듈 아이디
	 * @return 코멘트 목록
	 */
	public List<Comment> listComment(CommentModule moduleName, String moduleItemId);

	/**
	 * @param comment
	 *            코멘트 저장
	 */
	public void createComment(Comment comment);

	/**
	 * @param comment
	 *            코멘트 수정
	 */
	public void updateComment(Comment comment);

	/**
	 * @param commentSeq
	 *            일련번호
	 */
	public void removeComment(int commentSeq);

}
