package com.setvect.common.http;

/**
 * ���ε� ���� ���� üũ
 * 
 * @version $Id$
 */
public interface CheckAllowUploadFile {
	/**
	 * @param filename
	 *            ���ε� ���� ��
	 * @return true ���� ���ε� ���, false ���� ���ε� ����
	 */
	public boolean check(String filename);
}
