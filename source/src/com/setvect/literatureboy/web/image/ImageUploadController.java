package com.setvect.literatureboy.web.image;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.common.util.Binder;
import com.setvect.common.util.FileUtil;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.config.EnvirmentProperty;

/**
 * �̹��� ���ε�
 * 
 * @version $Id$
 */
@Controller
public class ImageUploadController {

	/**
	 * ���� ��ɾ� ����
	 */
	public static enum Mode {
		UPLOAD_FORM, UPLOAD_ACTION
	}

	/**
	 * �信 ������ ��ü�� ����Ű�� Ű
	 */
	public static enum AttributeKey {
		MODE, IMAGE_URL
	}

	/** �� ��Ʈ�� �������� ���� ��� */
	private static final String SAVE_PATH = EnvirmentProperty
			.getString("com.setvect.literatureboy.image.file_upload_dir");

	/** �̹��� ���ε� ���� */
	public static final int LIMIT_SIZE = EnvirmentProperty.getInt("com.setvect.literatureboy.image.limit_size") * 1024;

	@RequestMapping("/image/upload.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String mode = request.getParameter("mode");

		Mode m;
		if (StringUtilAd.isEmpty(mode)) {
			m = Mode.UPLOAD_FORM;
		}
		else {
			m = Mode.valueOf(mode);
		}

		if (m == Mode.UPLOAD_FORM) {
			mav.addObject(AttributeKey.MODE.name(), Mode.UPLOAD_ACTION);
			mav.setViewName("/app/image/image_upload_form");
		}
		else {
			String baseDir = request.getSession().getServletContext().getRealPath(SAVE_PATH);
			File targetDir = FileUtil.makeDayDir(baseDir);
			ImageItem item = new ImageItem();
			Binder.bind(request, item);

			MultipartFile imageFile = item.getImageFile()[0];
			String saveName = "upload" + FileUtil.getExt(imageFile.getOriginalFilename());
			File destination = File.createTempFile("image", saveName, targetDir);
			FileCopyUtils.copy(imageFile.getInputStream(), new FileOutputStream(destination));

			// ����Ʈ ���丮�� �������� �̹��� ���� URL�� ����
			String webbaseDirString = request.getSession().getServletContext().getRealPath("/");
			String savePathString = destination.getPath();
			int pos = savePathString.indexOf(webbaseDirString);
			String imageUrl = savePathString.substring(pos + webbaseDirString.length() - 1);
			imageUrl = imageUrl.replace('\\', '/');
			mav.addObject(AttributeKey.IMAGE_URL.name(), imageUrl);

			mav.setViewName("/app/image/image_upload_complete");
		}
		return mav;
	}
}
