package com.setvect.literatureboy.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setvect.literatureboy.db.CommentDao;
import com.setvect.literatureboy.vo.Comment;

/**
 * 코멘트 관리
 */
@Service("CommentService")
public class CommentService {

	@Autowired
	private CommentDao commentDao;

	public Comment getComment(int commentSeq) {
		return commentDao.getComment(commentSeq);
	}

	public List<Comment> listComment(CommentModule moduleName, String moduleItemId) {
		return commentDao.listComment(moduleName, moduleItemId);
	}

	public List<Comment> listComment(CommentModule moduleName, int moduleItemId) {
		return commentDao.listComment(moduleName, String.valueOf(moduleItemId));
	}

	public void createComment(Comment comment) {
		commentDao.createComment(comment);
	}

	public void updateComment(Comment comment) {
		commentDao.updateComment(comment);
	}

	public void removeComment(int commentSeq) {
		commentDao.removeComment(commentSeq);
	}

}
