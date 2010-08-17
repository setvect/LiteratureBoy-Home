package com.setvect.literatureboy.service.board;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.setvect.common.util.GenericPage;
import com.setvect.literatureboy.db.BoardDao;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;

/**
 * @version $Id$
 */
@Service
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class BoardService {

	@Resource
	private BoardDao boardDao;

	// --------------- ����

	/**
	 * @param code
	 *            �ڵ�
	 * @return ����
	 */
	public Board getBoard(String code) {
		return boardDao.getBoard(code);
	}

	/**
	 * @param pageCondition
	 * @return ���� �׸�
	 * @throws Exception
	 */
	public GenericPage<Board> getBoardPagingList(BoardManagerSearch pageCondition) throws Exception {
		return boardDao.getBoardPagingList(pageCondition);
	}

	/**
	 * @param board
	 * @throws Exception
	 */
	public void createBoard(Board board) throws Exception {
		boardDao.createBoard(board);
	}

	/**
	 * @param board
	 *            �Խù� ���̵�
	 */
	public void updateBoard(Board board) {
		boardDao.updateBoard(board);
	}

	/**
	 * @param code
	 *            �Խù� �ڵ�
	 */
	public void removeBoard(String code) {
		boardDao.removeBoard(code);
	}

	// --------------- �Խù�
	/**
	 * @param articleSeq
	 * @return
	 */
	public BoardArticle getArticle(int articleSeq) {
		return boardDao.getArticle(articleSeq);
	}

	/**
	 * @param pageCondition
	 *            �Խù� �˻� ����
	 * @return �Խù� ������ ��
	 * @throws Exception
	 */
	public GenericPage<BoardArticle> getArticlePagingList(BoardArticleSearch pageCondition) throws Exception {
		return boardDao.getArticlePagingList(pageCondition);
	}

	/**
	 * �Խù� ���
	 * 
	 * @param article
	 *            �Խù�
	 * @throws Exception
	 */
	public void createArticle(BoardArticle article) throws Exception {
		boardDao.createArticle(article);
	}

	/**
	 * �亯 ���
	 * 
	 * @param article
	 *            �Խù�
	 * @param parentId
	 *            �θ� �Խù�
	 * @throws Exception
	 */
	public void createArticleReply(BoardArticle article, int parentId) throws Exception {
		boardDao.createArticleReply(article, parentId);
	}

	/**
	 * @param article
	 */
	public void updateArticle(BoardArticle article) {
		boardDao.updateArticle(article);
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
	 *            �ڸ�Ʈ �Ϸù�ȣ
	 * @return �ڸ�Ʈ
	 */
	public BoardComment getComment(int seq) {
		return boardDao.getComment(seq);
	}

	/**
	 * @param boardItemSeq
	 *            �Խù� �׸� ��ȣ
	 * @return �Խù� �׸� ���� �ڸ�Ʈ ���
	 */
	public List<BoardComment> listComment(int boardItemSeq) {
		return boardDao.listComment(boardItemSeq);
	}

	/**
	 * @param comment
	 *            �ڸ�Ʈ ����
	 */
	public void createComment(BoardComment comment) {
		boardDao.createComment(comment);
	}

	/**
	 * @param comment
	 *            �ڸ�Ʈ
	 */
	public void updateComment(BoardComment comment) {
		boardDao.updateComment(comment);
	}

	/**
	 * @param seq
	 *            �ڸ�Ʈ ��ȣ
	 */
	public void removeComment(int seq) {
		boardDao.removeComment(seq);
	}

	// ----- Attach File

	/**
	 * @param boardItemSeq
	 *            �Խù� ��ȣ
	 * @return �Խù��� ��ϵ� ÷������ ���
	 */
	public List<BoardAttachFile> listAttachFile(int boardItemSeq) {
		return boardDao.listAttachFile(boardItemSeq);
	}

	/**
	 * @param attachFile
	 *            ÷������ ����
	 */
	public void createAttachFile(BoardAttachFile attachFile) {
		boardDao.createAttachFile(attachFile);
	}

	/**
	 * @param seq
	 *            ÷������ ��ȣ
	 */
	public void removeAttachFile(int seq) {
		boardDao.removeAttachFile(seq);
	}
}
