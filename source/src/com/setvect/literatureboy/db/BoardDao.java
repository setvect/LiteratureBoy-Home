package com.setvect.literatureboy.db;

import anyframe.common.Page;

import com.setvect.common.util.PagingCondition;

/**
 * �Խ��� DAO
 * 
 * @version $Id$
 */
public interface BoardDao {
	public Page getPagingList(PagingCondition searchVO) throws Exception;
}
