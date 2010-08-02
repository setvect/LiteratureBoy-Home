package com.setvect.literatureboy.db;

import java.util.List;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.PagingCondition;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;

/**
 * �Խù�, ÷������, �ڸ�Ʈ DAO
 * 
 * @version $Id$
 */
public interface BoardArticleDao {

	// --------------- ����

	public Board getManager(String code);

	/**
	 * @param searchVO
	 * @return �Խ��ǻ��� ���� �׸�
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

	// --------------- �Խù�
	/**
	 * @param articleSeq
	 * @return
	 */
	public BoardArticle getArticle(int articleSeq);

	/**
	 * @param searchVO
	 *            �Խù� �˻� ����
	 * @return �Խù� ������ ��
	 * @throws Exception
	 */
	public GenericPage<BoardArticle> getArticlePagingList(PagingCondition searchVO) throws Exception;

	/**
	 * �Խù� ���
	 * 
	 * @param article
	 *            �Խù�
	 * @throws Exception
	 */
	public void createArticle(BoardArticle article) throws Exception;

	/**
	 * �亯 ���
	 * 
	 * @param article
	 *            �Խù�
	 * @param parentId
	 *            �θ� �Խù�
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

}
