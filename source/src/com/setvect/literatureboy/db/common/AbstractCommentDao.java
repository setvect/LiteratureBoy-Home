package com.setvect.literatureboy.db.common;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.setvect.literatureboy.db.CommentDao;
import com.setvect.literatureboy.service.common.CommentModule;
import com.setvect.literatureboy.vo.Comment;

/**
 * ÷������
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

	public List<Comment> listComment(CommentModule moduleName, String moduleItemId) {
		Session session = sessionFactory.getCurrentSession();

		String q = " from Comment where moduleName = ? and moduleId = ? order by commentSeq ";
		Query query = session.createQuery(q);

		query.setString(0, moduleName.name());
		query.setString(1, moduleItemId);

		@SuppressWarnings("unchecked")
		List<Comment> resultList = query.list();

		return resultList;
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