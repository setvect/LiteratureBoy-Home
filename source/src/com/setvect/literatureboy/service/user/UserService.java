package com.setvect.literatureboy.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.setvect.common.util.GenericPage;
import com.setvect.literatureboy.db.UserDao;
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

	/**
	 * @param id
	 *            회원 아이디
	 * @return 아이디에 맞는 회원정보 없으면 null
	 * @throws Exception
	 */
	public User getUser(String id) throws Exception {
		return userDao.getUser(id);

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

	public GenericPage<User> getPageList(UserSearch searchVo) throws Exception {
		return userDao.getUserPagingList(searchVo);
	}

}
