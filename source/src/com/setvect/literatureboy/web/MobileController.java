package com.setvect.literatureboy.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.common.jsp.URLParameter;
import com.setvect.literatureboy.config.EnvirmentProperty;
import com.setvect.literatureboy.service.board.BoardService;
import com.setvect.literatureboy.service.comment.CommentService;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.web.board.BoardArticleController;
import com.setvect.literatureboy.web.board.BoardArticleController.JspPageKey;
import com.setvect.literatureboy.web.comment.CommentController;
import com.setvect.literatureboy.web.etc.EmailGetController;

/**
 * 모바일 페이지
 */
@Controller
@Scope("prototype")
public class MobileController {
	@Autowired
	private BoardService boardService;

	@Autowired
	private BoardArticleController boardArticleController;

	@Autowired
	private EmailGetController emailGetController;

	@Autowired
	private CommentController commentController;

	/**
	 * 뷰에 전달할 객체를 가르키는 키
	 */
	public static enum AttributeKey {
		STATUS, // 상태 정보
		LINK_PARAMETER, // 현제 페이지 링크 주소와 파라미터 정보
		BOARD_MAP, // 게시판 정보 Map
	}

	public static enum Menu {
		MAIN, // 메인 메뉴
		ALL, // 전체 보기
		THINK, // 순간의 생각들
	}

	/** 전체 보기에 포함될 게시판 코드 */
	public static final List<String> ALL_VIEW_BOARD_LIST;

	/** 전체 게시판 */
	private final List<Board> ALL_BOARD = new ArrayList<Board>();

	public final Map<String, Board> ALL_BOARD_MAP = new HashMap<String, Board>();
	static {
		String[] boardList = EnvirmentProperty.getStringArray("com.setvect.literatureboy.board.all_view");
		ALL_VIEW_BOARD_LIST = Arrays.asList(boardList);
	}

	@RequestMapping("/m/*.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (ALL_BOARD.isEmpty()) {
			loadViewBoardList();
		}

		String requestURI = request.getRequestURI();
		int posStart = requestURI.lastIndexOf("/") + 1;
		int posEnd = requestURI.lastIndexOf(".do");
		String pageName = requestURI.substring(posStart, posEnd);

		// 일반 게시판 UI
		if (pageName.equals("bd") || pageName.equals("bdAll")) {
			// 전체 게시물
			if (pageName.equals("bdAll")) {
				boardArticleController.setSearchBoards(ALL_VIEW_BOARD_LIST);
			}

			request.setAttribute(BoardArticleController.Parameter.PAGE_PER_ITEM_COUNT.name(), 10);
			HashMap<JspPageKey, String> jsp = boardArticleController.getJspPage();
			jsp.put(BoardArticleController.JspPageKey.LIST, "/m/app/board/board_article_list.jsp");
			jsp.put(BoardArticleController.JspPageKey.READ, "/m/app/board/board_article_read.jsp");
			boardArticleController.setJspPage(jsp);

			ModelAndView mav = new ModelAndView(ConstraintWeb.MOBILE_LAYOUT);
			request.setAttribute(ConstraintWeb.AttributeKey.MODEL_VIEW.name(), mav);

			mav = boardArticleController.process(request, response);
			Board board = (Board) mav.getModel().get(BoardArticleController.AttributeKey.BOARD.name());
			MobilePageStatus ps;
			if (pageName.equals("bdAll")) {
				ps = new MobilePageStatus("전체보기", Menu.ALL);
			}
			else {
				ps = new MobilePageStatus(board.getName(), Menu.MAIN);
			}
			ps.setUrlParam(getUrlParam(request));
			mav.addObject(AttributeKey.STATUS.name(), ps);
			mav.addObject(AttributeKey.BOARD_MAP.name(), ALL_BOARD_MAP);
			return mav;
		}
		else if (pageName.equals("think")) {
			// 순간의 생각들
			ModelAndView mav = commentController.process(request, response);
			mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/m/app/comment/comment.jsp");
			MobilePageStatus ps = new MobilePageStatus("순간의 생각", Menu.THINK);
			mav.addObject(AttributeKey.STATUS.name(), ps);
			mav.setViewName(ConstraintWeb.MOBILE_LAYOUT);
			return mav;
		}
		// 이메일 주소 알기
		else if (pageName.equals("emailget")) {
			ModelAndView mav = emailGetController.process(request, response);

			// 리턴 받을 뷰 이름을 조작해 모바일 페이지에 로딩 되게 함.
			// 웹 페이지 구성의 매우 의존적이기 때문에 별로 좋은 방법은 아님.
			String viewName = mav.getViewName();

			MobilePageStatus ps = new MobilePageStatus("문학소년 이메일 알기", Menu.MAIN);
			mav.addObject(AttributeKey.STATUS.name(), ps);
			mav.setViewName(ConstraintWeb.MOBILE_LAYOUT);
			mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/m/" + viewName + ".jsp");
			return mav;
		}
		else {
			// 메인화면
			ModelAndView mav = new ModelAndView(ConstraintWeb.MOBILE_LAYOUT);
			MobilePageStatus ps = new MobilePageStatus("Literature Boy", Menu.MAIN);
			mav.addObject(AttributeKey.STATUS.name(), ps);
			mav.addObject(ConstraintWeb.AttributeKey.BOARD_ITEMS.name(), ALL_BOARD);
			mav.addObject(ConstraintWeb.AttributeKey.INCLUDE_PAGE.name(), "/m/main.jsp");
			return mav;
		}
	}

	/**
	 * 현재 주소와 파마리터 값을 이용해서 링크 값을 수정 하기 위함
	 * 
	 * @urlParam request
	 * @return 현재 주소와 파라미터 정보를 그대로 셋팅
	 */
	private URLParameter getUrlParam(HttpServletRequest request) {
		String current = (String) request.getAttribute(ConstraintWeb.AttributeKey.SERVLET_URL.name());
		URLParameter urlParam = new URLParameter(current, request.getCharacterEncoding());
		urlParam.put(request);

		return urlParam;
	}

	/**
	 * 표시게시판 전체 로드
	 */
	private void loadViewBoardList() {
		ALL_BOARD.clear();
		ALL_BOARD_MAP.clear();
		for (String bd : ALL_VIEW_BOARD_LIST) {
			Board a = boardService.getBoard(bd);
			ALL_BOARD.add(a);
			ALL_BOARD_MAP.put(a.getBoardCode(), a);
		}
	}

	/**
	 * 모바일 페이지 상태 값
	 */
	public static class MobilePageStatus {
		/** 페이지 상단 제목 */
		private String title;

		/** 선택된 메뉴 */
		private Menu menu;

		/** 현재 페이지 주소와, 파라미터 */
		private URLParameter urlParam;

		/**
		 * @urlParam t 페이지 제목
		 * @urlParam m 선택 메뉴
		 */
		public MobilePageStatus(String t, Menu m) {
			title = t;
			menu = m;
		}

		/**
		 * @return 페이지 상단 제목
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @urlParam title 페이지 상단 제목
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * @return 선택된 메뉴
		 */
		public Menu getMenu() {
			return menu;
		}

		/**
		 * @urlParam menu 선택된 메뉴
		 */
		public void setMenu(Menu menu) {
			this.menu = menu;
		}

		/**
		 * @return the urlParam
		 */
		public URLParameter getUrlParam() {
			return urlParam;
		}

		/**
		 * @urlParam urlParam the urlParam to set
		 */
		public void setUrlParam(URLParameter param) {
			this.urlParam = param;
		}

	}
}
