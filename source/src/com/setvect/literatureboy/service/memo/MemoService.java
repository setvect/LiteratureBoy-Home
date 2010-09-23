package com.setvect.literatureboy.service.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.SearchListVo;
import com.setvect.literatureboy.db.MemoDao;
import com.setvect.literatureboy.vo.Memo;

/**
 * @version $Id$
 */
@Service
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

	public GenericPage<Memo> getPageList(SearchListVo searchVo) throws Exception {
		return memoDao.getPagingList(searchVo);
	}

}
