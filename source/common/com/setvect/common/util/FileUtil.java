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
	 * file download method
	 * 
	 * @param file 다운로드할 파일 경로 
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
		// 버퍼 클리어
		response.reset();
		try {
			if (file.exists()) {
				fin = new BufferedInputStream(new FileInputStream(file));
				outs = new BufferedOutputStream(response.getOutputStream());
				response.setContentType("application/x-force-download");

				// 한글 깨짐 현상은 java.net.URLEncoder.encode()를 쓰면 해결 됨
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
				throw new IOException("파일이 없습니다. : " + file);
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
