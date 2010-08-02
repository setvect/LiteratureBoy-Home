package com.setvect.literatureboy.db;

import anyframe.core.generic.dao.GenericDao;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.PagingCondition;
import com.setvect.literatureboy.vo.board.Board;

/**
 * 게시물, 첨부파일, 코멘트  DAO
 * 
 * @version $Id$
 */
public interface BoardArticleDao extends GenericDao<Board, String> {
	/**
	 * @param searchVO
	 * @return 게시판생성 정보 항목
	 * @throws Exception
	 */
	public GenericPage<Board> getPagingList(PagingCondition searchVO) throws Exception;
}
