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
	 * Ȯ���ڸ� ������ ���ϸ� ���� <br>
	 * ���� ���ϸ� ��α��� ���ԵǸ� �ش� ��δ� ���� ������. <br>
	 * ��) abcdef.txt => abcdef<br>
	 * ads.sdfe.xafe.gif => ads.sdfe.xafe
	 * 
	 * @param filename
	 *            ���ϸ�
	 * @return ���� Ȯ���ڸ� ������ ���ϸ� ����
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
		/* �������� �پ� �ִ� ���� ��ġ�� . */
		int end = filename.lastIndexOf('.', len);

		/* Ȯ���ڿ� ���� ���������� ����. */
		return (end < 0) ? filename : filename.substring(0, end);
	}

	/**
	 * Ȯ���� ���ϱ�
	 * 
	 * @param filename
	 *            filename
	 * @return Ȯ���� (.) ���� ex) .jpg, .hwp
	 */
	public static String getExt(String filename) {
		int len = filename.length();

		/* �������� �پ� �ִ� ���� ��ġ�� . */
		int start = filename.lastIndexOf('.', len);

		/* Ȯ���ڿ� ���� ���������� ����. */
		return (start < 0) ? "" : filename.substring(start);
	}

	/**
	 * Ȯ���� ���ϱ�. ��(.) �������� ����
	 * 
	 * @param filename
	 *            filename
	 * @return ���� ex) jpg, hwp
	 */
	public static String getExtWithoutDot(String filename) {
		int len = filename.length();

		/* �������� �پ� �ִ� ���� ��ġ�� . */
		int start = filename.lastIndexOf('.', len);

		/* Ȯ���ڿ� ���� ���������� ����. */
		return (start < 0) ? "" : filename.substring(start + 1);
	}

	/**
	 * ���� ��¥(��/��/��)���� ���丮�� ����
	 * 
	 * @param strBasePath
	 *            ���� ���丮
	 * @return ������� ���丮
	 */
	public static File makeDayDir(String strBasePath) {
		String year = NumberFormat.getNumberString("0000", DateUtil.getYear());
		String month = NumberFormat.getNumberString("00", DateUtil.getMonth());
		String day = NumberFormat.getNumberString("00", DateUtil.getDay());
		String dir;

		dir = strBasePath;
		// ���丮�� �ִ���
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
				throw new RuntimeException("���丮 ���� ���� : " + f.getPath());
			}
		}
		return f;
	}

	/**
	 * ���丮 ���� ����
	 * 
	 * @param path
	 *            File��(Full Path)
	 * @return bool ����(true), ����(false)
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
	 * ���丮 ����� �޼ҵ�
	 * 
	 * @param strDir
	 *            ������� ���丮 �̸� (Ǯ �н�)
	 * @return �����ߴ��� ���ߴ��� true�� ���� false ����
	 */
	public static boolean makeDir(String strDir) {
		File makeDir = new File(strDir);
		return makeDir.mkdir();
	}

	/**
	 * File ����
	 * 
	 * @param path
	 *            File��(Full Path)
	 * @return bool ����(true), ����(false)
	 */
	public static boolean delFile(String path) {
		boolean bool = false;
		File f = new File(path);

		// ���� �϶��� ������ �Ѵ�. ���� ���丮 ���� ���� �� �ִ�.
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
	 * File ���� ����
	 * 
	 * @param path
	 *            File��(Full Path)
	 * @return bool ����(true), ����(false)
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
	 * ���丮�� Ž���Ͽ� ���ϴ� ������ ã��
	 * 
	 * @param baseDir
	 *            ���� Ž�� ���� ����
	 * 
	 * @param subDirectory
	 *            ���� ���丮 Ž�� ����
	 * @param exts
	 *            Ȯ���� <br>
	 * @return ��� ���� ���
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
	 *            ���� ���丮
	 * @param exts
	 *            ��Ī�� Ȯ����<br>
	 *            ��) hwp,pdf,html
	 * @return ���丮 ���� �ִ� ���� �߿� Ȯ���ڿ� ��Ī�Ǵ� ���� ���
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
	 * ���� ������ Ž���Ͽ� Ư�� Ȯ���ڸ� ���� ���� ����� �˻� <br>
	 * TODO ���� ���̺귯���� �̵�
	 * 
	 * @version $Id$
	 */
	private static class FileFinder {
		private List<File> files = new ArrayList<File>();
		private File baseDir;
		private Set<String> includeExt;

		/**
		 * @param baseDir
		 *            �˻� ���� ���丮
		 * @param ext
		 *            �˻� ��� Ȯ����. Ȯ���ڴ� ���� <br>
		 *            ex) hwp, mp3, ...
		 */
		FileFinder(File baseDir, Set<String> ext) {
			this.baseDir = baseDir;
			this.includeExt = ext;
			setFile(this.baseDir);
		}

		/**
		 * @return ���� ���
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
