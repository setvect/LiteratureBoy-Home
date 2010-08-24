package com.setvect.literatureboy.service.user;

import java.util.Collection;

import com.setvect.literatureboy.vo.user.Auth;
import com.setvect.literatureboy.vo.user.AuthMap;

/**
 * ���� ���� ����
 * 
 * @version $Id$
 */
public interface AuthChangeListener {
	/**
	 * ���� ���� ����
	 * 
	 * @param auth
	 *            ���� ���
	 */
	public void updateAuth(Collection<Auth> auth);

	/**
	 * ���� ���� ���� ����
	 * 
	 * @param authMap
	 *            ���� ���� ����
	 */
	public void updateAuthMap(Collection<AuthMap> authMap);

}
