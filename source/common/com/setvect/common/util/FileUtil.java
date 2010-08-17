package com.setvect.common.util;

import org.apache.commons.io.FileUtils;

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

}
