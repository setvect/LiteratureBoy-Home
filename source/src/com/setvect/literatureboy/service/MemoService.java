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
	/** DB 컨트롤 인스턴스 */
	@Autowired
	private MemoDao memoDao;

	/**
	 * @param no
	 *            회원 아이디
	 * @return 아이디에 맞는 회원정보 없으면 null
	 * @throws Exception
	 */
	public Memo getUser(int no) throws Exception {
		return memoDao.get(no);

	}

	/**
	 * 회원 정보 삭제
	 * 
	 * @param id
	 *            회원 아이디
	 * @throws Exception
	 */
	public void addMemo(Memo memo) throws Exception {
		memoDao.create(memo);
	}

}
