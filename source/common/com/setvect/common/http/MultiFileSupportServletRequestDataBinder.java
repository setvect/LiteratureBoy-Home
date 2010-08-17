package com.setvect.common.http;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * Spring 2.5에서는 동일한 이름으로 첨부파일 등록 폼이 2개 이상이 경우 에러남
 * spring mvc 2.5.5 multipart multi file upload 지원<br>
 * 참고: http://nezah.egloos.com/3994564<br>
 * 
 * @version $Id$
 */
public class MultiFileSupportServletRequestDataBinder extends ServletRequestDataBinder {

	public MultiFileSupportServletRequestDataBinder(Object target) {
		super(target);
	}

	public MultiFileSupportServletRequestDataBinder(Object target, String objectName) {
		super(target, objectName);
	}

	protected void bindMultipartFiles(Map multipartFiles, MutablePropertyValues mpvs) {
		for (Iterator it = multipartFiles.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			if (isBindEmptyMultipartFiles()) {
				mpvs.addPropertyValue(key, value);
			}
		}
	}
}
