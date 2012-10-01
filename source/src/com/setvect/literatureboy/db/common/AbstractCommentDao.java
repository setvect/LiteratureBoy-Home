package com.setvect.literatureboy.db.common;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.db.CommentDao;
import com.setvect.literatureboy.service.comment.CommentModule;
import com.setvect.literatureboy.service.comment.CommentSearch;
import com.setvect.literatureboy.vo.Comment;
import com.setvect.literatureboy.vo.board.Board;

/**
 * 첨부파일
 * 
 * @version $Id$
 */
public abstract class AbstractCommentDao implements CommentDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Comment getComment(int commentSeq) {
		Session session = sessionFactory.getCurrentSession();
		return (Comment) session.get(Comment.class, commentSeq);
	}

	public GenericPage<Comment> getCommentPagingList(CommentSearch pageCondition) {
		Session session = sessionFactory.getCurrentSession();

		String q = "select count(*) from Comment " + getWhereClause(pageCondition);
		Query query = session.createQuery(q);
		int totalCount = ((Long) query.uniqueResult()).intValue();

		q = " from Comment " + getWhereClause(pageCondition) + " order by commentSeq desc";
		query = session.createQuery(q);
		query.setFirstResult(pageCondition.getStartNumber());
		query.setMaxResults(pageCondition.getPagePerItemCount());

		@SuppressWarnings("unchecked")
		List<Comment> resultList = query.list();
		GenericPage<Comment> resultPage = new GenericPage<Comment>(resultList, pageCondition.getCurrentPage(),
				totalCount, pageCondition.getPageUnit(), pageCondition.getPagePerItemCount());

		return resultPage;
	}

	private String getWhereClause(CommentSearch pageCondition) {
		String where = " where moduleName = " + StringUtilAd.getSqlString(pageCondition.getModuleName().name())
				+ " and moduleId = " + StringUtilAd.getSqlString(pageCondition.getModuleItemId());
		return where;

	}

	public void createComment(Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		session.save(comment);
		session.flush();
	}

	public void updateComment(Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		session.update(comment);
		session.flush();
	}

	public void removeComment(int commentSeq) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getComment(commentSeq));
		session.flush();
	}
}