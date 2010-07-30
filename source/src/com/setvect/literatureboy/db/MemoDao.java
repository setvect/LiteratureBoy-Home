package com.setvect.literatureboy.db;

import anyframe.core.generic.dao.GenericDao;

import com.setvect.common.util.PagingCondition;
import com.setvect.common.util.GenericPage;
import com.setvect.literatureboy.vo.Memo;

/**
 * ¸Þ¸ðÀå DAO
 */
public interface MemoDao extends GenericDao<Memo, Integer> {
	public GenericPage<Memo> getPagingList(PagingCondition paging) throws Exception;
}
