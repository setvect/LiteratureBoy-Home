package com.setvect.literatureboy.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.setvect.common.util.FileDown;
import com.setvect.common.util.StringUtilAd;

/**
 * ���� �ٿ�ε� �Ķ����
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
		// ������ ����� �����̸� Ǯ ���
		String saveName = StringUtilAd.decodeString(request.getParameter("s"));

		CommonUtil.checkAllowUploadFile(saveName);

		String downName;
		// ������ ���ڵ� ������ ������ �������� �̸��� �ٿ�ε�
		if (StringUtilAd.isEmpty(request.getParameter("d"))) {
			// �ٿ�ε��� �����̸�
			int n = saveName.lastIndexOf("/");
			if (n == -1) {
				n = saveName.lastIndexOf("\\");
			}
			downName = saveName.substring(n + 1);
		}
		else {
			// �ٿ�ε��� �����̸�
			downName = StringUtilAd.decodeString(request.getParameter("d"));
		}
		String webBase = request.getSession().getServletContext().getRealPath("");
		File attachFile = new File(webBase, saveName);

		try {
			FileDown.fileDown(attachFile, downName, request, response);
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

}