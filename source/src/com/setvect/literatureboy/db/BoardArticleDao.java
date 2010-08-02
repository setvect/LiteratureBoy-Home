package com.setvect.literatureboy.db;

import java.util.List;

import anyframe.core.generic.dao.GenericDao;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.PagingCondition;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;

/**
 * 게시물, 첨부파일, 코멘트 DAO
 * 
 * @version $Id$
 */
public interface BoardArticleDao extends GenericDao<BoardArticle, Integer> {
	/**
	 * @param searchVO
	 *            게시물 검색 정보
	 * @return 게시물 페이지 값
	 * @throws Exception
	 */
	public GenericPage<BoardArticle> getPagingList(PagingCondition searchVO) throws Exception;

	// ----- comment
	/**
	 * @param seq
	 *            코멘트 일련번호
	 * @return 코멘트
	 */
	public BoardComment getComment(int seq);

	/**
	 * @param boardItemSeq
	 *            게시물 항목 번호
	 * @return 게시물 항목에 대한 코멘트 목록
	 */
	public List<BoardComment> listComment(int boardItemSeq);

	/**
	 * @param comment
	 *            코멘트 정보
	 */
	public void saveComment(BoardComment comment);

	/**
	 * @param comment
	 *            코멘트
	 */
	public void updateComment(BoardComment comment);

	/**
	 * @param seq
	 *            코멘트 번호
	 */
	public void removeComment(int seq);

	// ----- Attach File

	/**
	 * @param boardItemSeq
	 *            게시물 번호
	 * @return 게시물에 등록된 첨부파일 목록
	 */
	public List<BoardAttachFile> listAttach(int boardItemSeq);

	/**
	 * @param attachFile
	 *            첨부파일 저장
	 */
	public void saveAttachFile(BoardAttachFile attachFile);

	/**
	 * @param seq
	 *            첨부파일 번호
	 */
	public void removeAttachFile(int seq);

}
