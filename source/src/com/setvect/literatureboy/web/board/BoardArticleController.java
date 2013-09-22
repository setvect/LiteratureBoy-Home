package com.setvect.literatureboy.web.board;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.common.http.HttpUtil;
import com.setvect.common.jsp.URLParameter;
import com.setvect.common.util.Binder;
import com.setvect.common.util.FileUtil;
import com.setvect.common.util.GenericPage;
import com.setvect.common.util.StringEncrypt;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.ProjectConstant;
import com.setvect.literatureboy.boot.ApplicationException;
import com.setvect.literatureboy.service.board.BoardArticleSearch;
import com.setvect.literatureboy.service.board.BoardService;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;
import com.setvect.literatureboy.vo.board.BoardTrackback;
import com.setvect.literatureboy.vo.user.User;
import com.setvect.literatureboy.web.AccessChecker;
import com.setvect.literatureboy.web.CommonUtil;
import com.setvect.literatureboy.web.ConstraintWeb;

/**
 * 환경설정>운영자 관리 메뉴 컨트롤러
 */
@Controller
@Scope("prototype")
public class BoardArticleController {

	/** 트래백 경로 */
	private static final String TRACKBACK_PATH = "/servlet/tb/";

	private static final HashMap<JspPageKey, String> DEFAUlT_JSP;
	static {
		HashMap<JspPageKey, String> jsp = new HashMap<JspPageKey, String>();
		jsp.put(JspPageKey.LIST, "/app/board/board_article_list.jsp");
		jsp.put(JspPageKey.READ, "/app/board/board_article_read.jsp");
		jsp.put(JspPageKey.WRITE, "/app/board/board_article_create.jsp");
		jsp.put(JspPageKey.ENCODE, "/app/board/board_article_encode.jsp");
		DEFAUlT_JSP = jsp;
	}

	/**
	 * 서브 명령어 정의
	 */
	public static enum Mode {
		/** USER,AGEN,Menu 리스트 보기 */
		LIST_FORM, SEARCH_FORM, READ_FORM, CREATE_FORM, CREATE_ACTION, UPDATE_FORM, UPDATE_ACTION, REMOVE_ACTION,
		//
		COMMENT_CREATE_ACTION, COMMENT_REMOVE_ACTION,
		//
		TRACKBACK_REMOVE_ACTION
	}

	/**
	 * 컨트롤러 호출 전에 전달될 파라미터 정보에 대한 키
	 */
	public static enum Parameter {
		PAGE_PER_ITEM_COUNT
		// 한 페이지당 표시 항목 수
	}

	/**
	 * 뷰에 전달할 객체를 가르키는 키
	 */
	public static enum AttributeKey {
		MODE,
		/** 리스트 */
		LIST,
		/** 게시판 정보 */
		BOARD,
		//
		ARTICLE, COMMENT,
		/** 페이지 및 검색 정보 */
		PAGE_SEARCH,
		// 권한 정보를 제공
		AUTH_WRITE,
		// 트래백 주소 및 목록
		TRACK_ADDR, TRACK_LIST,
	}

	@Autowired
	private BoardService boardService;

	/** jsp 페이지 */
	private HashMap<JspPageKey, String> jspPage = DEFAUlT_JSP;

	/** 다중 게시물 검색 */
	private List<String> searchBoards;

	/**
	 * View에 보여질 jsp 페이지는 정보 해쉬 키
	 */
	public static enum JspPageKey {
		LIST, READ, WRITE, ENCODE
	}

