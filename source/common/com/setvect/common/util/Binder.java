package com.setvect.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.setvect.common.http.MultiFileSupportServletRequestDataBinder;

/**
 * Request 값을 VO 클래스의 바인드
 * 
 * @version $Id$
 */
public class Binder {
	/**
	 * 바인드
	 * 
	 * @param request
	 * @param object
	 *            바인드될 Object
	 * @throws ServletRequestBindingException
	 */
	public static void bind(HttpServletRequest request, Object object) throws ServletRequestBindingException {
		ServletRequestDataBinder binder = new MultiFileSupportServletRequestDataBinder(object);
		binder.bind(request);
		binder.closeNoCatch();
	}
}
