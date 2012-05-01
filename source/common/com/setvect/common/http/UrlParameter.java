package com.setvect.common.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Map에 있는 값을 Get형식의 파라미터 스트링으로 변환<br>
 * ※ 파라미터 키 값이 2개 이상인 경우는 지원 하지 않음.<br>
 * 
 * @version $Id$
 * @deprecated com.setvect.common.jsp.URLParameter 사용하기 바람
 */
public class UrlParameter {
	private static final long serialVersionUID = 7246368964117438269L;

	private Map<String, Object> parameter = new HashMap<String, Object>();

	/**
	 * @return utf-8로 인코딩 된 파라미터
	 * @throws UnsupportedEncodingException
	 */
	public String getParameter() throws UnsupportedEncodingException {
		return getParameter("utf-8");
	}

	/**
	 * @param charset
	 *            파라미터 인코딩 chartset
	 * @return get방식 URL 파라미터
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
