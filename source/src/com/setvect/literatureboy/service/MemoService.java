package com.setvect.literatureboy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.setvect.literatureboy.db.MemoDao;
import com.setvect.literatureboy.vo.Memo;

@Component("service.memo")
public class MemoService {
	/** DB ��Ʈ�� �ν��Ͻ� */
	@Autowired
	private MemoDao memoDao;

	/**
	 * @param no
	 *            ȸ�� ���̵�
	 * @return ���̵� �´� ȸ������ ������ null
	 */
	public Memo getUser(int no) {
		try {
			return memoDao.get(no);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ȸ�� ���� ����
	 * 
	 * @param id
	 *            ȸ�� ���̵�
	 */
	public void addMemo(Memo memo) {
		try {
			memoDao.create(memo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
