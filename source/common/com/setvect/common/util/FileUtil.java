package com.setvect.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * file download method
	 * 
	 * @param file
	 *            �ٿ�ε��� ���� ���
	 * 
	 * @param strDownloadFileName
	 *            Download file name from client computer
	 * @param request
	 *            Request object
	 * @param response
	 *            Response object
	 */
	public static void fileDown(File file, String strDownloadFileName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String encodeCharSet = request.getCharacterEncoding();
		// String encodeCharSet = "euc-kr";
		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;
		int read = 0;
		byte[] b = new byte[1024];
		// ���� Ŭ����
		response.reset();
		try {
			if (file.exists()) {
				fin = new BufferedInputStream(new FileInputStream(file));
				outs = new BufferedOutputStream(response.getOutputStream());
				response.setContentType("application/x-force-download");

				// �ѱ� ���� ������ java.net.URLEncoder.encode()�� ���� �ذ� ��
				// response.setHeader("Content-Disposition", option + ";
				// filename=" + java.net.URLEncoder.encode(orgFileName) + ";");

				response.setHeader("Content-Type", "application/octet-stream; charset=" + encodeCharSet + "\"");

				response.setHeader("Content-Disposition",
						"attachment;filename=" + URLEncoder.encode(strDownloadFileName, encodeCharSet) + ";");

				response.setHeader("Content-Length", "" + file.length());
				response.setHeader("Pragma", "no-cache;");
				response.setHeader("Expires", "-1;");

				while ((read = fin.read(b)) != -1) {
					outs.write(b, 0, read);
				}
			} else {
				throw new IOException("������ �����ϴ�. : " + file);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (outs != null) {
				try {
					outs.close();
				} catch (IOException e) {
				}
			}
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
				}
			}
		}
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
}
