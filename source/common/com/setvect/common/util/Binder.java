package com.setvect.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.setvect.common.http.MultiFileSupportServletRequestDataBinder;

/**
 * Request ���� VO Ŭ������ ���ε�
 * 
 * @version $Id$
 */
public class Binder {
	/**
	 * ���ε�
	 * 
	 * @param request
	 * @param object
	 *            ���ε�� Object
	 * @throws ServletRequestBindingException
	 */
	public static void bind(HttpServletRequest request, Object object) throws ServletRequestBindingException {
		ServletRequestDataBinder binder = new MultiFileSupportServletRequestDataBinder(object);
		binder.bind(request);
		binder.closeNoCatch();
	}
}
