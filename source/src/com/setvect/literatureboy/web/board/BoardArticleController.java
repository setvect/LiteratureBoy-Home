package com.setvect.literatureboy.web.board;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.common.http.UrlParameter;
import com.setvect.common.util.Binder;
import com.setvect.common.util.FileUtil;
import com.setvect.common.util.GenericPage;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.boot.ApplicationException;
import com.setvect.literatureboy.config.EnvirmentProperty;
import com.setvect.literatureboy.service.board.BoardArticleSearch;
import com.setvect.literatureboy.service.board.BoardService;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;
import com.setvect.literatureboy.web.CommonUtil;
import com.setvect.literatureboy.web.ConstraintWeb;

/**
 * ȯ�漳��>��� ���� �޴� ��Ʈ�ѷ�
 */
@Controller
public class BoardArticleController {
	/**
	 * ���� ��ɾ� ����
	 */
	public static enum Mode {
		/** USER,AGEN,Menu ����Ʈ ���� */
		LIST_FORM, SEARCH_FORM, READ_FORM, CREATE_FORM, CREATE_ACTION, UPDATE_FORM, UPDATE_ACTION, DELETE_ACTION,
		//
		COMMENT_CREATE_ACTION, COMMENT_DELETE_ACTION
	}

	/**
	 * �信 ������ ��ü�� ����Ű�� Ű
	 */
	public static enum AttributeKey {
		MODE,
		/** ����Ʈ */
		LIST,
		//
		ARTICLE,
		/** ������ �� �˻� ���� */
		PAGE_SEARCH
	}

