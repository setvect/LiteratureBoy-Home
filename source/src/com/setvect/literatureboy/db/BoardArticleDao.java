package com.setvect.literatureboy.db;

import java.util.List;

import anyframe.core.generic.dao.GenericDao;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.PagingCondition;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;

/**
 * �Խù�, ÷������, �ڸ�Ʈ DAO
 * 
 * @version $Id$
 */
public interface BoardArticleDao extends GenericDao<BoardArticle, Integer> {
	/**
	 * @param searchVO
	 *            �Խù� �˻� ����
	 * @return �Խù� ������ ��
	 * @throws Exception
	 */
	public GenericPage<BoardArticle> getPagingList(PagingCondition searchVO) throws Exception;

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
	public void saveComment(BoardComment comment);

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
	public List<BoardAttachFile> listAttach(int boardItemSeq);

	/**
	 * @param attachFile
	 *            ÷������ ����
	 */
	public void saveAttachFile(BoardAttachFile attachFile);

	/**
	 * @param seq
	 *            ÷������ ��ȣ
	 */
	public void removeAttachFile(int seq);

}
