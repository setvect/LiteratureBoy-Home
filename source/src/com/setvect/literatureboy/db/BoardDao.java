package com.setvect.literatureboy.db;

import java.util.List;

import com.setvect.common.util.GenericPage;
import com.setvect.literatureboy.service.board.BoardArticleSearch;
import com.setvect.literatureboy.service.board.BoardManagerSearch;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;
import com.setvect.literatureboy.vo.board.BoardTrackback;

/**
 * 게시물, 첨부파일, 코멘트 DAO
 * 
 * @version $Id$
 */
public interface BoardDao {

	// --------------- 관리

	public Board getBoard(String code);

	/**
	 * @param pageCondition
	 * @return 게시판생성 정보 항목
	 */
	public GenericPage<Board> getBoardPagingList(BoardManagerSearch pageCondition);

	/**
	 * @param board
	 */
	public void createBoard(Board board);

	/**
	 * @param article
	 */
	public void updateBoard(Board board);

	/**
	 * @param articleSeq
	 */
	public void removeBoard(String code);

	// --------------- 게시물
	/**
	 * @param articleSeq
	 * @return
	 */
	public BoardArticle getArticle(int articleSeq);

	/**
	 * @param pageCondition
	 *            게시물 검색 정보
	 * @return 게시물 페이지 값
	 */
	public GenericPage<BoardArticle> getArticlePagingList(BoardArticleSearch pageCondition);

	/**
	 * 게시물 등록
	 * 
	 * @param article
	 *            게시물
	 */
	public void createArticle(BoardArticle article);

	/**
	 * 답변 등록
	 * 
	 * @param article
	 *            게시물
	 * @param parentId
	 *            부모 게시물
	 */
	public void createArticleReply(BoardArticle article, int parentId);

	/**
	 * idx 관련 연산없음 <br>
	 * 마이그레이션 용
	 * 
	 * @param article
	 *            게시물 정보
	 */
	public void createArticleMigration(BoardArticle article);

	/**
	 * @param article
	 */
	public void updateArticle(BoardArticle article);

	/**
	 * @param articleSeq
	 */
	public void updateArticleIncrementHit(int articleSeq);

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

	// ----- Trackback 
	/**
	 * @param boardItemSeq
	 *            게시물 번호
	 * @return 게시물에 등록된 트래백 목로
	 */
	public List<BoardTrackback> listTrackback(int boardItemSeq);

	/**
	 * 트래백 등록
	 * 
	 * @param trackback
	 *            트래백 정보
	 */
	public void createTrackback(BoardTrackback trackback);

	/**
	 * 트래백 삭제
	 * 
	 * @param seq
	 *            트래백 번호
	 */
	public void removeTrackback(int seq);

}
