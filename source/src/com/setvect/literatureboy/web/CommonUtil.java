package com.setvect.literatureboy.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import com.setvect.common.http.CookieProcess;
import com.setvect.common.util.FileUtil;
import com.setvect.common.util.SearchListVo;
import com.setvect.common.util.SerializerUtil;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.boot.ApplicationException;
import com.setvect.literatureboy.vo.user.User;

/**
 * ������Ʈ �������� ���� �޼ҵ� ����
 * 
 * @version $Id$
 */
public class CommonUtil {
	private static final String GET_SEARCH = "getSearch";

	/**
	 * @param request
	 * @param listName
	 *            displaytag ID �̸�
	 * @return displaytag �Ķ���Ͱ� ������ �ش� �Ķ������ ������ ��, ������ "currentPage" �Ķ���� ��, �̰͵� ������ 1
	 */
	public static int getCurrentPage(HttpServletRequest request, String listName) {
		String pageParam = new ParamEncoder(listName).encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String pageParamValue = request.getParameter(pageParam);

		if (StringUtilAd.isEmpty(pageParamValue)) {
			pageParamValue = request.getParameter("currentPage");
		}
		int currentPage = Integer.parseInt(StringUtilAd.null2str(pageParamValue, "1"));
		return currentPage;
	}

	/**
	 * search�� ���۵Ǵ� �޼ҵ带 �м��Ͽ� �ش� �̸��� ���� ������ ����
	 * 
	 * @param pageCondition
	 * @return �˻� ��
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static Map<String, Object> getSearchMap(SearchListVo pageCondition) throws Exception {
		Method[] methods = pageCondition.getClass().getMethods();
		Map<String, Object> param = new HashMap<String, Object>();
		for (Method m : methods) {
			String name = m.getName();
			if (!name.startsWith(GET_SEARCH)) {
				continue;
			}
			Object v = m.invoke(pageCondition, new Object[0]);
			String key = "search" + name.substring(GET_SEARCH.length());
			param.put(key, v);
		}
		return param;
	}

	/**
	 * ���� ���ε� �� �ٿ�ε� ���� ���θ� üũ��.
	 * 
	 * @param fileName
	 *            üũ�� ���� �̸�
	 * @throws ApplicationException
	 *             �Ұ��� �� ��� ���� �߻�
	 */
	public static void checkAllowUploadFile(String fileName) throws ApplicationException {
		String ext = FileUtil.getExt(fileName).toLowerCase().trim();

		int a = StringUtilAd.indexOfAny(ext, ConstraintWeb.ALLOW_UPLOAD_FILE);
		if (a == -1) {
			throw new ApplicationException("[" + fileName + "] �� �㰡�� ������ �ƴմϴ�.");
		}
	}

	/**
	 * ��� ������ �ִ� �α��� �ڵ带 ��äȭ ���� ������
	 * 
	 * @param request
	 * @return �α��� �ȵǾ� ������ null
	 * @throws Exception
	 */
	public static User getLoginSession(HttpServletRequest request) throws Exception {
		User user = null;
		CookieProcess cookie = new CookieProcess(request);
		String encode = cookie.get(ConstraintWeb.USER_COOKIE_KEY);
		if (encode != null) {
			encode = encode.replaceAll("  ", "\n");
			user = (User) SerializerUtil.restoreBase64Decode(encode);
		}
		return user;
	}
}
