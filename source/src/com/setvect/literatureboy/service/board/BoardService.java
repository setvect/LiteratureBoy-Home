package com.setvect.literatureboy.service.board;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.setvect.literatureboy.db.BoardDao;

/**
 * @version $Id$
 */
@Service("service.board")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class BoardService {

	/**
	 * �Խ��� ���� ���� �˻� �׸�
	 */
	public enum BOARD_SEARCH_ITEM {
		NAME, CODE
	}

	/** DB ��Ʈ�� �ν��Ͻ� */
	@Resource
	private BoardDao boardDao;
}
