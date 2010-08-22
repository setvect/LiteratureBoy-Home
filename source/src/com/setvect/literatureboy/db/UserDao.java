package com.setvect.literatureboy.db;

import com.setvect.common.util.GenericPage;
import com.setvect.literatureboy.service.user.UserSearch;
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
	public GenericPage<User> getPagingList(UserSearch paging);

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
}
