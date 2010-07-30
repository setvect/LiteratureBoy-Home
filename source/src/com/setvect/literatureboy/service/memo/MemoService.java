package com.setvect.literatureboy.service.memo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import anyframe.common.Page;

import com.setvect.common.util.PagingCondition;
import com.setvect.common.util.GenericPage;
import com.setvect.literatureboy.db.MemoDao;
import com.setvect.literatureboy.vo.Memo;

@Service("service.memo")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class MemoService {
	/** DB ��Ʈ�� �ν��Ͻ� */
	@Resource
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

	public GenericPage<Memo> getPageList(PagingCondition searchVo) throws Exception {
		return memoDao.getPagingList(searchVo);
	}

}
