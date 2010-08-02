package com.setvect.literatureboy.db;

import java.util.List;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.PagingCondition;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;

/**
 * 게시물, 첨부파일, 코멘트 DAO
 * 
 * @version $Id$
 */
public interface BoardArticleDao {

	// --------------- 관리

	public Board getManager(String code);

	/**
	 * @param searchVO
	 * @return 게시판생성 정보 항목
	 * @throws Exception
	 */
	public GenericPage<Board> getManagerPagingList(PagingCondition searchVO) throws Exception;

	/**
	 * @param board
	 * @throws Exception
	 */
	public void createManager(Board board) throws Exception;

	/**
	 * @param article
	 */
	public void updateManager(Board board);

	/**
	 * @param articleSeq
	 */
	public void removeManager(String code);

	// --------------- 게시물
	/**
	 * @param articleSeq
	 * @return
	 */
	public BoardArticle getArticle(int articleSeq);

	/**
	 * @param searchVO
	 *            게시물 검색 정보
	 * @return 게시물 페이지 값
	 * @throws Exception
	 */
	public GenericPage<BoardArticle> getArticlePagingList(PagingCondition searchVO) throws Exception;

	/**
	 * 게시물 등록
	 * 
	 * @param article
	 *            게시물
	 * @throws Exception
	 */
	public void createArticle(BoardArticle article) throws Exception;

	/**
	 * 답변 등록
	 * 
	 * @param article
	 *            게시물
	 * @param parentId
	 *            부모 게시물
	 * @throws Exception
	 */
	public void createArticleReply(BoardArticle article, int parentId) throws Exception;

	/**
	 * @param article
	 */
	public void updateArticle(BoardArticle article);

	/**
	 * @param articleSeq
	 */
	public void removeArticle(int articleSeq);

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
	public void createComment(BoardComment comment);

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
	public List<BoardAttachFile> listAttachFile(int boardItemSeq);

	/**
	 * @param attachFile
	 *            첨부파일 저장
	 */
	public void createAttachFile(BoardAttachFile attachFile);

	/**
	 * @param seq
	 *            첨부파일 번호
	 */
	public void removeAttachFile(int seq);

}
