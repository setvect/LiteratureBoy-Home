package com.setvect.literatureboy.service.board;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.setvect.common.util.GenericPage;
import com.setvect.literatureboy.config.EnvirmentProperty;
import com.setvect.literatureboy.db.BoardDao;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;

/**
 * @version $Id$
 */
@Service
public class BoardService {
	/** 웹 루트를 기준으로 저장 경로 */
	public static final String SAVE_PATH = EnvirmentProperty
			.getString("com.setvect.literatureboy.board.file_upload_dir");

	@Resource
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

}
