package com.setvect.literatureboy.db.common;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.db.BoardDao;
import com.setvect.literatureboy.service.board.BoardArticleSearch;
import com.setvect.literatureboy.service.board.BoardManagerSearch;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;

/**
 * 게시물 DAO
 * 
 * @version $Id$
 */
public abstract class AbstractBoardDao implements BoardDao {
	@Resource
	SessionFactory sessionFactory;

	// --------------- 관리
	public Board getBoard(String code) {
		Session session = sessionFactory.getCurrentSession();
		return (Board) session.get(Board.class, code);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.MemoDao#getPagingList(com.setvect.literatureboy.service.memo.MemoSearchVO)
	 */
	public GenericPage<Board> getBoardPagingList(BoardManagerSearch pageCondition) {

		Session session = sessionFactory.getCurrentSession();

		String q = "select count(*) from Board " + getManagerWhereClause(pageCondition);
		Query query = session.createQuery(q);
		int totalCount = ((Long) query.uniqueResult()).intValue();

		q = " from Board " + getManagerWhereClause(pageCondition) + " order by boardCode ";
		query = session.createQuery(q);
		query.setFirstResult(pageCondition.getStartNumber());
		query.setMaxResults(pageCondition.getPagePerItemCount());

		@SuppressWarnings("unchecked")
		List<Board> resultList = query.list();

		GenericPage<Board> resultPage = new GenericPage<Board>(resultList, pageCondition.getCurrentPage(), totalCount,
				pageCondition.getPageUnit(), pageCondition.getPagePerItemCount());
		return resultPage;
	}

	/**
	 * @param pageCondition
	 *            검색 조건
	 * @return select where 절 조건
	 */
	private String getManagerWhereClause(BoardManagerSearch pageCondition) {
		String where = "where deleteF = 'N'";

		// 두개가 동새에 검색 조건에 포함 될 수 없음
		if (!StringUtilAd.isEmpty(pageCondition.getSearchCode())) {
			where += " and boardCode like " + StringUtilAd.getSqlStringLike(pageCondition.getSearchCode());
		}
		else if (!StringUtilAd.isEmpty(pageCondition.getSearchName())) {
			where += " and name like " + StringUtilAd.getSqlStringLike(pageCondition.getSearchName());
		}
		return where;
	}

	/**
	 * @param board
	 * @throws Exception
	 */
	public void createBoard(Board board) {
		Session session = sessionFactory.getCurrentSession();
		session.save(board);
		session.flush();
	}

	/**
	 * @param article
	 */
	public void updateBoard(Board board) {
		Session session = sessionFactory.getCurrentSession();
		session.update(board);
		session.flush();
	}

	/**
	 * @param articleSeq
	 */
	public void removeBoard(String code) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getBoard(code));
		session.flush();
	}

	// --------------- 게시물
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.BoardArticleDao#getArticle(int)
	 */
	public BoardArticle getArticle(int articleSeq) {
		Session session = sessionFactory.getCurrentSession();
		return (BoardArticle) session.get(BoardArticle.class, articleSeq);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.MemoDao#getPagingList(com.setvect.literatureboy.service.memo.MemoSearchVO)
	 */
	// TODO 목록 검색시 불필요한 항목(내용 TEXT)까지 가져오는 경우 발생. 성능 문제 발생시 수정
	public GenericPage<BoardArticle> getArticlePagingList(BoardArticleSearch pageCondtion) {
		Session session = sessionFactory.getCurrentSession();

		String q = "select count(*) from BoardArticle " + getArticleWhereClause(pageCondtion);
		Query query = session.createQuery(q);
		int totalCount = ((Long) query.uniqueResult()).intValue();

		q = " from BoardArticle " + getArticleWhereClause(pageCondtion) + " order by IDX2 desc, IDX3 ASC ";
		query = session.createQuery(q);
		query.setFirstResult(pageCondtion.getStartNumber());
		query.setMaxResults(pageCondtion.getPagePerItemCount());

		@SuppressWarnings("unchecked")
		List<BoardArticle> resultList = query.list();

		GenericPage<BoardArticle> resultPage = new GenericPage<BoardArticle>(resultList, pageCondtion.getCurrentPage(),
				totalCount, pageCondtion.getPageUnit(), pageCondtion.getPagePerItemCount());
		return resultPage;
	}

	/**
	 * @param search
	 *            검색 조건
	 * @return select where 절 조건
	 */
	private String getArticleWhereClause(BoardArticleSearch search) {

		String where = " where boardCode = " + StringUtilAd.getSqlString(search.getSearchCode());

		if (!search.isDeleteView()) {
			// 삭제 게시물 보여 주지 않음
			where += " and deleteF = 'N' ";
		}

		// 두개 이상 동시에 검색 조건에 포함 될 수 없음
		if (!StringUtilAd.isEmpty(search.getSearchName())) {
			where += " and name like " + StringUtilAd.getSqlStringLike(search.getSearchName());
		}
		else if (!StringUtilAd.isEmpty(search.getSearchTitle())) {
			where += " and title like " + StringUtilAd.getSqlStringLike(search.getSearchTitle());
		}
		else if (!StringUtilAd.isEmpty(search.getSearchContent())) {
			where += " and content like " + StringUtilAd.getSqlStringLike(search.getSearchContent());
		}
		return where;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see anyframe.core.generic.dao.hibernate.GenericDaoHibernate#create(java.lang.Object)
	 */
	public void createArticle(BoardArticle article) {
		Session session = sessionFactory.getCurrentSession();
		String q;

		// 인덱스 순서
		q = "select COALESCE(max(idx1) + 1, 1)  from BoardArticle WHERE boardCode = ?";
		Query query = session.createQuery(q);
		query.setParameter(0, article.getBoardCode());
		int idx1 = ((Integer) query.uniqueResult()).intValue();
		article.setIdx1(idx1);

		q = "select COALESCE(max(idx2) + 1, 1) from BoardArticle WHERE boardCode = ?";
		query = session.createQuery(q);
		query.setParameter(0, article.getBoardCode());
		int idx2 = ((Integer) query.uniqueResult()).intValue();
		article.setIdx2(idx2);

		// 기본 값
		article.setIdx3(1);
		article.setDepthLevel(1);

		session.save(article);
		session.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.BoardArticleDao#createReply(com.setvect.literatureboy.vo.board.BoardArticle,
	 * int)
	 */
	public void createArticleReply(BoardArticle article, int parentId) {
		Session session = sessionFactory.getCurrentSession();

		// IDX1
		String q = "select COALESCE(max(IDX1) + 1, 1) AS CNT from BoardArticle WHERE boardCode = ?";
		Query query = session.createQuery(q);
		query.setParameter(0, article.getBoardCode());
		article.setIdx1(((Integer) query.uniqueResult()).intValue());

		BoardArticle target = getArticle(parentId);

		// IDX2
		article.setIdx2(target.getIdx2());

		// 게시물의 다음 번호
		article.setIdx3(target.getIdx3() + 1);

		// 답글 대상 글 깊이 + 1
		article.setDepthLevel(target.getDepthLevel() + 1);

		// 현재 답변이 달릴 게시판으로 부터 이하의 이전 답변 게시판에 idx3를 +1를 해준다.
		q = " UPDATE BoardArticle SET                                 "
				+ " 	idx3 = idx3 + 1                               "
				+ " WHERE	boardCode = ? AND idx2 = ? AND idx3 >=	? ";
		query = session.createQuery(q);
		query.setParameter(0, article.getBoardCode());
		query.setParameter(1, new Integer(article.getIdx2()));
		query.setParameter(2, new Integer(article.getIdx3()));
		query.executeUpdate();

		session.save(article);
		session.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.BoardArticleDao#updateArticle(com.setvect.literatureboy.vo.board.BoardArticle)
	 */
	public void updateArticle(BoardArticle article) {
		Session session = sessionFactory.getCurrentSession();
		session.update(article);
		session.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.BoardArticleDao#removeArticle(int)
	 */
	public void removeArticle(int articleSeq) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getArticle(articleSeq));
		session.flush();
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

		String q = " from BoardComment where articleSeq = ? order by commentSeq ";
		Query query = session.createQuery(q);

		query.setInteger(0, boardItemSeq);

		@SuppressWarnings("unchecked")
		List<BoardComment> resultList = query.list();

		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.BoardArticleDao#saveComment(com.setvect.literatureboy.vo.board.BoardComment)
	 */
	public void createComment(BoardComment comment) {
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
		BoardComment comment = getComment(seq);
		session.delete(comment);
		session.flush();
	}

	// ----- Attach File

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.BoardArticleDao#listAttach(int)
	 */
	public List<BoardAttachFile> listAttachFile(int boardItemSeq) {
		Session session = sessionFactory.getCurrentSession();

		String q = " from BoardAttachFile where articleSeq = ? order by fileSeq ";
		Query query = session.createQuery(q);

		query.setInteger(0, boardItemSeq);

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
	public void createAttachFile(BoardAttachFile attachFile) {
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