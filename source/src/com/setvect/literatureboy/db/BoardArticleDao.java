package com.setvect.literatureboy.db;

import anyframe.core.generic.dao.GenericDao;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.PagingCondition;
import com.setvect.literatureboy.vo.board.Board;

/**
 * �Խù�, ÷������, �ڸ�Ʈ  DAO
 * 
 * @version $Id$
 */
public interface BoardArticleDao extends GenericDao<Board, String> {
	/**
	 * @param searchVO
	 * @return �Խ��ǻ��� ���� �׸�
	 * @throws Exception
	 */
	public GenericPage<Board> getPagingList(PagingCondition searchVO) throws Exception;
}
