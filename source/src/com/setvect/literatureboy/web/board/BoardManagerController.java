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
 * ȯ�漳��>��� ���� �޴� ��Ʈ�ѷ�
 */
@Controller
public class BoardManagerController {
	/**
	 * ���� ��ɾ� ����
	 */
	public static enum Mode {
		/** USER,AGEN,Menu ����Ʈ ���� */
		LIST_FORM, READ_FORM, CREATE_FORM, UPDATE_FORM, CREATE_ACTION, UPDATE_ACTION, DELETE_ACTION
	}

	/**
	 * �信 ������ ��ü�� ����Ű�� Ű
	 */
	public static enum AttributeKey {
		/** ����Ʈ */
		BOARD_LIST,
		//
		MODE, BOARD_ITEM,
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

		PagingCondition searchVO = bindSearch(request);
		mav.addObject(AttributeKey.PAGE_SEARCH.name(), searchVO);

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
		// ���� �� 
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
		// ���� ó��
		else if (m == Mode.DELETE_ACTION) {
			String code = request.getParameter("boardCode");
			Board b = boardService.getBoard(code);
			b.setDeleteF(true);
			boardService.updateBoard(b);
			m = Mode.LIST_FORM;
		}
		// �����
		if (m == Mode.LIST_FORM) {
			GenericPage<Board> boardPagingList = boardService.getBoardPagingList(searchVO);
			mav.addObject(AttributeKey.BOARD_LIST.name(), boardPagingList);
			mav.addObject(ConstraintWeb.INCLUDE_PAGE, "/app/board/manager/board_manager_list.jsp");
			
			request.setAttribute("productList", boardPagingList.getList());
			request.setAttribute("size", boardPagingList.getTotalCount());
			request.setAttribute("pagesize", boardPagingList.getPagesize());
			request.setAttribute("pageunit", boardPagingList.getPageunit());
		}

		return mav;
	}

	/**
	 * request parameter���� ����¡ �� �˻� ������ ���� ��
	 * 
	 * @param request
	 * @return ����¡ �� �˻� ����
	 */
	private PagingCondition bindSearch(HttpServletRequest request) {
		int currentPage = Integer.parseInt(StringUtilAd.null2str(request.getParameter("currentPage"), "1"));
		PagingCondition searchVO = new PagingCondition(currentPage);

		// �˻�
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
