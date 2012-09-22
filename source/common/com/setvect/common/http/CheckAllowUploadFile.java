package com.setvect.common.http;

/**
 * 업로드 파일 유형 체크
 * 
 * @version $Id$
 */
public interface CheckAllowUploadFile {
	/**
	 * @param filename
	 *            업로드 파일 명
	 * @return true 파일 업로드 허락, false 파일 업로드 금지
	 */
	public boolean check(String filename);
}
