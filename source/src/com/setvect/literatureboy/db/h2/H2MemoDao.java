package com.setvect.literatureboy.db.h2;

import org.springframework.stereotype.Service;

import com.setvect.literatureboy.db.common.AbstractMemoDao;
import com.setvect.literatureboy.vo.Memo;

/**
 * H2 DB용 메모장 DAO
 * 
 * @version $Id$
 */
@Service
public class H2MemoDao extends AbstractMemoDao<Memo, Integer> {
}
