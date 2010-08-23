package com.setvect.literatureboy.web;

import java.util.List;

import com.setvect.literatureboy.config.EnvirmentProperty;

/**
 * Web UI에서 사용되는 상수 모음
 * 
 * @version $Id$
 */
public class ConstraintWeb {
	/** 기본 틀을 가진 뷰 이름 */
	public final static String INDEX_VIEW = "index";

	/** jsp에 전달할 인쿨루드 페이지 정보를 답는 attribute key */
	public final static String INCLUDE_PAGE = "include_page";

	/** 서비스 주소 저장 Attribute Key */
	public static final String SERVLET_URL = "controller_url";

	/** 업로드 파일 확장자 */
	public final static String[] ALLOW_UPLOAD_FILE;
	static {
		@SuppressWarnings("unchecked")
		List<String> s = EnvirmentProperty.getList("com.setvect.literatureboy.allow_upload_file");
		ALLOW_UPLOAD_FILE = new String[s.size()];
		for (int i = 0; i < s.size(); i++) {
			ALLOW_UPLOAD_FILE[i] = s.get(i).toLowerCase().trim();
		}
	}

	/** 패스워드 암호화 알고리즘 */
	public final static String PASSWD_ALGORITHM = "MD5";

	/** 로그인 쿠키 키값 */
	public static final String USER_COOKIE_KEY = "_user_cookie_key";

	/** 로그인 attribute 키값*/
	public static final String USER_SESSION_KEY = "_user_session_key";

}
