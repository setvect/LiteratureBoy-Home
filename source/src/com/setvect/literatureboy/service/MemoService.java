package com.setvect.literatureboy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.setvect.literatureboy.db.MemoDao;
import com.setvect.literatureboy.vo.Memo;

@Component("service.memo")
public class MemoService {
	/** DB 컨트롤 인스턴스 */
	@Autowired
	private MemoDao memoDao;

	/**
	 * @param no
	 *            회원 아이디
	 * @return 아이디에 맞는 회원정보 없으면 null
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
	 * 회원 정보 삭제
	 * 
	 * @param id
	 *            회원 아이디
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
