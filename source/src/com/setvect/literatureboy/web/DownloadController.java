package com.setvect.literatureboy.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.setvect.common.util.FileUtil;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.boot.ApplicationException;

/**
 * 파일 다운로드 파라미터
 * 
 * @version $Id$
 */
@Controller
public class DownloadController extends HttpServlet {
	/** */
	private static final long serialVersionUID = -7894468662893018437L;

	/**
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/download.do")
	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 서버에 저장된 파일이름 풀 경로
		String saveName = StringUtilAd.decodeString(request.getParameter("s"));

		CommonUtil.checkAllowUploadFile(saveName);

		String downName;
		// 별도의 디코드 파일이 없으면 저장파일 이름을 다운로드
		if (StringUtilAd.isEmpty(request.getParameter("d"))) {
			// 다운로도될 파일이름
			int n = saveName.lastIndexOf("/");
			if (n == -1) {
				n = saveName.lastIndexOf("\\");
			}
			downName = saveName.substring(n + 1);
		}
		else {
			// 다운로도될 파일이름
			downName = StringUtilAd.decodeString(request.getParameter("d"));
		}
		String webBase = request.getSession().getServletContext().getRealPath("");
		File attachFile = new File(webBase, saveName);

		try {
			FileUtil.fileDown(attachFile, downName, request, response);
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}


}