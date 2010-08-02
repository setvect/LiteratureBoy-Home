package com.setvect.literatureboy.db.common;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import anyframe.core.generic.dao.hibernate.GenericDaoHibernate;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.PagingCondition;
import com.setvect.literatureboy.db.MemoDao;
import com.setvect.literatureboy.vo.Memo;

/**
 * ¸Þ¸ðÀå DAO
 * 
 * @version $Id$
 */
public abstract class AbstractMemoDao<T, PK extends Serializable> extends GenericDaoHibernate<Memo, Integer> implements
		MemoDao {
	@Resource
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.db.MemoDao#getPagingList(com.setvect.literatureboy.service.memo.MemoSearchVO)
	 */
	public GenericPage<Memo> getPagingList(PagingCondition paging) throws Exception {

		Session session = sessionFactory.getCurrentSession();

		String q = "select count(*) from Memo ";
		Query query = session.createQuery(q);
		int totalCount = ((Long) query.uniqueResult()).intValue();

		q = " from Memo order by id ";
		query = session.createQuery(q);
		query.setFirstResult(paging.getStartNumber());
		query.setMaxResults(paging.getPagePerItemCount());

		@SuppressWarnings("unchecked")
		List<Memo> resultList = query.list();

		GenericPage<Memo> resultPage = new GenericPage<Memo>(resultList, paging.getCurrentPageNo(), totalCount,
				paging.getPageUnit(), paging.getPagePerItemCount());
		return resultPage;
	}

}
