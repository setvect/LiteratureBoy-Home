package com.setvect.literatureboy.db.h2;

import org.springframework.stereotype.Service;

import com.setvect.literatureboy.db.common.AbstractUserDao;
import com.setvect.literatureboy.vo.user.User;

/**
 * H2 일정
 * 
 * @version $Id: H2MemoDao.java 41 2010-08-02 07:22:59Z setvect@naver.com $
 */
@Service
public class H2UserDao extends AbstractUserDao<User, String> {
}
