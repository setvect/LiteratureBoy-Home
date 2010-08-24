package com.setvect.literatureboy.service.memo;

import javax.annotation.Resource;

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
	/** DB 컨트롤 인스턴스 */
	@Resource
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

	public GenericPage<Memo> getPageList(SearchListVo searchVo) throws Exception {
		return memoDao.getPagingList(searchVo);
	}

}
