package com.setvect.literatureboy.db;

import anyframe.common.Page;
import anyframe.core.generic.dao.GenericDao;

import com.setvect.common.util.PagingCondition;
import com.setvect.literatureboy.vo.Memo;

/**
 * ¸Þ¸ðÀå DAO
 */
public interface MemoDao extends GenericDao<Memo, Integer> {
	public Page getPagingList(PagingCondition searchVO) throws Exception;
}
