package com.setvect.common.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Map�� �ִ� ���� Get������ �Ķ���� ��Ʈ������ ��ȯ<br>
 * �� �Ķ���� Ű ���� 2�� �̻��� ���� ���� ���� ����.<br>
 * 
 * @version $Id$
 * @deprecated com.setvect.common.jsp.URLParameter ����ϱ� �ٶ�
 */
public class UrlParameter {
	private static final long serialVersionUID = 7246368964117438269L;

	private Map<String, Object> parameter = new HashMap<String, Object>();

	/**
	 * @return utf-8�� ���ڵ� �� �Ķ����
	 * @throws UnsupportedEncodingException
	 */
	public String getParameter() throws UnsupportedEncodingException {
		return getParameter("utf-8");
	}

	/**
	 * @param charset
	 *            �Ķ���� ���ڵ� chartset
	 * @return get��� URL �Ķ����
	 * @throws UnsupportedEncodingException
	 */
	public String getParameter(String charset) throws UnsupportedEncodingException {
		StringBuffer param = new StringBuffer();
		Set<String> keys = parameter.keySet();
		for (String key : keys) {
			Object value = parameter.get(key);
			if (value == null) {
				continue;
			}
			if (param.length() != 0) {
				param.append("&");
			}
			param.append(key + "=" + URLEncoder.encode(value.toString(), charset));
		}
		return param.toString();
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public Object put(String key, Object value) {
		return parameter.put(key, value);
	}

	/**
	 * @param t
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	public void putAll(Map<? extends String, ? extends Object> t) {
		parameter.putAll(t);
	}
}
