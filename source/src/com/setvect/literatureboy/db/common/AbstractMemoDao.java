package com.setvect.literatureboy.db.common;

import java.io.Serializable;

import anyframe.core.generic.dao.hibernate.GenericDaoHibernate;

import com.setvect.literatureboy.db.MemoDao;
import com.setvect.literatureboy.vo.Memo;

/**
 * ¸Þ¸ðÀå DAO
 */
public abstract class AbstractMemoDao<T, PK extends Serializable> extends GenericDaoHibernate<Memo, Integer> implements
		MemoDao {

}
