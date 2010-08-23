package com.setvect.literatureboy.web;

import java.util.List;

import com.setvect.literatureboy.config.EnvirmentProperty;

/**
 * Web UI���� ���Ǵ� ��� ����
 * 
 * @version $Id$
 */
public class ConstraintWeb {
	/** �⺻ Ʋ�� ���� �� �̸� */
	public final static String INDEX_VIEW = "index";

	/** jsp�� ������ ������ ������ ������ ��� attribute key */
	public final static String INCLUDE_PAGE = "include_page";

	/** ���� �ּ� ���� Attribute Key */
	public static final String SERVLET_URL = "controller_url";

	/** ���ε� ���� Ȯ���� */
	public final static String[] ALLOW_UPLOAD_FILE;
	static {
		@SuppressWarnings("unchecked")
		List<String> s = EnvirmentProperty.getList("com.setvect.literatureboy.allow_upload_file");
		ALLOW_UPLOAD_FILE = new String[s.size()];
		for (int i = 0; i < s.size(); i++) {
			ALLOW_UPLOAD_FILE[i] = s.get(i).toLowerCase().trim();
		}
	}

	/** �н����� ��ȣȭ �˰��� */
	public final static String PASSWD_ALGORITHM = "MD5";

	/** �α��� ��Ű Ű�� */
	public static final String USER_SESSION = "_user_session_name";
}
