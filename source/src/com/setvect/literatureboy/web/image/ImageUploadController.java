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
 * 이미지 업로드
 * 
 * @version $Id$
 */
@Controller
public class ImageUploadController {

	/**
	 * 서브 명령어 정의
	 */
	public static enum Mode {
		UPLOAD_FORM, UPLOAD_ACTION
	}

	/**
	 * 뷰에 전달할 객체를 가르키는 키
	 */
	public static enum AttributeKey {
		MODE, IMAGE_URL
	}

	/** 웹 루트를 기준으로 저장 경로 */
	private static final String SAVE_PATH = EnvirmentProperty
			.getString("com.setvect.literatureboy.image.file_upload_dir");

	/** 이미지 업로드 제한 */
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

			// 웹루트 디렉토리를 기준으로 이미지 접근 URL를 추출
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
