package com.setvect.literatureboy.db.common;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.setvect.literatureboy.db.CtmemoDao;
import com.setvect.literatureboy.vo.ctmemo.CtmemoSearchCondition;
import com.setvect.literatureboy.vo.ctmemo.CtmemoVo;

/**
 * 하이버네이트을 이용한 메모장 DAO<br>
 * DBMS에 맞추어 상속받아 사용.
 * 
 * @version $Id$
 */
public abstract class AbstractCtmemoDao implements CtmemoDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CtmemoVo getCtmemo(int ctmemoId) {
		Session session = sessionFactory.getCurrentSession();
		return (CtmemoVo) session.get(CtmemoVo.class, ctmemoId);
	}

	@Override
	public int getMaxZindex() {
		Session session = sessionFactory.getCurrentSession();
		String q = " select COALESCE(max(zIndex) + 1, 1) from CtmemoVo where deleteF = 'N'";
		Query query = session.createQuery(q);
		return (Integer) query.uniqueResult();
	}

	@Override
	public List<CtmemoVo> listCtmemo(CtmemoSearchCondition condition) {
		Session session = sessionFactory.getCurrentSession();
		String q = " from CtmemoVo where deleteF = 'N' order by ctmemoSeq desc";
		Query query = session.createQuery(q);

		@SuppressWarnings("unchecked")
		List<CtmemoVo> resultList = query.list();
		return resultList;
	}

	@Override
	public void insert(CtmemoVo ctmemo) {
		Session session = sessionFactory.getCurrentSession();
		session.save(ctmemo);
	}

	@Override
	public void updateCtmemo(CtmemoVo ctmemo) {
		Session session = sessionFactory.getCurrentSession();
		session.update(ctmemo);
	}

	@Override
	public void deleteCtmemo(int ctmemoId) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getCtmemo(ctmemoId));
	}
}
