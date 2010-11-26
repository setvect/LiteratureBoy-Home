package com.setvect.common.jsp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * �ߺ��Ǵ� URL �Ķ���͸� ����ȭ ���Ѽ� �� ����� ���ϰ� �ϱ� ���ؼ� ����
 * 
 */
public class URLParameter {

	private final static String AMP = "&amp;";

	/** �Ķ���� ���� */
	private Hashtable<String, ArrayList<String>> param = new Hashtable<String, ArrayList<String>>();

	/** url ��� */
	private String url;

	/** ���ڵ� Ÿ�� */
	private String encode;

	private URLParameter() {

	}

	/**
	 * @param url
	 *            URL ���
	 * @param encode
	 *            URL ���ڵ�
	 */
	public URLParameter(String url, String encode) {
		this.encode = encode;
		this.url = url;
	}

	/**
	 * @param key
	 *            Ű
	 * @param value
	 *            �Ķ���Ͱ�
	 * @return URLParamter ��ü ����. put() ������ �� �ϰ� �ϱ� ����
	 */
	public URLParameter put(String key, String value) {
		ArrayList<String> s = param.get(key);
		if (s == null) {
			s = new ArrayList<String>();
			s.add(value);
		} else {
			s.clear();
			s.add(value);
		}
		param.put(key, s);
		return this;
	}

	/**
	 * @param key
	 *            Ű
	 * @param value
	 *            �Ķ���Ͱ�
	 * @return URLParamter ��ü ����. put() ������ �� �ϰ� �ϱ� ����
	 */
	public URLParameter put(String key, int value) {
		return put(key, String.valueOf(value));
	}

	/**
	 * Ű �ߺ����
	 * 
	 * @param key
	 *            Ű
	 * @param value
	 *            �Ķ���Ͱ�
	 * @return URLParamter ��ü ����. put() ������ �� �ϰ� �ϱ� ����
	 */
	public URLParameter putOverlap(String key, String value) {
		ArrayList<String> s = param.get(key);
		if (s == null) {
			s = new ArrayList<String>();
			s.add(value);
		} else {
			s.add(value);
		}
		param.put(key, s);
		return this;
	}

	/**
	 * @param key
	 *            Ű
	 * @param value
	 *            �Ķ���Ͱ�
	 * @return URLParamter ��ü ����. put() ������ �� �ϰ� �ϱ� ����
	 */
	public URLParameter putOverlap(String key, int value) {
		return putOverlap(key, String.valueOf(value));
	}

	/**
	 * @param url
	 *            URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * ���� Ű�� �ߺ� �Ķ�����̸� ��� ����
	 * 
	 * @param key
	 *            Ű
	 */
	public void remove(String key) {
		param.remove(key);
	}

	/**
	 * @return �ĸ����͸� ������ ��ü �ּ�
	 */
	public String getParam() {
		StringBuffer s = new StringBuffer();

		s.append(url);

		Enumeration<String> keys = param.keys();
		boolean first = true;
		while (keys.hasMoreElements()) {

			String key = keys.nextElement();
			if (first) {
				s.append("?");
				first = false;
			}
			try {
				ArrayList<String> vs = param.get(key);
				for (int i = 0; i < vs.size(); i++) {
					String v = vs.get(i);
					s.append(key);
					s.append("=");
					s.append(URLEncoder.encode(v, encode));
					s.append(AMP);
				}
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		// �� ���� "&amp;"�� ������ �� ���� �Ѵ�.
		String rtnParam = s.toString();
		if (rtnParam.contains(AMP)) {
			rtnParam = rtnParam.substring(0, rtnParam.length() - AMP.length());
		}
		return rtnParam;
	}

	/**
	 * ������ ����
	 */
	@SuppressWarnings("unchecked")
	public URLParameter clone() {
		URLParameter pa = new URLParameter();
		pa.encode = new String(this.encode);
		pa.url = new String(this.url);
		pa.param = (Hashtable<String, ArrayList<String>>) this.param.clone();
		return pa;
	}
}
