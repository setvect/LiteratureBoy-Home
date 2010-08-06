package com.setvect.literatureboy.web.board;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
		LIST_FORM, CREATE_FROM, UPDATE_FROM, CREATE_ACTION, UPDATE_ACTION, DELETE_ACTION
	}

	/**
	 * �信 ������ ��ü�� ����Ű�� Ű
	 */
	public static enum AttributeKey {
		/** ����Ʈ */
		BOARD_LIST, MODE, BOARD_ITEM,
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

		mav.setViewName(ConstraintWeb.INDEX_VIEW);

		// �����
		if (Mode.LIST_FORM == m) {
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

			GenericPage<Board> boardPagingList = boardService.getBoardPagingList(searchVO);
			mav.addObject(AttributeKey.BOARD_LIST.name(), boardPagingList);
			mav.addObject(ConstraintWeb.INCLUDE_PAGE, "/app/board/manager/board_manager_list.jsp");
		}
		else if (Mode.CREATE_FROM == m) {
			mav.addObject(AttributeKey.MODE.name(), Mode.CREATE_ACTION);
			mav.addObject(ConstraintWeb.INCLUDE_PAGE, "/app/board/manager/board_manager_create.jsp");
		}

		return mav;
	}
}
