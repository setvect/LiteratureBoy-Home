package com.setvect.literatureboy.db;

import org.hibernate.Session;

import com.setvect.common.db.HibernateUtil;
import com.setvect.literatureboy.vo.Memo;

/**
 * �޸��� DAO
 */
public abstract class MemoDao {
	/**
	 * @param no
	 *            �޸� ��ȣ
	 * @return �ش� ��ȣ�� ���� �޸�
	 */
	public Memo getMemo(int no) {
		Session session = HibernateUtil.getCurrentSession();
		return (Memo) session.get(Memo.class, no);
	}

	/**
	 * �޸� ���
	 * 
	 * @param memo
	 *            �޸� ����
	 */
	public void insMemo(Memo memo) {
		Session session = HibernateUtil.getCurrentSession();
		session.save(memo);
		session.flush();
	}
}
