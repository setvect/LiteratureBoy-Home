package com.setvect.literatureboy.web;

import java.util.List;

import com.setvect.literatureboy.config.EnvirmentProperty;

/**
 * Web UI���� ���Ǵ� ��� ����
 * 
 * @version $Id$
 */
public class ConstraintWeb {
	/** ���мҳ�: �⺻ Ʋ�� ���� �� �̸� */
	public final static String LITERATUREBOY_LAYOUT = "literatureboy/layout";

	/** ���мҳ�(�����): �⺻ Ʋ�� ���� �� �̸� */
	public final static String MOBILE_LAYOUT = "m/layout";

	/** ����: �⺻ Ʋ�� ���� �� �̸� */
	public final static String DEVLOP_LAYOUT = "devlop/layout";

	/** ���ε� ���� URL */
	public final static String UPLOAD_URL_BASE = EnvirmentProperty
			.getString("com.setvect.literatureboy.file_upload_url");

	public enum AttributeKey {
		// jsp�� ������ ������ ������ ����
		INCLUDE_PAGE,
		// ���� �ּ� ����
		SERVLET_URL,
		// �Խ��� ��ü ����
		BOARD_ITEMS,
		// ���� ��Ʈ�ѷ����� �����ϴ� �� ��
		MODEL_VIEW,

	}

	/** ���ε� ���� Ȯ���� */
	public final static String[] ALLOW_UPLOAD_FILE;
	/** �̹��� Ȯ���� */
	public final static List<String> IMAGE_EXT;

	static {
		@SuppressWarnings("unchecked")
		List<String> s = EnvirmentProperty.getList("com.setvect.literatureboy.allow_upload_file");
		ALLOW_UPLOAD_FILE = new String[s.size()];
		for (int i = 0; i < s.size(); i++) {
			ALLOW_UPLOAD_FILE[i] = s.get(i).toLowerCase().trim();
		}

		IMAGE_EXT = EnvirmentProperty.getList("com.setvect.literatureboy.image_ext");
	}

	/** �н����� ��ȣȭ �˰��� */
	public final static String PASSWD_ALGORITHM = "MD5";

	/** �α��� ��Ű Ű�� */
	public static final String USER_COOKIE_KEY = "_user_cookie_key";

	/** �α��� attribute Ű�� */
	public static final String USER_SESSION_KEY = "_user_session_key";

	/** ���� URL �Ķ���� �̸� */
	public static final String RETURN_URL = "returnUrl";

	/** ����ȭ�� �Խ��� �ڵ� */
	public static final String MAIN_BOARD = "BDAAAA00";

	/** ����Ͽ��� PC ȭ�� ���� ��� */
	public static final String PC_VIEW_COOKIE_KEY = "pc_view_key";

	/**
	 * ����� ������ Agent <br>
	 * request.getHeader("user-agent") Ȯ���� ��
	 */
	public static final String[] MOBILE_BROWSER_AGENT = { "iPhone", "iPod", "Android", "Windows CE", "BlackBerry",
			"Symbian", "Windows Phone", "webOS", "Opera Mini", "Opera Mobi", "POLARIS", "IEMobile", "lgtelecom",
			"nokia", "SonyEricsson", "LG", "SAMSUNG", "Samsung" };
}
