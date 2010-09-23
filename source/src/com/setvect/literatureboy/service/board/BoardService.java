package com.setvect.literatureboy.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	/** �� ��Ʈ�� �������� ���� ��� */
	public static final String SAVE_PATH = EnvirmentProperty
			.getString("com.setvect.literatureboy.board.file_upload_dir");

	@Autowired
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
	 */
	public GenericPage<BoardArticle> getArticlePagingList(BoardArticleSearch pageCondition) {
		return boardDao.getArticlePagingList(pageCondition);
	}

	/**
	 * �Խù� ���
	 * 
	 * @param article
	 *            �Խù�
	 */
	public void createArticle(BoardArticle article) {
		boardDao.createArticle(article);
	}

	/**
	 * �亯 ���
	 * 
	 * @param article
	 *            �Խù�
	 * @param parentId
	 *            �θ� �Խù�
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
	 * ��ȸ�� ����
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
