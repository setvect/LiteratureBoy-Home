package com.setvect.literatureboy;

import com.setvect.literatureboy.config.EnvirmentProperty;

/**
 * 프로젝트 상용 정의
 * 
 * @version $Id$
 */
public class ProjectConstant {
	/** 업로드 기준 URL */
	public static final String ATTACH_UPLOAD_URL = EnvirmentProperty
			.getString("com.setvect.literatureboy.board.file_upload_url");
	/** 웹 루트를 기준으로 저장 경로 */
	public static final String SAVE_PATH = EnvirmentProperty
			.getString("com.setvect.literatureboy.board.file_upload_dir");
}
