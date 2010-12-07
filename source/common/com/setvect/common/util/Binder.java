package com.setvect.common.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

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
		bind(request, object, null);
	}

	/**
	 * @param request
	 * @param object
	 * @param customEditor
	 *            bind�� �Ӽ� Ŭ������ ���� request ���
	 * @throws ServletRequestBindingException
	 */
	public static void bind(HttpServletRequest request, Object object, Map<Class<?>, PropertyEditorSupport> customEditor)
			throws ServletRequestBindingException {
		ServletRequestDataBinder binder = new MultiFileSupportServletRequestDataBinder(object);

		if (customEditor != null) {
			Set<Class<?>> keys = customEditor.keySet();
			for (Class<?> k : keys) {
				binder.registerCustomEditor(k, customEditor.get(k));
			}
		}

		binder.bind(request);
		binder.closeNoCatch();
	}

	/**
	 * @param dateFormat
	 * @return ��¥ ���� bind
	 */
	public static PropertyEditorSupport getDateFromatProperty(String dateFormat) {

		return new DatePropertyEditorSupport(dateFormat) {
			public void setAsText(String value) {
				try {
					setValue(new SimpleDateFormat(pattern).parse(value));
				} catch (ParseException e) {
					setValue(null);
				}
			}

			public String getAsText() {
				return new SimpleDateFormat(pattern).format((Date) getValue());
			}
		};

	}

	static class DatePropertyEditorSupport extends PropertyEditorSupport {
		protected final String pattern;

		public DatePropertyEditorSupport(String pattern) {
			this.pattern = pattern;
		}
	}

}
