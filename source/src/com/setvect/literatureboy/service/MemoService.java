package com.setvect.literatureboy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.setvect.literatureboy.db.MemoDao;
import com.setvect.literatureboy.vo.Memo;

@Service("service.memo")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class MemoService {
	/** DB ��Ʈ�� �ν��Ͻ� */
	@Autowired
	private MemoDao memoDao;

	/**
	 * @param no
	 *            ȸ�� ���̵�
	 * @return ���̵� �´� ȸ������ ������ null
	 * @throws Exception
	 */
	public Memo getUser(int no) throws Exception {
		return memoDao.get(no);

	}

	/**
	 * ȸ�� ���� ����
	 * 
	 * @param id
	 *            ȸ�� ���̵�
	 * @throws Exception
	 */
	public void addMemo(Memo memo) throws Exception {
		memoDao.create(memo);
	}

}
