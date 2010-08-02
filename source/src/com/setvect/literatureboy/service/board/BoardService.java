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
	 * 게시판 설정 정보 검색 항목
	 */
	public enum BOARD_SEARCH_ITEM {
		NAME, CODE
	}

	/** DB 컨트롤 인스턴스 */
	@Resource
	private BoardDao boardDao;
}
