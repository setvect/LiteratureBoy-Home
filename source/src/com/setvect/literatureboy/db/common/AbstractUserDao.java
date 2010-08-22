package com.setvect.literatureboy.db.common;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.SearchListVo;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.db.UserDao;
import com.setvect.literatureboy.service.user.AuthMapSearch;
import com.setvect.literatureboy.service.user.UserSearch;
import com.setvect.literatureboy.vo.user.Auth;
import com.setvect.literatureboy.vo.user.AuthMap;
import com.setvect.literatureboy.vo.user.AuthMapKey;
import com.setvect.literatureboy.vo.user.User;

/**
 * ȸ������ DAO
 * 
 * @version $Id: AbstractMemoDao.java 63 2010-08-16 12:24:44Z setvect@naver.com $
 */
public abstract class AbstractUserDao<T, PK extends Serializable> implements UserDao {
	@Resource
	SessionFactory sessionFactory;

	// ---------------- �����
	public User getUser(String userId) {
		Session session = sessionFactory.getCurrentSession();
		return (User) session.get(User.class, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.MemoDao#getPagingList(com.setvect.literatureboy.service.memo.MemoSearchVO)
	 */
	public GenericPage<User> getUserPagingList(UserSearch search) {
		Session session = sessionFactory.getCurrentSession();

		String q = "select count(*) from User " + getUserWhere(search);
		Query query = session.createQuery(q);
		int totalCount = ((Long) query.uniqueResult()).intValue();

		q = " from User " + getUserWhere(search) + " order by userId ";
		query = session.createQuery(q);
		query.setFirstResult(search.getStartNumber());
		query.setMaxResults(search.getPagePerItemCount());

		@SuppressWarnings("unchecked")
		List<User> resultList = query.list();

		GenericPage<User> resultPage = new GenericPage<User>(resultList, search.getCurrentPage(), totalCount,
				search.getPageUnit(), search.getPagePerItemCount());
		return resultPage;
	}

	private String getUserWhere(UserSearch search) {
		String where = "where deleteF = 'N' ";

		// �ΰ��� ������ �˻� ���ǿ� ���� �� �� ����
		if (!StringUtilAd.isEmpty(search.getSearchId())) {
			where += " and userId like " + StringUtilAd.getSqlStringLike(search.getSearchId());
		}
		else if (!StringUtilAd.isEmpty(search.getSearchName())) {
			where += " and name like " + StringUtilAd.getSqlStringLike(search.getSearchName());
		}
		return where;
	}

	public void createUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		session.flush();
	}

	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		session.flush();
	}

	public void removeUser(String userId) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getUser(userId));
		session.flush();
	}

	// ---------------- ����
	public Auth getAuth(int authSeq) {
		Session session = sessionFactory.getCurrentSession();
		return (Auth) session.get(Auth.class, authSeq);
	}

	public GenericPage<Auth> getAuthPagingList(SearchListVo search) {
		Session session = sessionFactory.getCurrentSession();

		String q = "select count(*) from Auth ";
		Query query = session.createQuery(q);
		int totalCount = ((Long) query.uniqueResult()).intValue();

		q = " from Auth order by authSeq ";
		query = session.createQuery(q);
		query.setFirstResult(search.getStartNumber());
		query.setMaxResults(search.getPagePerItemCount());

		@SuppressWarnings("unchecked")
		List<Auth> resultList = query.list();

		GenericPage<Auth> resultPage = new GenericPage<Auth>(resultList, search.getCurrentPage(), totalCount,
				search.getPageUnit(), search.getPagePerItemCount());
		return resultPage;

	}

	public void createAuth(Auth auth) {
		Session session = sessionFactory.getCurrentSession();
		session.save(auth);
		session.flush();
	}

	public void updateAuth(Auth auth) {
		Session session = sessionFactory.getCurrentSession();
		session.update(auth);
		session.flush();
	}

	public void removeAuth(int authSeq) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getAuth(authSeq));
		session.flush();
	}

	// ---------------- ���� ����
	public AuthMap getAuthMap(AuthMapKey key) {
		Session session = sessionFactory.getCurrentSession();
		return (AuthMap) session.get(AuthMap.class, key);
	}

	public GenericPage<AuthMap> getAuthMapPagingList(AuthMapSearch search) {
		Session session = sessionFactory.getCurrentSession();

		String q = "select count(*) from AuthMap " + getAuthMapWhere(search);
		Query query = session.createQuery(q);
		int totalCount = ((Long) query.uniqueResult()).intValue();

		q = " from AuthMap " + getAuthMapWhere(search) + " order by userId ";
		query = session.createQuery(q);
		query.setFirstResult(search.getStartNumber());
		query.setMaxResults(search.getPagePerItemCount());

		@SuppressWarnings("unchecked")
		List<AuthMap> resultList = query.list();

		GenericPage<AuthMap> resultPage = new GenericPage<AuthMap>(resultList, search.getCurrentPage(), totalCount,
				search.getPageUnit(), search.getPagePerItemCount());
		return resultPage;
	}

	private String getAuthMapWhere(AuthMapSearch search) {
		String where = "";

		return where;
	}

	public void createAuthMap(AuthMap authMap) {
		Session session = sessionFactory.getCurrentSession();
		session.save(authMap);
		session.flush();
	}

	public void removeAuthMap(AuthMapKey key) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getAuthMap(key));
		session.flush();
	}

}
