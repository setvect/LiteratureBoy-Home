package com.setvect.literatureboy.service.board;

import java.util.List;

import com.setvect.common.util.SearchListVo;
import com.setvect.common.util.StringUtilAd;

/**
 * �Խù� ���� ��� ����¡ �� �˻� ����
 * 
 * @version $Id$
 */
public class BoardArticleSearch extends SearchListVo {

	/** */
	private static final long serialVersionUID = 6832353125378512460L;

	private String searchName;
	private String searchCode;
	/**
	 * ������ �Խù� ��ȸ<br>
	 * �� �ʵ忡 ���� ������ searchCode ���� ������
	 */
	private List<String> searchCodes;
	private String searchTitle;
	private String searchContent;

	/** ������ �Խù��� ���� �� ������ */
	private boolean deleteView;

	public BoardArticleSearch(int currentPage) {
		super(currentPage);
	}

	/**
	 * @return the searchName
	 */
	public String getSearchName() {
		return searchName;
	}

	/**
	 * @param searchName
	 *            the searchName to set
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * ��ȸ�� �Խ��� �ڵ�<br>
	 * 
	 * @return the searchCode
	 */
	public String getSearchCode() {
		return searchCode;
	}

	/**
	 * ��ȸ�� �Խ��� �ڵ�<br>
	 * 
	 * @param searchCode
	 *            the searchCode to set
	 */
	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}

	/**
	 * ������ �Խù� ��ȸ
	 * 
	 * @return the searchCodes
	 */
	public List<String> getSearchCodes() {
		return searchCodes;
	}

	/**
	 * ������ �Խù� ��ȸ
	 * 
	 * @param searchCodes
	 *            the searchCodes to set
	 */
	public void setSearchCodes(List<String> searchCodes) {
		this.searchCodes = searchCodes;
	}

	/**
	 * @return the searchTitle
	 */
	public String getSearchTitle() {
		return searchTitle;
	}

	/**
	 * @param searchTitle
	 *            the searchTitle to set
	 */
	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	/**
	 * @return the searchContent
	 */
	public String getSearchContent() {
		return searchContent;
	}

	/**
	 * @param searchContent
	 *            the searchContent to set
	 */
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	/**
	 * ������ �Խù��� ���� �� ������. true ���� �Խù��� ������.
	 * 
	 * @return the deleteView
	 */
	public boolean isDeleteView() {
		return deleteView;
	}

	/**
	 * ������ �Խù��� ���� �� ������. true ���� �Խù��� ������.
	 * 
	 * @param deleteView
	 *            the deleteView to set
	 */
	public void setDeleteView(boolean deleteView) {
		this.deleteView = deleteView;
	}

	/**
	 * @return �˻� �ܾ��� ���� �ִ� �ϳ��� ��ȯ. �ƹ��� ������ null. 2�� �̻� ���� ���� ��� ��� ��ȯ ���� ��
	 */
	public String getWord() {

		if (!StringUtilAd.isEmpty(searchName)) {
			return searchName;
		}

		if (!StringUtilAd.isEmpty(searchTitle)) {
			return searchTitle;
		}

		if (!StringUtilAd.isEmpty(searchContent)) {
			return searchContent;
		}
		return null;
	}

}
