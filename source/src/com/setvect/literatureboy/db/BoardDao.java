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
 * �Խù�, ÷������, �ڸ�Ʈ DAO
 * 
 * @version $Id$
 */
public interface BoardDao {

	// --------------- ����

	public Board getBoard(String code);

	/**
	 * @param pageCondition
	 * @return �Խ��ǻ��� ���� �׸�
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

	// --------------- �Խù�
	/**
	 * @param articleSeq
	 * @return
	 */
	public BoardArticle getArticle(int articleSeq);

	/**
	 * @param pageCondition
	 *            �Խù� �˻� ����
	 * @return �Խù� ������ ��
	 */
	public GenericPage<BoardArticle> getArticlePagingList(BoardArticleSearch pageCondition);

	/**
	 * �Խù� ���
	 * 
	 * @param article
	 *            �Խù�
	 */
	public void createArticle(BoardArticle article);

	/**
	 * �亯 ���
	 * 
	 * @param article
	 *            �Խù�
	 * @param parentId
	 *            �θ� �Խù�
	 */
	public void createArticleReply(BoardArticle article, int parentId);

	/**
	 * idx ���� ������� <br>
	 * ���̱׷��̼� ��
	 * 
	 * @param article
	 *            �Խù� ����
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
	 *            �ڸ�Ʈ �Ϸù�ȣ
	 * @return �ڸ�Ʈ
	 */
	public BoardComment getComment(int seq);

	/**
	 * @param boardItemSeq
	 *            �Խù� �׸� ��ȣ
	 * @return �Խù� �׸� ���� �ڸ�Ʈ ���
	 */
	public List<BoardComment> listComment(int boardItemSeq);

	/**
	 * @param comment
	 *            �ڸ�Ʈ ����
	 */
	public void createComment(BoardComment comment);

	/**
	 * @param comment
	 *            �ڸ�Ʈ
	 */
	public void updateComment(BoardComment comment);

	/**
	 * @param seq
	 *            �ڸ�Ʈ ��ȣ
	 */
	public void removeComment(int seq);

	// ----- Attach File

	/**
	 * @param boardItemSeq
	 *            �Խù� ��ȣ
	 * @return �Խù��� ��ϵ� ÷������ ���
	 */
	public List<BoardAttachFile> listAttachFile(int boardItemSeq);

	/**
	 * @param attachFile
	 *            ÷������ ����
	 */
	public void createAttachFile(BoardAttachFile attachFile);

	/**
	 * @param seq
	 *            ÷������ ��ȣ
	 */
	public void removeAttachFile(int seq);

	// ----- Trackback 
	/**
	 * @param boardItemSeq
	 *            �Խù� ��ȣ
	 * @return �Խù��� ��ϵ� Ʈ���� ���
	 */
	public List<BoardTrackback> listTrackback(int boardItemSeq);

	/**
	 * Ʈ���� ���
	 * 
	 * @param trackback
	 *            Ʈ���� ����
	 */
	public void createTrackback(BoardTrackback trackback);

	/**
	 * Ʈ���� ����
	 * 
	 * @param seq
	 *            Ʈ���� ��ȣ
	 */
	public void removeTrackback(int seq);

}
