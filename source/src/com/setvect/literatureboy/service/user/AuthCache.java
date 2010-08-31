package com.setvect.literatureboy.service.user;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.setvect.literatureboy.vo.user.Auth;
import com.setvect.literatureboy.vo.user.AuthMap;

/**
 * ���� ���� ������ ĳ���ϰ� �ִ�. ȸ���� Ư�� URL�� mode �Ķ���Ϳ� ������ ���� �㰡 ���θ� ������
 * 
 * @version $Id$
 */
@Service
public class AuthCache implements AuthChangeListener {

	private static Collection<Auth> authCache;

	private static Collection<AuthMap> authMapCache;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.service.user.AuthChangeListener#updateAuth(java.util.List)
	 */
	public void updateAuth(Collection<Auth> auth) {
		authCache = auth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.setvect.literatureboy.service.user.AuthChangeListener#updateAuthMap(java.util.List)
	 */
	public void updateAuthMap(Collection<AuthMap> authMap) {
		authMapCache = authMap;
	}

	/**
	 * @return the authCache
	 */
	public static Collection<Auth> getAuthCache() {
		return authCache;
	}

	/**
	 * @return the authMapCache
	 */
	public static Collection<AuthMap> getAuthMapCache() {
		return authMapCache;
	}
}
