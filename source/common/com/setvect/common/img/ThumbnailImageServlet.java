package com.setvect.common.img;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.setvect.common.log.LogPrinter;
import com.setvect.common.util.FileUtil;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.config.EnvirmentProperty;

/**
 * �� ��Ʈ �ٱ��� �ִ� �̹��� ������ ��� �����͸� �ѿ� �ִ� Ŭ���� <br>
 * ex) <img src="/servlet/Thumbnail?i=�̸�����&w=����&h=����"> �̷� ������ ��� �ϸ� �ȴ�.
 * 
 * @version $Id$
 */
public class ThumbnailImageServlet extends HttpServlet {

	/** */
	private static final long serialVersionUID = 3865935617999439844L;

	/** ������ ��� �̹����� ��� �ִ� �⺻ ��� */
	public final static String BASE_DIR_SESSION_NAME = "_thum_base_dir";

	private static String tempImagePath = EnvirmentProperty.getString("com.setvect.literatureboy.image.thumbnail_dir");

	/**
	 * ����� �̹���
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// ���� �̹���
		String orgImagePath = req.getParameter("i");
		int width = Integer.parseInt(StringUtilAd.null2str(req.getParameter("w"), "0"));
		int height = Integer.parseInt(StringUtilAd.null2str(req.getParameter("h"), "0"));

		// �Է°��� ���� �Էµ��� ������ �׳� ����
		if (orgImagePath == null || width == 0 || height == 0) {
			return;
		}
		File sourceImageFile;
		String baseDir = (String) req.getSession().getAttribute(BASE_DIR_SESSION_NAME);
		if (baseDir == null) {
			// �⺻ ��ΰ� ���� ��� �� ��Ʈ ��θ� �⺻���� �Ѵ�.
			sourceImageFile = new File(getServletConfig().getServletContext().getRealPath(orgImagePath));
		}
		else {
			sourceImageFile = new File(baseDir, orgImagePath);
		}

		// ������ ���� ���� ������ �׳� ����
		if (!sourceImageFile.exists()) {
			LogPrinter.out.warn(sourceImageFile + " �̹����� �����ϴ�.");
			return;
		}

		// ������ �̹��� �����̸� �����
		// e.g) imagename_w33_h44.jpg
		String tempImg = FileUtil.getFilenameWithoutExt(orgImagePath) + "_w" + width + "_h" + height
				+ FileUtil.getExt(orgImagePath);

		File saveDir = new File(tempImagePath, orgImagePath).getParentFile();
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}

		// ������ ������ ���
		File toFile = new File(saveDir, tempImg);
		boolean fileExist = toFile.exists();
		boolean fileOld = toFile.lastModified() < sourceImageFile.lastModified();

		// ������ �����Ϸ� ��ȯ�� ������ �ִ³�?
		// �����Ϸ� ��ȯ�� ������ ���ų�, ������ �����Ǿ��� ��� ������ �ٽ� �����
		if (!fileExist || fileOld) {
			ThumbnailImageConvert.toJPEGAny(sourceImageFile.getPath(), toFile.getPath(), width, height);
		}

		// ����Ϸ� ��ȯ�� ���� �о� �鸮��
		FileInputStream ifp = null;

		try {
			ifp = new FileInputStream(toFile);
			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();
			int ch;

			byte b[] = new byte[1024];
			while ((ch = ifp.read(b)) != -1) {
				out.write(b, 0, ch);
				out.flush();
			}
		} catch (Exception e) {
			LogPrinter.out.error("�̹��� �о� �帮�� ����: " + toFile, e);
			return;
		} finally {
			if (ifp != null) {
				ifp.close();
			}
		}
	}
}