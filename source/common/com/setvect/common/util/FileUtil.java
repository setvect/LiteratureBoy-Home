package com.setvect.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.commons.io.FileUtils;

import com.setvect.common.date.DateUtil;

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

	/**
	 * 오늘 날짜(년/월/일)통해 디렉토리를 만듬
	 * 
	 * @param strBasePath
	 *            기준 디렉토리
	 * @return 만들어진 디렉토리
	 */
	public static File makeDayDir(String strBasePath) {
		String year = NumberFormat.getNumberString("0000", DateUtil.getYear());
		String month = NumberFormat.getNumberString("00", DateUtil.getMonth());
		String day = NumberFormat.getNumberString("00", DateUtil.getDay());
		String dir;

		dir = strBasePath;
		// 디렉토리가 있는지
		if (!FileUtil.isDirExists(dir)) {
			throw new RuntimeException(strBasePath + " directory is not exist!");
		}

		dir += "/" + year;
		dir += "/" + month;
		dir += "/" + day;
		File f = new File(dir);
		if (!f.exists()) {
			boolean make = f.mkdirs();
			if (!make) {
				throw new RuntimeException("디렉토리 생성 실패 : " + f.getPath());
			}
		}
		return f;
	}

	/**
	 * 디렉토리 존재 여부
	 * 
	 * @param path
	 *            File명(Full Path)
	 * @return bool 성공(true), 실패(false)
	 */
	public static boolean isDirExists(String path) {
		boolean bool = false;
		File f = new File(path);

		if (f.exists() && f.isDirectory()) {
			bool = true;
		}

		return bool;
	}

	/**
	 * 디렉토리 만드는 메소드
	 * 
	 * @param strDir
	 *            만들어진 디렉토리 이름 (풀 패스)
	 * @return 성공했는지 안했는지 true면 성공 false 실패
	 */
	public static boolean makeDir(String strDir) {
		File makeDir = new File(strDir);
		return makeDir.mkdir();
	}

	/**
	 * File 삭제
	 * 
	 * @param path
	 *            File명(Full Path)
	 * @return bool 성공(true), 실패(false)
	 */
	public static boolean delFile(String path) {
		boolean bool = false;
		File f = new File(path);

		// 파일 일때만 삭제를 한다. 괜히 디렉토리 까지 지울 수 있다.
		if (f.exists() && f.isFile()) {
			try {
				bool = f.delete();
			} catch (SecurityException e) {
				bool = false;
			}
		}

		return bool;
	}

	/**
	 * File 존재 여부
	 * 
	 * @param path
	 *            File명(Full Path)
	 * @return bool 성공(true), 실패(false)
	 */
	public static boolean isExists(String path) {
		boolean bool = false;
		File f = new File(path);

		if (f.exists() && f.isFile()) {
			bool = true;
		}

		return bool;
	}

	/**
	 * @param saveFile
	 * @param stringData
	 * @param charset
	 */
	public static void saveString2File(File saveFile, String stringData, String charset) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			fos = new FileOutputStream(saveFile);
			osw = new OutputStreamWriter(fos, charset);
			osw.write(stringData);
			osw.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException ignor) {
				}
			}
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException ignor) {
				}
			}
		}
	}
}
