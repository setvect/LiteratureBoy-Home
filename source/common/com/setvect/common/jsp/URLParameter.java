package com.setvect.common.jsp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

/**
 * 중복되는 URL 파라미터를 간소화 시켜서 재 사용을 편리하게 하기 위해서 만듬
 * 
 */
public class URLParameter {

	private final static String AMP = "&amp;";

	/** 파라미터 저장 */
	private Hashtable<String, ArrayList<String>> param = new Hashtable<String, ArrayList<String>>();

	/** url 경로 */
	private String url;

	/** 인코딩 타입 */
	private String encode;

	private URLParameter() {

	}

	/**
	 * @param url
	 *            URL 경로
	 * @param encode
	 *            URL 인코딩
	 */
	public URLParameter(String url, String encode) {
		this.encode = encode;
		this.url = url;
	}

	/**
	 * @param key
	 *            키
	 * @param value
	 *            파라미터값
	 * @return URLParamter 객체 정보. put() 연산을 편리 하게 하기 위해
	 */
	public URLParameter put(String key, String value) {
		ArrayList<String> s = param.get(key);
		if (s == null) {
			s = new ArrayList<String>();
			s.add(value);
		}
		else {
			s.clear();
			s.add(value);
		}
		param.put(key, s);
		return this;
	}

	/**
	 * @param key
	 *            키
	 * @param value
	 *            파라미터값
	 * @return URLParamter 객체 정보. put() 연산을 편리 하게 하기 위해
	 */
	public URLParameter put(String key, int value) {
		return put(key, String.valueOf(value));
	}

	/**
	 * 키 중복허용
	 * 
	 * @param key
	 *            키
	 * @param value
	 *            파라미터값
	 * @return URLParamter 객체 정보. put() 연산을 편리 하게 하기 위해
	 */
	public URLParameter putOverlap(String key, String value) {
		ArrayList<String> s = param.get(key);
		if (s == null) {
			s = new ArrayList<String>();
			s.add(value);
		}
		else {
			s.add(value);
		}
		param.put(key, s);
		return this;
	}

	/**
	 * request 파라미터 정보를 그대로 셋팅<br>
	 * 파라미터 배열(같은 이름이 두개 이상인 파라미터)는 지원 하지 않음
	 * 
	 * @param request
	 */
	public void put(HttpServletRequest request) {
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String value = request.getParameter(name);
			putOverlap(name, value);
		}
	}

	/**
	 * @param key
	 *            키
	 * @param value
	 *            파라미터값
	 * @return URLParamter 객체 정보. put() 연산을 편리 하게 하기 위해
	 */
	public URLParameter putOverlap(String key, int value) {
		return putOverlap(key, String.valueOf(value));
	}

	/**
	 * 특정 키 값을 제거
	 * 
	 * @param key
	 *            키
	 * @return URLParamter 객체 정보. chain 형식
	 */
	public URLParameter clearParam(String key) {
		param.remove(key);
		return this;
	}

	/**
	 * @param url
	 *            URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 만약 키에 중복 파라미터이면 모두 삭제
	 * 
	 * @param key
	 *            키
	 */
	public void remove(String key) {
		param.remove(key);
	}

	/**
	 * @return 파리머터를 포함한 전체 주소
	 */
	public String getParam() {
		return getParam(AMP);
	}

	/**
	 * @param seperator
	 *            보통은 '&'
	 * @return
	 */
	public String getParam(String seperator) {
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
					s.append(seperator);
				}
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		// 맨 끝의 "&amp;"를 삭제한 후 리턴 한다.
		String rtnParam = s.toString();
		if (rtnParam.contains(seperator)) {
			rtnParam = rtnParam.substring(0, rtnParam.length() - seperator.length());
		}
		return rtnParam;
	}

	/**
	 * 정보값 복제
	 */
	@SuppressWarnings("unchecked")
	public URLParameter clone() {
		URLParameter pa = new URLParameter();
		pa.encode = new String(this.encode);
		pa.url = new String(this.url);
		pa.param = (Hashtable<String, ArrayList<String>>) this.param.clone();
		return pa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "URLParameter [url=" + url + ", encode=" + encode + ", param=" + param + "]";
	}

}
