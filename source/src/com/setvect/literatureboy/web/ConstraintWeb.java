package com.setvect.literatureboy.web;

import java.util.List;

import com.setvect.literatureboy.config.EnvirmentProperty;

/**
 * Web UI에서 사용되는 상수 모음
 * 
 * @version $Id$
 */
public class ConstraintWeb {
	/** 문학소년: 기본 틀을 가진 뷰 이름 */
	public final static String LITERATUREBOY_LAYOUT = "literatureboy/layout";

	/** 문학소년(모바일): 기본 틀을 가진 뷰 이름 */
	public final static String MOBILE_LAYOUT = "m/layout";

	/** 개발: 기본 틀을 가진 뷰 이름 */
	public final static String DEVLOP_LAYOUT = "devlop/layout";

	/** 업로드 기준 URL */
	public final static String UPLOAD_URL_BASE = EnvirmentProperty
			.getString("com.setvect.literatureboy.file_upload_url");

	public enum AttributeKey {
		// jsp에 전달할 인쿨루드 페이지 정보
		INCLUDE_PAGE,
		// 서비스 주소 저장
		SERVLET_URL,
		// 게시판 전체 정보
		BOARD_ITEMS,
		// 상위 컨트롤러에서 전달하는 뷰 모델 
		MODEL_VIEW,

	}

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

	/** 로그인 attribute 키값 */
	public static final String USER_SESSION_KEY = "_user_session_key";

	/** 리턴 URL 파라미터 이름 */
	public static final String RETURN_URL = "returnUrl";

	/** 메인화면 게시판 코드 */
	public static final String MAIN_BOARD = "BDAAAA00";

}
