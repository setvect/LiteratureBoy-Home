package com.setvect.literatureboy.web.board;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.common.util.Binder;
import com.setvect.common.util.GenericPage;
import com.setvect.common.util.PagingCondition;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.service.board.BoardService;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.web.ConstraintWeb;

/**
 * 환경설정>운영자 관리 메뉴 컨트롤러
 */
@Controller
public class BoardManagerController {
	/**
	 * 서브 명령어 정의
	 */
	public static enum Mode {
		/** USER,AGEN,Menu 리스트 보기 */
		LIST_FORM, READ_FORM, CREATE_FORM, UPDATE_FORM, CREATE_ACTION, UPDATE_ACTION, DELETE_ACTION
	}

	/**
	 * 뷰에 전달할 객체를 가르키는 키
	 */
	public static enum AttributeKey {
		/** 리스트 */
		BOARD_LIST,
		//
		MODE, BOARD_ITEM,
		/** 페이지 및 검색 정보 */
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
	@RequestMapping("/board/manager.do")
	public ModelAndView userTranscation(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

		PagingCondition pageCondition = bindSearch(request);
		mav.addObject(AttributeKey.PAGE_SEARCH.name(), pageCondition);

		mav.setViewName(ConstraintWeb.INDEX_VIEW);
		if(m == Mode.READ_FORM){
			String code = request.getParameter("boardCode");
			Board b = boardService.getBoard(code);
			mav.addObject(BoardManagerController.AttributeKey.BOARD_ITEM.name(), b);
			mav.addObject(ConstraintWeb.INCLUDE_PAGE, "/app/board/manager/board_manager_read.jsp");
		}
		else if (m == Mode.CREATE_FORM) {
			mav.addObject(AttributeKey.MODE.name(), Mode.CREATE_ACTION);
			mav.addObject(ConstraintWeb.INCLUDE_PAGE, "/app/board/manager/board_manager_create.jsp");
		}
		else if (m == Mode.CREATE_ACTION) {
			Board bd = new Board();
			Binder.bind(request, bd);
			boardService.createBoard(bd);
			m = Mode.LIST_FORM;
		}
		// 수정 폼 
		else if(m == Mode.UPDATE_FORM){
			String code = request.getParameter("boardCode");
			Board b = boardService.getBoard(code);
			mav.addObject(BoardManagerController.AttributeKey.BOARD_ITEM.name(), b);
			mav.addObject(AttributeKey.MODE.name(), Mode.UPDATE_ACTION);
			mav.addObject(ConstraintWeb.INCLUDE_PAGE, "/app/board/manager/board_manager_create.jsp");
		}
		else if (m == Mode.UPDATE_ACTION) {
			Board bd = new Board();
			Binder.bind(request, bd);
			boardService.updateBoard(bd);
			m = Mode.LIST_FORM;
		}
		// 삭제 처리
		else if (m == Mode.DELETE_ACTION) {
			String code = request.getParameter("boardCode");
			Board b = boardService.getBoard(code);
			b.setDeleteF(true);
			boardService.updateBoard(b);
			m = Mode.LIST_FORM;
		}
		// 목록폼
		if (m == Mode.LIST_FORM) {
			GenericPage<Board> boardPagingList = boardService.getBoardPagingList(pageCondition);
			mav.addObject(AttributeKey.BOARD_LIST.name(), boardPagingList);
			mav.addObject(ConstraintWeb.INCLUDE_PAGE, "/app/board/manager/board_manager_list.jsp");
			
			request.setAttribute("boardList", boardPagingList.getList());
			request.setAttribute("size", boardPagingList.getTotalCount());
			request.setAttribute("pagesize", boardPagingList.getPagesize());
			request.setAttribute("pageunit", boardPagingList.getPageunit());
		}

		return mav;
	}

	/**
	 * request parameter에서 페이징 및 검색 정보를 추출 함
	 * 
	 * @param request
	 * @return 페이징 및 검색 정보
	 */
	private PagingCondition bindSearch(HttpServletRequest request) {
		int currentPage = Integer.parseInt(StringUtilAd.null2str(request.getParameter("currentPage"), "1"));
		PagingCondition searchVO = new PagingCondition(currentPage, 2);

		// 검색
		String searchName = request.getParameter("searchName");
		String searchCode = request.getParameter("searchCode");
		if (StringUtilAd.isEmpty(searchName)) {
			searchVO.addCondition(BoardService.BOARD_SEARCH_ITEM.NAME, searchName);
		}
		if (StringUtilAd.isEmpty(searchCode)) {
			searchVO.addCondition(BoardService.BOARD_SEARCH_ITEM.CODE, searchCode);
		}
		return searchVO;
	}

}