	@RequestMapping("/board/article.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = (ModelAndView) request.getAttribute(ConstraintWeb.AttributeKey.MODEL_VIEW.name());
		// 전달될 뷰 모델없으면 기본 값으로
		if (mav == null) {
			mav = new ModelAndView(ConstraintWeb.LITERATUREBOY_LAYOUT);
		}

		String mode = request.getParameter("mode");

		Mode m;
		if (StringUtilAd.isEmpty(mode)) {
			m = Mode.LIST_FORM;
		}
		else {
			m = Mode.valueOf(mode);
		}
		// 한페이지당 표시 갯수 정하기
		BoardArticleSearch pageCondition = bindSearch(request);
		Integer countItem = (Integer) request.getAttribute(BoardArticleController.Parameter.PAGE_PER_ITEM_COUNT.name());
		if (countItem != null) {
			pageCondition.setPagePerItemCount(countItem);
		}

		// 여러게 게시판에 게시물 표시 하기 위함
		if (searchBoards != null) {
			pageCondition.setSearchCodes(searchBoards);
		}

		// pageCondition.setDeleteView(true);
		if (StringUtilAd.isEmpty(pageCondition.getSearchCode())) {
			throw new ApplicationException("not setting to 'searchCode'");
		}
		mav.addObject(AttributeKey.PAGE_SEARCH.name(), pageCondition);

		Board board = boardService.getBoard(pageCondition.getSearchCode());
		mav.addObject(AttributeKey.BOARD.name(), board);
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

			article.setAttach(getAttachFile(article));

			boolean encodePage = false;
			encodePage = isEncodePage(request, article);

			if (encodePage) {
				mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), jspPage.get(JspPageKey.ENCODE));
				mav.addObject(AttributeKey.MODE.name(), m);
			}
			else {
				List<BoardAttachFile> attach = getAttachFile(article);
				article.setAttach(attach);

				List<BoardComment> comments = boardService.listComment(articleSeq);
				mav.addObject(AttributeKey.COMMENT.name(), comments);
				mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), jspPage.get(JspPageKey.READ));

				mav.addObject(AttributeKey.TRACK_ADDR.name(), getTrackbackAddr(request, article));
				mav.addObject(AttributeKey.TRACK_LIST.name(), getTrackbackList(article));
			}
		}
		else if (m == Mode.CREATE_FORM) {
			User user = CommonUtil.getLoginSession(request);
			BoardArticle article = new BoardArticle();
			if (user != null) {
				article.setUserId(user.getUserId());
				article.setEmail(user.getEmail());
				article.setName(user.getName());
			}

			mav.addObject(AttributeKey.ARTICLE.name(), article);
			mav.addObject(AttributeKey.MODE.name(), Mode.CREATE_ACTION);
			mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), jspPage.get(JspPageKey.WRITE));
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
				mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), jspPage.get(JspPageKey.ENCODE));
				mav.addObject(AttributeKey.MODE.name(), m);
			}
			else {
				mav.addObject(BoardArticleController.AttributeKey.ARTICLE.name(), article);
				mav.addObject(AttributeKey.MODE.name(), Mode.UPDATE_ACTION);
				List<BoardAttachFile> attach = getAttachFile(article);
				article.setAttach(attach);
				mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), jspPage.get(JspPageKey.WRITE));
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
		else if (m == Mode.TRACKBACK_REMOVE_ACTION) {
			int relationSeq = Integer.parseInt(request.getParameter("relationSeq"));
			boardService.removeTrackback(relationSeq);
			mav.setViewName("redirect:" + getRedirectionUrl(request, pageCondition));
		}

		if (m == Mode.LIST_FORM) {
			GenericPage<BoardArticle> boardPagingList = boardService.getArticlePagingList(pageCondition);

			Collection<BoardArticle> list = boardPagingList.getList();
			for (BoardArticle article : list) {
				article.setAttach(getAttachFile(article));
			}

			mav.addObject(AttributeKey.LIST.name(), boardPagingList);
			mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), jspPage.get(JspPageKey.LIST));

			checkWrite(request, pageCondition);
		}

		return mav;
	}

	/**
	 * @param article
	 *            게시물 정보
	 * @return 해당 게시물에 등록된 트래백 목록
	 */
	private List<BoardTrackback> getTrackbackList(BoardArticle article) {
		List<BoardTrackback> listTrackback = boardService.listTrackback(article.getArticleSeq());
		return listTrackback;
	}

	/**
	 * @param request
	 * @param article
	 * @return 해당 게시물의 트래백 주소
	 */
	private String getTrackbackAddr(HttpServletRequest request, BoardArticle article) {
		return HttpUtil.getHomepageUrl(request) + TRACKBACK_PATH + article.getArticleSeq();
	}

	/**
	 * @param article
	 * @return
	 */
	private List<BoardAttachFile> getAttachFile(BoardArticle article) {
		List<BoardAttachFile> attach = boardService.listAttachFile(article.getArticleSeq());
		for (BoardAttachFile f : attach) {
			f.setArticle(article);
		}
		return attach;
	}

	/**
	 * 쓰기 권한이 있는지 체크함
	 * 
	 * @param request
	 * @param pageCondition
	 * @throws Exception
	 */
	private void checkWrite(HttpServletRequest request, BoardArticleSearch pageCondition) {
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
		String destDir = request.getSession().getServletContext().getRealPath(ProjectConstant.SAVE_PATH);

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

		URLParameter param = new URLParameter(request.getRequestURI(), "utf-8");
		Map<String, Object> searchParam = CommonUtil.getSearchMap(pageCondition);

		for (Entry<String, Object> item : searchParam.entrySet()) {
			if (item.getValue() == null) {
				continue;
			}
			param.put(item.getKey(), item.getValue().toString());
		}

		String pageParam = new ParamEncoder("articleList").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		param.put(pageParam, pageCondition.getCurrentPage());

		String mode = request.getParameter("mode");
		Mode m = Mode.valueOf(mode);
		// 코멘트, 트래백 관련 액션이면 읽기 페이지로 이동
		if (m == Mode.COMMENT_CREATE_ACTION || m == Mode.COMMENT_REMOVE_ACTION || m == Mode.TRACKBACK_REMOVE_ACTION) {
			param.put("mode", Mode.READ_FORM.name());
			param.put("articleSeq", request.getParameter("articleSeq"));
		}

		String url = param.getParam("&");
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

		// bind시 currentPage 파라미터가 있으면 display tag에 값을 무시 하기 때문에
		// display Tag에서 가져온 값을 우선적으로 대입(설명이 잘 안된다 ㅡㅡ);
		searchVO.setCurrentPage(currentPage);
		return searchVO;
	}

	/**
	 * @return the jspPage
	 */
	@SuppressWarnings("unchecked")
	public HashMap<JspPageKey, String> getJspPage() {
		return (HashMap<JspPageKey, String>) jspPage.clone();
	}

	/**
	 * @param jspPage
	 *            the jspPage to set
	 */
	public void setJspPage(HashMap<JspPageKey, String> jspPage) {
		this.jspPage = jspPage;
	}

	/**
	 * 다중 게시물을 검색하기 위함
	 */
	public void setSearchBoards(List<String> boards) {
		searchBoards = boards;
	}

}