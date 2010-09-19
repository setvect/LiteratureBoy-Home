package com.setvect.literatureboy.web.board;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.setvect.common.util.StringEncrypt;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.boot.ApplicationException;
import com.setvect.literatureboy.config.EnvirmentProperty;
import com.setvect.literatureboy.service.board.BoardArticleSearch;
import com.setvect.literatureboy.service.board.BoardService;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;
import com.setvect.literatureboy.web.AccessChecker;
import com.setvect.literatureboy.web.CommonUtil;
import com.setvect.literatureboy.web.ConstraintWeb;

/**
 * 환경설정>운영자 관리 메뉴 컨트롤러
 */
@Controller
public class BoardArticleController {
	/** 웹 루트를 기준으로 저장 경로 */
	private static final String SAVE_PATH = EnvirmentProperty
			.getString("com.setvect.literatureboy.board.file_upload_dir");

	/**
	 * 서브 명령어 정의
	 */
	public static enum Mode {
		/** USER,AGEN,Menu 리스트 보기 */
		LIST_FORM, SEARCH_FORM, READ_FORM, CREATE_FORM, CREATE_ACTION, UPDATE_FORM, UPDATE_ACTION, REMOVE_ACTION,
		//
		COMMENT_CREATE_ACTION, COMMENT_REMOVE_ACTION
	}

	/**
	 * 뷰에 전달할 객체를 가르키는 키
	 */
	public static enum AttributeKey {
		MODE,
		/** 리스트 */
		LIST,
		/** 게시물 정보 */
		BOARD,
		//
		ARTICLE, ATTACH, COMMENT,
		/** 페이지 및 검색 정보 */
		PAGE_SEARCH,
		// 권한 정보를 제공
		AUTH_WRITE
	}

	@Resource
	private BoardService boardService;

	@RequestMapping("/board/article.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		String mode = request.getParameter("mode");

		Mode m;
		if (StringUtilAd.isEmpty(mode)) {
			m = Mode.LIST_FORM;
		}
		else {
			m = Mode.valueOf(mode);
		}

		BoardArticleSearch pageCondition = bindSearch(request);
		// pageCondition.setDeleteView(true);
		if (StringUtilAd.isEmpty(pageCondition.getSearchCode())) {
			throw new ApplicationException("not setting to 'searchCode'");
		}
		mav.addObject(AttributeKey.PAGE_SEARCH.name(), pageCondition);

		Board board = boardService.getBoard(pageCondition.getSearchCode());
		mav.addObject(AttributeKey.BOARD.name(), board);

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
			boardService.updateArticleIncrementHit(articleSeq);
			BoardArticle article = boardService.getArticle(articleSeq);
			mav.addObject(AttributeKey.ARTICLE.name(), article);

			boolean encodePage = false;
			encodePage = isEncodePage(request, article);

			if (encodePage) {
				mav.addObject(ConstraintWeb.Attribute.INCLUDE_PAGE.name(), "/app/board/board_article_encode.jsp");
				mav.addObject(AttributeKey.MODE.name(), m);
			}
			else {
				List<BoardAttachFile> attach = boardService.listAttachFile(articleSeq);
				for (BoardAttachFile f : attach) {
					f.setBasePath(new File(SAVE_PATH, article.getBoardCode()));
				}
				mav.addObject(AttributeKey.ATTACH.name(), attach);

				List<BoardComment> comments = boardService.listComment(articleSeq);
				mav.addObject(AttributeKey.COMMENT.name(), comments);

				mav.addObject(ConstraintWeb.Attribute.INCLUDE_PAGE.name(), "/app/board/board_article_read.jsp");
			}
		}
		else if (m == Mode.CREATE_FORM) {
			mav.addObject(AttributeKey.MODE.name(), Mode.CREATE_ACTION);
			mav.addObject(ConstraintWeb.Attribute.INCLUDE_PAGE.name(), "/app/board/board_article_create.jsp");
		}
		else if (m == Mode.CREATE_ACTION) {
			BoardArticle article = new BoardArticle();
			Binder.bind(request, article);
			processEncrypt(request, article);

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
			BoardArticle article = boardService.getArticle(articleSeq);
			boolean encodePage = isEncodePage(request, article);
			
			mav.addObject(AttributeKey.ARTICLE.name(), article);
			if (encodePage) {
				mav.addObject(ConstraintWeb.Attribute.INCLUDE_PAGE.name(), "/app/board/board_article_encode.jsp");
				mav.addObject(AttributeKey.MODE.name(), m);
			}
			else {
				mav.addObject(BoardArticleController.AttributeKey.ARTICLE.name(), article);
				mav.addObject(AttributeKey.MODE.name(), Mode.UPDATE_ACTION);
				mav.addObject(AttributeKey.ATTACH.name(), boardService.listAttachFile(articleSeq));
				mav.addObject(ConstraintWeb.Attribute.INCLUDE_PAGE.name(), "/app/board/board_article_create.jsp");
			}
		}
		else if (m == Mode.UPDATE_ACTION) {
			int articleSeq = Integer.parseInt(request.getParameter("articleSeq"));
			BoardArticle article = boardService.getArticle(articleSeq);
			Binder.bind(request, article);
			processEncrypt(request, article);
			boardService.updateArticle(article);
			saveAttachFile(request, article);

			String[] deleteAttach = request.getParameterValues("deleteAttach");
			if (deleteAttach != null) {
				for (String attach : deleteAttach) {
					boardService.removeAttachFile(Integer.parseInt(attach));
				}
			}

			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
			return mav;
		}
		else if (m == Mode.REMOVE_ACTION) {
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
			comment.setIp(request.getRemoteAddr());
			comment.setRegDate(new Date());
			boardService.createComment(comment);
			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
			return mav;
		}
		else if (m == Mode.COMMENT_REMOVE_ACTION) {
			int commentSeq = Integer.parseInt(request.getParameter("commentSeq"));
			boardService.removeComment(commentSeq);
			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
		}

