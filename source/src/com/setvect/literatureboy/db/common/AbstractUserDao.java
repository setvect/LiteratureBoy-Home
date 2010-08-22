package com.setvect.literatureboy.db.common;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.db.UserDao;
import com.setvect.literatureboy.service.user.UserSearch;
import com.setvect.literatureboy.vo.user.User;

/**
 * 회원관리 DAO
 * 
 * @version $Id: AbstractMemoDao.java 63 2010-08-16 12:24:44Z setvect@naver.com $
 */
public abstract class AbstractUserDao<T, PK extends Serializable> implements UserDao {
	@Resource
	SessionFactory sessionFactory;

	public User getUser(String userId) {
		Session session = sessionFactory.getCurrentSession();
		return (User) session.get(User.class, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.MemoDao#getPagingList(com.setvect.literatureboy.service.memo.MemoSearchVO)
	 */
	public GenericPage<User> getPagingList(UserSearch search) {

		Session session = sessionFactory.getCurrentSession();

		String q = "select count(*) from User " + getWhere(search);
		Query query = session.createQuery(q);
		int totalCount = ((Long) query.uniqueResult()).intValue();

		q = " from User " + getWhere(search) + " order by userId ";
		query = session.createQuery(q);
		query.setFirstResult(search.getStartNumber());
		query.setMaxResults(search.getPagePerItemCount());

		@SuppressWarnings("unchecked")
		List<User> resultList = query.list();

		GenericPage<User> resultPage = new GenericPage<User>(resultList, search.getCurrentPage(), totalCount,
				search.getPageUnit(), search.getPagePerItemCount());
		return resultPage;
	}

	/**
	 * @param search
	 *            검색 정보
	 * @return 검색 where
	 */
	private String getWhere(UserSearch search) {
		String where = "where deleteF = 'N' ";

		// 두개가 동새에 검색 조건에 포함 될 수 없음
		if (!StringUtilAd.isEmpty(search.getSearchId())) {
			where += " and userId like " + StringUtilAd.getSqlStringLike(search.getSearchId());
		}
		else if (!StringUtilAd.isEmpty(search.getSearchName())) {
			where += " and name like " + StringUtilAd.getSqlStringLike(search.getSearchName());
		}
		return where;
	}

	/**
	 * @param user
	 * @throws Exception
	 */
	public void createUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		session.flush();
	}

	/**
	 * @param user
	 */
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		session.flush();
	}

	/**
	 * @param userId
	 */
	public void removeUser(String userId) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getUser(userId));
		session.flush();
	}

}
