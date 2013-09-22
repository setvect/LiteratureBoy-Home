package com.setvect.literatureboy.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setvect.common.util.GenericPage;
import com.setvect.literatureboy.db.BoardDao;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;
import com.setvect.literatureboy.vo.board.BoardTrackback;

/**
 * @version $Id$
 */
@Service("BoardService")
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	// --------------- 관리

	/**
	 * @param code
	 *            코드
	 * @return 정보
	 */
	public Board getBoard(String code) {
		return boardDao.getBoard(code);
	}

	/**
	 * @param pageCondition
	 * @return 정보 항목
	 */
	public GenericPage<Board> getBoardPagingList(BoardManagerSearch pageCondition) {
		return boardDao.getBoardPagingList(pageCondition);
	}

	/**
	 * @param board
	 */
	public void createBoard(Board board) {
		boardDao.createBoard(board);
	}

	/**
	 * @param board
	 *            게시물 아이디
	 */
	public void updateBoard(Board board) {
		boardDao.updateBoard(board);
	}

	/**
	 * @param code
	 *            게시물 코드
	 */
	public void removeBoard(String code) {
		boardDao.removeBoard(code);
	}

	// --------------- 게시물
	/**
	 * @param articleSeq
	 * @return
	 */
	public BoardArticle getArticle(int articleSeq) {
		return boardDao.getArticle(articleSeq);
	}

	/**
	 * @param pageCondition
	 *            게시물 검색 정보
	 * @return 게시물 페이지 값
	 */
	public GenericPage<BoardArticle> getArticlePagingList(BoardArticleSearch pageCondition) {
		return boardDao.getArticlePagingList(pageCondition);
	}

	/**
	 * 게시물 등록
	 * 
	 * @param article
	 *            게시물
	 */
	public void createArticle(BoardArticle article) {
		boardDao.createArticle(article);
	}

	/**
	 * 답변 등록
	 * 
	 * @param article
	 *            게시물
	 * @param parentId
	 *            부모 게시물
	 */
	public void createArticleReply(BoardArticle article, int parentId) {
		boardDao.createArticleReply(article, parentId);
	}

	/**
	 * idx 관련 연산없음 <br>
	 * 마이그레이션 용
	 * 
	 * @param article
	 *            게시물
	 */
	public void createArticleMigration(BoardArticle article) {
		boardDao.createArticleMigration(article);
	}

	/**
	 * @param article
	 */
	public void updateArticle(BoardArticle article) {
		boardDao.updateArticle(article);
	}

	/**
	 * 조회수 증가
	 * 
	 * @param articleSeq
	 */
	public void updateArticleIncrementHit(int articleSeq) {
		boardDao.updateArticleIncrementHit(articleSeq);
	}

	/**
	 * @param articleSeq
	 */
	public void removeArticle(int articleSeq) {
		boardDao.removeArticle(articleSeq);
	}

	// ----- comment
	/**
	 * @param seq
	 *            코멘트 일련번호
	 * @return 코멘트
	 */
	public BoardComment getComment(int seq) {
		return boardDao.getComment(seq);
	}

	/**
	 * @param boardItemSeq
	 *            게시물 항목 번호
	 * @return 게시물 항목에 대한 코멘트 목록
	 */
	public List<BoardComment> listComment(int boardItemSeq) {
		return boardDao.listComment(boardItemSeq);
	}

	/**
	 * @param comment
	 *            코멘트 정보
	 */
	public void createComment(BoardComment comment) {
		boardDao.createComment(comment);
	}

	/**
	 * @param comment
	 *            코멘트
	 */
	public void updateComment(BoardComment comment) {
		boardDao.updateComment(comment);
	}

	/**
	 * @param seq
	 *            코멘트 번호
	 */
	public void removeComment(int seq) {
		boardDao.removeComment(seq);
	}

	// ----- Attach File

	/**
	 * @param boardItemSeq
	 *            게시물 번호
	 * @return 게시물에 등록된 첨부파일 목록
	 */
	public List<BoardAttachFile> listAttachFile(int boardItemSeq) {
		return boardDao.listAttachFile(boardItemSeq);
	}

	/**
	 * @param attachFile
	 *            첨부파일 저장
	 */
	public void createAttachFile(BoardAttachFile attachFile) {
		boardDao.createAttachFile(attachFile);
	}

	/**
	 * @param seq
	 *            첨부파일 번호
	 */
	public void removeAttachFile(int seq) {
		boardDao.removeAttachFile(seq);
	}

	// ----- Attach File
	/**
	 * @param boardItemSeq
	 *            게시물 번호
	 * @return 게시물에 등록된 트래백 목로
	 */
	public List<BoardTrackback> listTrackback(int boardItemSeq) {
		return boardDao.listTrackback(boardItemSeq);
	}

	/**
	 * 트래백 등록
	 * 
	 * @param trackback
	 *            트래백 정보
	 */
	public void createTrackback(BoardTrackback trackback) {
		boardDao.createTrackback(trackback);
	}

	/**
	 * 트래백 삭제
	 * 
	 * @param seq
	 *            트래백 번호
	 */
	public void removeTrackback(int seq) {
		boardDao.removeTrackback(seq);
	}

}
