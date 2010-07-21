package com.setvect.literatureboy.db;

import org.hibernate.Session;

import com.setvect.common.db.HibernateUtil;
import com.setvect.literatureboy.vo.Memo;

/**
 * 메모장 DAO
 */
public abstract class MemoDao {
	/**
	 * @param no
	 *            메모 번호
	 * @return 해당 번호에 대한 메모
	 */
	public Memo getMemo(int no) {
		Session session = HibernateUtil.getCurrentSession();
		return (Memo) session.get(Memo.class, no);
	}

	/**
	 * 메모 등록
	 * 
	 * @param memo
	 *            메모 정보
	 */
	public void insMemo(Memo memo) {
		Session session = HibernateUtil.getCurrentSession();
		session.save(memo);
		session.flush();
	}
}
