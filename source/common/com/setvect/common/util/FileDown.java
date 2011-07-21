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

public class FileDown extends FileUtils {

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
			}
			else {
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

}
