package com.setvect.literatureboy.service.common;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Controller;

import com.setvect.literatureboy.boot.EnvirmentInit;
import com.setvect.literatureboy.vo.Comment;
import com.setvect.literatureboy.vo.user.User;
import com.setvect.literatureboy.web.CommonUtil;

/**
 * 프로젝트 목록 제공
 * 
 * @version $Id$
 */
@Controller
public class CommentDwrService {
	/**
	 * 주의: Spring configuration이 초기화 된 후에 해당 클래스가 접근 되어야 초기화를 할 수 있음
	 */
	private CommentService commentService = (CommentService) EnvirmentInit.getSpringContext().getBean("CommentService");

	/**
	 * @param moduleName
	 *            모듈 이름
	 * @param moduleItemId
	 *            모듈 아이디
	 * @return 코멘트 목록
	 */
	public Collection<Comment> getCommentList(CommentModule moduleName, String moduleItemId) {
		Collection<Comment> comments = commentService.listComment(moduleName, moduleItemId);
		return comments;
	}

	public void createComment(Comment comment) {
		User loginUser = getUser();
		comment.setUserId(loginUser.getUserId());
		comment.setRegDate(new Date());

		commentService.createComment(comment);
	}

	public void updateComment(Comment comment) {
		commentService.updateComment(comment);
	}

	public void removeComment(int commentSeq) {
		User loginUser = getUser();
		Comment comment = commentService.getComment(commentSeq);
		if (comment.getUserId().equals(loginUser.getUserId())) {
			commentService.removeComment(commentSeq);
		}
		else {
			throw new RuntimeException("권한이 없습니다. (작성자:" + comment.getUserId() + " , 삭제자:" + loginUser.getUserId()
					+ " )");
		}
	}

	private User getUser() {
		WebContext wctx = WebContextFactory.get();
		HttpServletRequest request = wctx.getHttpServletRequest();
		User loginUser = CommonUtil.getLoginSession(request);
		return loginUser;
	}
}