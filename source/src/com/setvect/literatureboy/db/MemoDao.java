package com.setvect.literatureboy.db;

import anyframe.core.generic.dao.GenericDao;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.SearchListVo;
import com.setvect.literatureboy.vo.Memo;

/**
 * �޸��� DAO
 */
public interface MemoDao extends GenericDao<Memo, Integer> {
	public GenericPage<Memo> getPagingList(SearchListVo paging) throws Exception;
}
