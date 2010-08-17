package com.setvect.common.util;

import org.apache.commons.io.FileUtils;

public class FileUtil extends FileUtils {

	/**
	 * 확장자를 제외한 파일명 리턴 <br>
	 * 만약 파일명에 경로까지 포함되면 해당 경로는 없에 버린다. <br>
	 * 예) abcdef.txt => abcdef<br>
	 * ads.sdfe.xafe.gif => ads.sdfe.xafe
	 * 
	 * @param filename
	 *            파일명
	 * @return 파일 확장자를 제외한 파일명 리턴
	 */
	public static String getFilenameWithoutExt(String filename) {

		int path = filename.lastIndexOf("/");
		if (path == -1) {
			path = filename.lastIndexOf("\\");
		}
		if (path != -1) {
			filename = filename.substring(path + 1);
		}
		int len = filename.length();
		/* 마지막에 붙어 있는 점을 위치를 . */
		int end = filename.lastIndexOf('.', len);

		/* 확장자에 대한 아이콘파일 전송. */
		return (end < 0) ? filename : filename.substring(0, end);
	}

	/**
	 * 확장자 구하기
	 * 
	 * @param filename
	 *            filename
	 * @return 확장자 (.) 붙음 ex) .jpg, .hwp
	 */
	public static String getExt(String filename) {
		int len = filename.length();

		/* 마지막에 붙어 있는 점을 위치를 . */
		int start = filename.lastIndexOf('.', len);

		/* 확장자에 대한 아이콘파일 전송. */
		return (start < 0) ? "" : filename.substring(start);
	}

}
