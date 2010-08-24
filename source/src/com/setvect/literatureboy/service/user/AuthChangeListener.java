package com.setvect.literatureboy.service.user;

import java.util.Collection;

import com.setvect.literatureboy.vo.user.Auth;
import com.setvect.literatureboy.vo.user.AuthMap;

/**
 * 권한 수정 여부
 * 
 * @version $Id$
 */
public interface AuthChangeListener {
	/**
	 * 권한 정보 수정
	 * 
	 * @param auth
	 *            권한 목록
	 */
	public void updateAuth(Collection<Auth> auth);

	/**
	 * 권한 매핑 정보 수정
	 * 
	 * @param authMap
	 *            권한 맴핑 정보
	 */
	public void updateAuthMap(Collection<AuthMap> authMap);

}