		if (m == Mode.LIST_FORM) {
			GenericPage<BoardArticle> boardPagingList = boardService.getArticlePagingList(pageCondition);
			mav.addObject(AttributeKey.LIST.name(), boardPagingList);
			mav.addObject(ConstraintWeb.Attribute.INCLUDE_PAGE.name(), "/app/board/board_article_list.jsp");

			checkWrite(request, pageCondition);
		}

		return mav;
	}

	/**
	 * 쓰기 권한이 있는지 체크함 
	 * @param request
	 * @param pageCondition
	 * @throws Exception
	 */
	private void checkWrite(HttpServletRequest request, BoardArticleSearch pageCondition)
			throws Exception {
		Map<String, String> param = new HashMap<String, String>();
		param.put("searchCode", pageCondition.getSearchCode());
		param.put("mode", Mode.CREATE_FORM.name());
		boolean writerable = AccessChecker.isAccessToUrl(request, param);
		request.setAttribute(AttributeKey.AUTH_WRITE.name(), writerable);
	}

	/**
	 * @param request
	 * @param article
	 * @return
	 */
	private boolean isEncodePage(HttpServletRequest request, BoardArticle article) {
		if (article.isEncodeF()) {
			String encode = request.getParameter("encode");
			if (encode == null) {
				return true;
			}
			else {
				article.setContent(StringEncrypt.decodeJ(article.getContent(), encode));
			}
		}
		return false;
	}

	/**
	 * 암호화 글 처리
	 * 
	 * @param request
	 * @param article
	 */
	private void processEncrypt(HttpServletRequest request, BoardArticle article) {
		String encode = request.getParameter("encode");

		// 암호화 글
		if (!StringUtilAd.isEmpty(encode)) {
			article.setContent(StringEncrypt.encodeJ(article.getContent(), encode));
			article.setEncodeF(true);
		}
	}

	/**
	 * 첨부파일 저장
	 * 
	 * @param request
	 * @param article
	 *            관계 글
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void saveAttachFile(HttpServletRequest request, BoardArticle article) throws IOException,
			FileNotFoundException {
		String destDir = request.getSession().getServletContext().getRealPath(SAVE_PATH);

		File saveDir = new File(destDir, article.getBoardCode());
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}

		MultipartFile[] attachFiles = article.getAttachFile();
		if (attachFiles == null) {
			return;
		}
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
	 * 새로고침을 통한 재 업로드 방지를 하기위해 정해진 페이지로 redirection 하기 위한 주소를 제공
	 * 
	 * @param request
	 * @param pageCondition
	 * @return redirection 주소
	 * @throws Exception
	 */
	private String getRedirectionUrl(HttpServletRequest request, BoardArticleSearch pageCondition) throws Exception {

		UrlParameter param = new UrlParameter();
		Map<String, Object> searchParam = CommonUtil.getSearchMap(pageCondition);
		param.putAll(searchParam);

		String pageParam = new ParamEncoder("articleList").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		param.put(pageParam, pageCondition.getCurrentPage());

		String mode = request.getParameter("mode");
		Mode m = Mode.valueOf(mode);
		// 코멘트 관련 액션이면 읽기 페이지로 이동
		if (m == Mode.COMMENT_CREATE_ACTION || m == Mode.COMMENT_REMOVE_ACTION) {
			param.put("mode", Mode.READ_FORM);
			param.put("articleSeq", request.getParameter("articleSeq"));
		}

		String url = request.getRequestURI() + "?" + param.getParameter();
		return url;
	}

	/**
	 * request parameter에서 페이징 및 검색 정보를 추출 함
	 * 
	 * @param request
	 * @return 페이징 및 검색 정보
	 * @throws ServletRequestBindingException
	 */
	private BoardArticleSearch bindSearch(HttpServletRequest request) throws ServletRequestBindingException {
		int currentPage = CommonUtil.getCurrentPage(request, "articleList");
		BoardArticleSearch searchVO = new BoardArticleSearch(currentPage);
		Binder.bind(request, searchVO);
		return searchVO;
	}
}