package com.setvect.literatureboy.db.common;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import anyframe.core.generic.dao.hibernate.GenericDaoHibernate;

import com.setvect.common.util.AdvanceStringUtil;
import com.setvect.common.util.GenericPage;
import com.setvect.common.util.PagingCondition;
import com.setvect.literatureboy.db.BoardDao;
import com.setvect.literatureboy.service.board.BoardService;
import com.setvect.literatureboy.vo.board.Board;

/**
 * 게시판 DAO
 * 
 * @version $Id$
 */
public abstract class AbstractBoardDao<T, PK extends Serializable> extends GenericDaoHibernate<Board, String> implements
		BoardDao {
	@Resource
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.MemoDao#getPagingList(com.setvect.literatureboy.service.memo.MemoSearchVO)
	 */
	public GenericPage<Board> getPagingList(PagingCondition paging) throws Exception {

		Session session = sessionFactory.getCurrentSession();

		String q = "select count(*) from Board " + getWhereClause(paging);
		Query query = session.createQuery(q);
		int totalCount = ((Long) query.uniqueResult()).intValue();

		q = " from Board " + getWhereClause(paging) + " order by code ";
		query = session.createQuery(q);
		query.setFirstResult(paging.getStartNumber());
		query.setMaxResults(paging.getPagePerItemCount());

		@SuppressWarnings("unchecked")
		List<Board> resultList = query.list();

		GenericPage<Board> resultPage = new GenericPage<Board>(resultList, paging.getCurrentPageNo(), totalCount,
				paging.getPageUnit(), paging.getPagePerItemCount());
		return resultPage;
	}

	/**
	 * @param paging
	 *            검색 조건
	 * @return select where 절 조건
	 */
	private String getWhereClause(PagingCondition paging) {
		String where = "";

		String code = paging.getConditionString(BoardService.BOARD_SEARCH_ITEM.CODE);
		String name = paging.getConditionString(BoardService.BOARD_SEARCH_ITEM.NAME);

		// 두개가 동새에 검색 조건에 포함 될 수 없음
		if (!AdvanceStringUtil.isEmpty(code)) {
			where = " where boardCode = " + AdvanceStringUtil.getSqlString(code);
		}
		else if (!AdvanceStringUtil.isEmpty(name)) {
			where = " where name = " + AdvanceStringUtil.getSqlString(name);
		}
		return where;
	}
}
