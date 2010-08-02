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
import com.setvect.literatureboy.db.BoardArticleDao;
import com.setvect.literatureboy.service.board.BoardService;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;

/**
 * 게시물 DAO
 * 
 * @version $Id$
 */
public abstract class AbstractBoardArticleDao<T, PK extends Serializable> extends
		GenericDaoHibernate<BoardArticle, Integer> implements BoardArticleDao {
	@Resource
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.MemoDao#getPagingList(com.setvect.literatureboy.service.memo.MemoSearchVO)
	 */
	public GenericPage<BoardArticle> getPagingList(PagingCondition paging) throws Exception {

		Session session = sessionFactory.getCurrentSession();

		String q = "select count(*) from BoardArticle " + getWhereClause(paging);
		Query query = session.createQuery(q);
		int totalCount = ((Long) query.uniqueResult()).intValue();

		q = " from BoardArticle " + getWhereClause(paging) + " order by IDX2 desc, IDX3 ASC ";
		query = session.createQuery(q);
		query.setFirstResult(paging.getStartNumber());
		query.setMaxResults(paging.getPagePerItemCount());

		@SuppressWarnings("unchecked")
		List<BoardArticle> resultList = query.list();

		GenericPage<BoardArticle> resultPage = new GenericPage<BoardArticle>(resultList, paging.getCurrentPageNo(),
				totalCount, paging.getPageUnit(), paging.getPagePerItemCount());
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

	// ----- comment

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.BoardArticleDao#getComment(int)
	 */
	public BoardComment getComment(int seq) {
		Session session = sessionFactory.getCurrentSession();
		return (BoardComment) session.get(BoardComment.class, seq);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.BoardArticleDao#listComment(int)
	 */
	public List<BoardComment> listComment(int boardItemSeq) {
		Session session = sessionFactory.getCurrentSession();

		String q = " from BoardComment where articleSeq = ? order commectSeq ";
		Query query = session.createQuery(q);

		query.setInteger(1, boardItemSeq);

		@SuppressWarnings("unchecked")
		List<BoardComment> resultList = query.list();

		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.BoardArticleDao#saveComment(com.setvect.literatureboy.vo.board.BoardComment)
	 */
	public void saveComment(BoardComment comment) {
		Session session = sessionFactory.getCurrentSession();
		session.save(comment);
		session.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.BoardArticleDao#updateComment(com.setvect.literatureboy.vo.board.BoardComment)
	 */
	public void updateComment(BoardComment comment) {
		Session session = sessionFactory.getCurrentSession();
		session.update(comment);
		session.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.BoardArticleDao#removeComment(int)
	 */
	public void removeComment(int seq) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getComment(seq));
		session.flush();
	}

	// ----- Attach File

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.BoardArticleDao#listAttach(int)
	 */
	public List<BoardAttachFile> listAttach(int boardItemSeq) {
		Session session = sessionFactory.getCurrentSession();

		String q = " from BoardAttachFile where articleSeq = ? order fileSeq ";
		Query query = session.createQuery(q);

		query.setInteger(1, boardItemSeq);

		@SuppressWarnings("unchecked")
		List<BoardAttachFile> resultList = query.list();

		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.setvect.literatureboy.db.BoardArticleDao#saveAttachFile(com.setvect.literatureboy.vo.board.BoardAttachFile)
	 */
	public void saveAttachFile(BoardAttachFile attachFile) {
		Session session = sessionFactory.getCurrentSession();
		session.save(attachFile);
		session.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.BoardArticleDao#removeAttachFile(int)
	 */
	public void removeAttachFile(int seq) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(BoardAttachFile.class, seq));
		session.flush();
	}
}