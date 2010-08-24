package com.setvect.literatureboy.service.user;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.setvect.literatureboy.vo.user.Auth;
import com.setvect.literatureboy.vo.user.AuthMap;

/**
 * 권한 맴핑 정보를 캐싱하고 있다. 회원이 특정 URL과 mode 파라미터에 접근해 접근 허가 여부를 제공함
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

}
