package com.setvect.literatureboy.web.comment;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.common.util.GenericPage;
import com.setvect.literatureboy.service.comment.CommentModule;
import com.setvect.literatureboy.service.comment.CommentSearch;
import com.setvect.literatureboy.service.comment.CommentService;
import com.setvect.literatureboy.vo.Comment;

/**
 * 이메일 주소 알아 내기
 * 
 * @version $Id$
 */
@Controller
public class CommentController {

	/**
	 * 서브 명령어 정의
	 */
	public static enum Mode {
		LIST_FORM,
	}

	/**
	 * 뷰에 전달할 객체를 가르키는 키
	 */
	public static enum AttributeKey {
		LIST
	}

	@Autowired
	private CommentService commentService;

	/**
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/comment.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView();

		CommentModule moduleName = CommentModule.valueOf(request.getParameter("moduleName"));
		String moduleItemId = request.getParameter("moduleId");
		CommentSearch pageCondition = new CommentSearch(1, moduleName, moduleItemId);
		GenericPage<Comment> page = commentService.getCommentPagingList(pageCondition);
		List<Comment> list = page.getList();
		request.setAttribute(AttributeKey.LIST.name(), list);
		mav.setViewName("/app/comment/comment_list");

		return mav;
	}
}