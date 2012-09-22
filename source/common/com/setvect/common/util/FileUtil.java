package com.setvect.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	 * 확장자 구하기. 쩜(.) 포함하지 않음
	 * 
	 * @param filename
	 *            filename
	 * @return 붙음 ex) jpg, hwp
	 */
	public static String getExtWithoutDot(String filename) {
		int len = filename.length();

		/* 마지막에 붙어 있는 점을 위치를 . */
		int start = filename.lastIndexOf('.', len);

		/* 확장자에 대한 아이콘파일 전송. */
		return (start < 0) ? "" : filename.substring(start + 1);
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
	 * 디렉토리를 탐색하여 원하는 파일을 찾음
	 * 
	 * @param baseDir
	 *            파일 탐색 시작 지점
	 * 
	 * @param subDirectory
	 *            하위 디렉토리 탐색 여부
	 * @param exts
	 *            확장자 <br>
	 * @return 결과 파일 목록
	 */
	public static List<File> getSubFiles(File baseDir, boolean subDirectory, Set<String> exts) {
		if (subDirectory) {
			FileFinder files = new FileFinder(baseDir, exts);
			return files.getFiles();
		}

		return getSubFiles(baseDir, exts);
	}

	/**
	 * @param baseDir
	 *            기준 디렉토리
	 * @param exts
	 *            맴칭할 확장자<br>
	 *            예) hwp,pdf,html
	 * @return 디렉토리 내에 있는 파일 중에 확장자와 매칭되는 파일 목록
	 */
	public static List<File> getSubFiles(File baseDir, Set<String> exts) {
		List<File> files = new ArrayList<File>();

		File[] fileList = baseDir.listFiles();
		Set<String> includeExt = new HashSet<String>();
		for (String e : exts) {
			includeExt.add(e);
		}

		for (File f : fileList) {
			String ext = FileUtil.getExtWithoutDot(f.getName());
			if (!f.isFile()) {
				continue;
			}
			if (includeExt.contains(ext.toLowerCase())) {
				files.add(f);
			}
		}
		return files;
	}

	/**
	 * 하위 폴더를 탐색하여 특정 확장자를 갖는 파일 목록을 검색 <br>
	 * TODO 공통 라이브러리로 이동
	 * 
	 * @version $Id$
	 */
	private static class FileFinder {
		private List<File> files = new ArrayList<File>();
		private File baseDir;
		private Set<String> includeExt;

		/**
		 * @param baseDir
		 *            검색 시작 디렉토리
		 * @param ext
		 *            검색 대상 확장자. 확장자는 포함 <br>
		 *            ex) hwp, mp3, ...
		 */
		FileFinder(File baseDir, Set<String> ext) {
			this.baseDir = baseDir;
			this.includeExt = ext;
			setFile(this.baseDir);
		}

		/**
		 * @return 파일 목록
		 */
		public List<File> getFiles() {
			return Collections.unmodifiableList(files);
		}

		private void setFile(File dir) {
			File[] fileList = dir.listFiles();
			if (fileList == null) {
				return;
			}
			for (File f : fileList) {
				if (f.isDirectory() == true) {
					this.setFile(f);
				}
			}
			List<File> c = getSubFiles(dir, includeExt);
			files.addAll(c);
		}
	}
}
