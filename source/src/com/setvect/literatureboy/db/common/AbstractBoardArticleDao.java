package com.setvect.literatureboy.db.common;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import com.setvect.common.util.StringUtilAd;
import com.setvect.common.util.GenericPage;
import com.setvect.common.util.PagingCondition;
import com.setvect.literatureboy.db.BoardDao;
import com.setvect.literatureboy.service.board.BoardService;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;

/**
 * 게시물 DAO
 * 
 * @version $Id$
 */
public abstract class AbstractBoardArticleDao implements BoardDao {
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
	public GenericPage<Board> getBoardPagingList(PagingCondition pageCondition) throws Exception {

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
	private String getManagerWhereClause(PagingCondition pageCondition) {
		String where = "where deleteF = 'N'";

		String code = pageCondition.getConditionString(BoardService.BOARD_SEARCH_ITEM.CODE);
		String name = pageCondition.getConditionString(BoardService.BOARD_SEARCH_ITEM.NAME);

		// 두개가 동새에 검색 조건에 포함 될 수 없음
		if (!StringUtilAd.isEmpty(code)) {
			where += " and boardCode like " + StringUtilAd.getSqlStringLike(code);
		}
		else if (!StringUtilAd.isEmpty(name)) {
			where += " and name like " + StringUtilAd.getSqlStringLike(name);
		}
		return where;
	}

	/**
	 * @param board
	 * @throws Exception
	 */
	public void createBoard(Board board) throws Exception {
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
		// TODO 플래그 형태로 삭제 
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
	public GenericPage<BoardArticle> getArticlePagingList(PagingCondition pageCondtion) throws Exception {
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
	 * @param paging
	 *            검색 조건
	 * @return select where 절 조건
	 */
	private String getArticleWhereClause(PagingCondition paging) {
		String code = paging.getConditionString(BoardService.BOARD_ARTICLE_SEARCH_ITEM.CODE);
		String name = paging.getConditionString(BoardService.BOARD_ARTICLE_SEARCH_ITEM.NAME);
		String title = paging.getConditionString(BoardService.BOARD_ARTICLE_SEARCH_ITEM.TITLE);
		String content = paging.getConditionString(BoardService.BOARD_ARTICLE_SEARCH_ITEM.CONTENT);

		String where = " where boardCode = " + StringUtilAd.getSqlString(code);

		// 두개 이상 동시에 검색 조건에 포함 될 수 없음
		if (!StringUtilAd.isEmpty(name)) {
			where += " and name like " + StringUtilAd.getSqlStringLike(name);
		}
		else if (!StringUtilAd.isEmpty(title)) {
			where += " and title like " + StringUtilAd.getSqlStringLike(title);
		}
		else if (!StringUtilAd.isEmpty(content)) {
			where += " and content like " + StringUtilAd.getSqlStringLike(content);
		}
		return where;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see anyframe.core.generic.dao.hibernate.GenericDaoHibernate#create(java.lang.Object)
	 */
	public void createArticle(BoardArticle article) throws Exception {
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
	public void createArticleReply(BoardArticle article, int parentId) throws Exception {
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

		String q = " from BoardComment where articleSeq = ? order by commectSeq ";
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
		session.delete(getComment(seq));
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