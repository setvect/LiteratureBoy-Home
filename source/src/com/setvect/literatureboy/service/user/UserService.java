package com.setvect.literatureboy.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.setvect.common.util.GenericPage;
import com.setvect.literatureboy.db.UserDao;
import com.setvect.literatureboy.vo.user.Auth;
import com.setvect.literatureboy.vo.user.AuthMap;
import com.setvect.literatureboy.vo.user.AuthMapKey;
import com.setvect.literatureboy.vo.user.User;

/**
 * @version $Id: MemoService.java 63 2010-08-16 12:24:44Z setvect@naver.com $
 */
@Service
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class UserService {
	/** DB 컨트롤 인스턴스 */
	@Resource
	private UserDao userDao;

	// ---------------- 사용자
	/**
	 * @param id
	 *            회원 아이디
	 * @return 아이디에 맞는 회원정보 없으면 null
	 * @throws Exception
	 */
	public User getUser(String id) throws Exception {
		return userDao.getUser(id);

	}

	public GenericPage<User> getPageList(UserSearch searchVo) throws Exception {
		return userDao.getUserPagingList(searchVo);
	}

	/**
	 * 회원 정보 등록
	 * 
	 * @param user
	 *            회원
	 * @throws Exception
	 */
	public void createUser(User user) throws Exception {
		userDao.createUser(user);
	}

	/**
	 * @param user
	 * @throws Exception
	 */
	public void updateUser(User user) throws Exception {
		userDao.updateUser(user);
	}

	/**
	 * @param id
	 * @throws Exception
	 */
	public void removeUser(String id) throws Exception {
		userDao.removeUser(id);
	}

	// ---------------- 권한
	public Auth getAuth(int authSeq) {
		return userDao.getAuth(authSeq);
	}

	public GenericPage<Auth> getAuthPagingList(AuthSearch search) {
		return userDao.getAuthPagingList(search);
	}

	public void createAuth(Auth auth) {
		userDao.createAuth(auth);
	}

	public void updateAuth(Auth auth) {
		userDao.updateAuth(auth);
	}

	public void removeAuth(int authSeq) {
		userDao.removeAuth(authSeq);
	}

	// ---------------- 권한 맵핑
	public AuthMap getAuthMap(AuthMapKey key) {
		return userDao.getAuthMap(key);
	}

	public GenericPage<AuthMap> getAuthMapPagingList(AuthMapSearch search) {
		return userDao.getAuthMapPagingList(search);
	}

	public void createAuthMap(AuthMap authMap) {
		userDao.createAuthMap(authMap);
	}

	public void removeAuthMap(AuthMapKey key) {
		userDao.removeAuthMap(key);
	}

}