	@Resource
	private BoardService boardService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ipms.sfj.pt.setup.user.IUserController#userTranscation(javax.servlet
	 * .http.HttpServletRequest, com.ipms.sfj.pt.setup.user.User)
	 */
	@RequestMapping("/board/article.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		response.setCharacterEncoding(request.getCharacterEncoding());
		String mode = request.getParameter("mode");

		Mode m;
		if (StringUtilAd.isEmpty(mode)) {
			m = Mode.LIST_FORM;
		}
		else {
			m = Mode.valueOf(mode);
		}

		BoardArticleSearch pageCondition = bindSearch(request);
		if (StringUtilAd.isEmpty(pageCondition.getSearchCode())) {
			throw new ApplicationException("not setting to 'searchCode'");
		}
		mav.addObject(AttributeKey.PAGE_SEARCH.name(), pageCondition);

		mav.setViewName(ConstraintWeb.INDEX_VIEW);
		if (m == Mode.SEARCH_FORM) {
			String type = request.getParameter("searchType");
			String word = request.getParameter("searchWord");
			if (type.equals("title")) {
				pageCondition.setSearchTitle(word);
			}
			else if (type.equals("name")) {
				pageCondition.setSearchName(word);
			}
			else if (type.equals("content")) {
				pageCondition.setSearchContent(word);
			}
			m = Mode.LIST_FORM;
		}
		else if (m == Mode.READ_FORM) {
			int articleSeq = Integer.parseInt(request.getParameter("articleSeq"));
			BoardArticle b = boardService.getArticle(articleSeq);
			mav.addObject(BoardArticleController.AttributeKey.ARTICLE.name(), b);
			mav.addObject(ConstraintWeb.INCLUDE_PAGE, "/app/board/board_article_read.jsp");
		}
		else if (m == Mode.CREATE_FORM) {
			mav.addObject(AttributeKey.MODE.name(), Mode.CREATE_ACTION);
			mav.addObject(ConstraintWeb.INCLUDE_PAGE, "/app/board/board_article_create.jsp");
		}
		else if (m == Mode.CREATE_ACTION) {
			BoardArticle article = new BoardArticle();
			Binder.bind(request, article);
			article.setDepthLevel(1);
			article.setRegDate(new Date());
			article.setIp(request.getRemoteAddr());
			boardService.createArticle(article);

			saveAttachFile(request, article);

			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
			return mav;
		}
		else if (m == Mode.UPDATE_FORM) {
			int articleSeq = Integer.parseInt(request.getParameter("articleSeq"));
			BoardArticle b = boardService.getArticle(articleSeq);
			mav.addObject(BoardArticleController.AttributeKey.ARTICLE.name(), b);
			mav.addObject(AttributeKey.MODE.name(), Mode.UPDATE_ACTION);
			mav.addObject(ConstraintWeb.INCLUDE_PAGE, "/app/board/board_article_create.jsp");
		}
		else if (m == Mode.UPDATE_ACTION) {
			int articleSeq = Integer.parseInt(request.getParameter("articleSeq"));
			BoardArticle b = boardService.getArticle(articleSeq);
			Binder.bind(request, b);
			boardService.updateArticle(b);
			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
			return mav;
		}
		else if (m == Mode.DELETE_ACTION) {
			int articleSeq = Integer.parseInt(request.getParameter("articleSeq"));
			BoardArticle b = boardService.getArticle(articleSeq);
			b.setDeleteF(true);
			boardService.updateArticle(b);
			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
			return mav;
		}
		else if (m == Mode.COMMENT_CREATE_ACTION) {
			BoardComment comment = new BoardComment();
			Binder.bind(request, comment);
			boardService.updateComment(comment);
			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
			return mav;
		}
		else if (m == Mode.COMMENT_DELETE_ACTION) {
			int commentSeq = Integer.parseInt(request.getParameter("commentSeq"));
			boardService.removeComment(commentSeq);
			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
		}

		if (m == Mode.LIST_FORM) {
			GenericPage<BoardArticle> boardPagingList = boardService.getArticlePagingList(pageCondition);
			mav.addObject(AttributeKey.LIST.name(), boardPagingList);
			mav.addObject(ConstraintWeb.INCLUDE_PAGE, "/app/board/board_article_list.jsp");

			request.setAttribute("pageList", boardPagingList);
		}

		return mav;
	}

	/**
	 * ÷������ ����
	 * 
	 * @param request
	 * @param article
	 *            ���� ��
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void saveAttachFile(HttpServletRequest request, BoardArticle article) throws IOException,
			FileNotFoundException {
		String destDir = request.getSession().getServletContext()
				.getRealPath(EnvirmentProperty.getString("com.setvect.literatureboy.board.file_upload_dir"));

		File saveDir = new File(destDir, article.getBoardCode());
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}

		MultipartFile[] attachFiles = article.getAttachFile();

		for (MultipartFile file : attachFiles) {
			if (StringUtilAd.isEmpty(file.getOriginalFilename())) {
				continue;
			}
			String fileName = "upload" + FileUtil.getExt(file.getOriginalFilename());

			File destination = File.createTempFile("file", fileName, saveDir);
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destination));
			BoardAttachFile attach = new BoardAttachFile();
			attach.setArticleSeq(article.getArticleSeq());
			attach.setOriginalName(file.getOriginalFilename());
			attach.setSaveName(destination.getName());
			attach.setSize((int) file.getSize());
			boardService.createAttachFile(attach);
		}
	}

	/**
	 * ���ΰ�ħ�� ���� �� ���ε� ������ �ϱ����� ������ �������� redirection �ϱ� ���� �ּҸ� ����
	 * 
	 * @param request
	 * @param pageCondition
	 * @return redirection �ּ�
	 * @throws Exception
	 */
	private String getRedirectionUrl(HttpServletRequest request, BoardArticleSearch pageCondition) throws Exception {

		UrlParameter param = new UrlParameter();
		Map<String, Object> searchParam = CommonUtil.getSearchMap(pageCondition);
		param.putAll(searchParam);

		String pageParam = new ParamEncoder("boardList").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		param.put(pageParam, pageCondition.getCurrentPage());

		String mode = request.getParameter("mode");
		Mode m = Mode.valueOf(mode);
		// �ڸ�Ʈ ���� �׼��̸� �б� �������� �̵�
		if (m == Mode.COMMENT_CREATE_ACTION || m == Mode.COMMENT_DELETE_ACTION) {
			param.put("mode", Mode.READ_FORM);
			param.put("articleSeq", request.getParameter("articleSeq"));
		}

		String url = request.getRequestURI() + "?" + param.getParameter();
		return url;
	}

	/**
	 * request parameter���� ����¡ �� �˻� ������ ���� ��
	 * 
	 * @param request
	 * @return ����¡ �� �˻� ����
	 * @throws ServletRequestBindingException
	 */
	private BoardArticleSearch bindSearch(HttpServletRequest request) throws ServletRequestBindingException {
		int currentPage = CommonUtil.getCurrentPage(request, "boardList");
		BoardArticleSearch searchVO = new BoardArticleSearch(currentPage);
		Binder.bind(request, searchVO);
		return searchVO;
	}
}