package com.setvect.literatureboy.web.image;

import org.springframework.web.multipart.MultipartFile;

/**
 * �̹��� ���� <br>
 * ���� �̹����� ���� ��Ÿ �������� �߰�
 * 
 * @version $Id$
 */
public class ImageItem {
	/** �̹����� */
	private MultipartFile[] imageFile;

	/**
	 * @return the attachFile
	 */
	public MultipartFile[] getImageFile() {
		return imageFile;
	}

	/**
	 * @param attachFile
	 *            the attachFile to set
	 */
	public void setImageFile(MultipartFile[] attachFile) {
		this.imageFile = attachFile;
	}

}
