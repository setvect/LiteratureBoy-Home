package com.setvect.literatureboy.web;

/**
 * ��⿡ ���� Access ���θ� üũ
 * 
 * @version $Id$
 */
public interface AccessRule {
	/**
	 * @return ���� ���� ����
	 */
	public boolean isAccess();

	/**
	 * @return �ΰ����� ����
	 */
	public int getStatus();
}
