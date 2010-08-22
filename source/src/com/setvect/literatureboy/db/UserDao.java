package com.setvect.literatureboy.db;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.SearchListVo;
import com.setvect.literatureboy.service.user.AuthMapSearch;
import com.setvect.literatureboy.service.user.UserSearch;
import com.setvect.literatureboy.vo.user.Auth;
import com.setvect.literatureboy.vo.user.AuthMap;
import com.setvect.literatureboy.vo.user.AuthMapKey;
import com.setvect.literatureboy.vo.user.User;

/**
 * 게시물, 첨부파일, 코멘트 DAO
 * 
 * @version $Id: BoardDao.java 63 2010-08-16 12:24:44Z setvect@naver.com $
 */
public interface UserDao {
	// ---------------- 사용자
	/**
	 * @param userId
	 * @return
	 */
	public User getUser(String userId);

	/**
	 * @param paging
	 * @return
	 * @throws Exception
	 */
	public GenericPage<User> getUserPagingList(UserSearch paging);

	/**
	 * @param user
	 * @throws Exception
	 */
	public void createUser(User user);

	/**
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * @param userId
	 */
	public void removeUser(String userId);

	// ---------------- 권한
	/**
	 * @param authSeq
	 * @return
	 */
	public Auth getAuth(int authSeq);

	/**
	 * @param paging
	 * @return
	 * @throws Exception
	 */
	public GenericPage<Auth> getAuthPagingList(SearchListVo paging);

	/**
	 * @param auth
	 * @throws Exception
	 */
	public void createAuth(Auth auth);

	/**
	 * @param auth
	 */
	public void updateAuth(Auth auth);

	/**
	 * @param authSeq
	 */
	public void removeAuth(int authSeq);

	// ---------------- 권한 맵핑
	/**
	 * @param key
	 * @return
	 */
	public AuthMap getAuthMap(AuthMapKey key);

	/**
	 * @param paging
	 * @return
	 * @throws Exception
	 */
	public GenericPage<AuthMap> getAuthMapPagingList(AuthMapSearch paging);

	/**
	 * @param auth
	 * @throws Exception
	 */
	public void createAuthMap(AuthMap authMap);

	/**
	 * @param key
	 */
	public void removeAuthMap(AuthMapKey key);

}
