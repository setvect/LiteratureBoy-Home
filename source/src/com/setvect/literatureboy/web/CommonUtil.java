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
 * 프로젝트 의존적인 공통 메소드 모음
 * 
 * @version $Id$
 */
public class CommonUtil {
	private static final String GET_SEARCH = "getSearch";

	/**
	 * @param request
	 * @param listName
	 *            displaytag ID 이름
	 * @return displaytag 파라미터가 있으면 해당 파라미터의 페이지 값, 없으면 "currentPage" 파라미터 값, 이것도 없으면 1
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
	 * search로 시작되는 메소드를 분석하여 해당 이름과 값을 맵으로 만듬
	 * 
	 * @param pageCondition
	 * @return 검색 맵
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
	 * 파일 업로드 및 다운로드 가능 여부를 체크함.
	 * 
	 * @param fileName
	 *            체크할 파일 이름
	 * @throws ApplicationException
	 *             불가능 할 경우 예외 발생
	 */
	public static void checkAllowUploadFile(String fileName) throws ApplicationException {
		String ext = FileUtil.getExt(fileName).toLowerCase().trim();

		int a = StringUtilAd.indexOfAny(ext, ConstraintWeb.ALLOW_UPLOAD_FILE);
		if (a == -1) {
			throw new ApplicationException("[" + fileName + "] 은 허가된 파일이 아닙니다.");
		}
	}

	/**
	 * 쿠기 정보에 있는 로그인 코드를 객채화 시켜 가져옴
	 * 
	 * @param request
	 * @return 로그인 안되어 있으면 null
	 * @throws Exception
	 */
	public static User getLoginSession(HttpServletRequest request) throws Exception {
		User user = null;
		CookieProcess cookie = new CookieProcess(request);
		String encode = cookie.get(ConstraintWeb.USER_COOKIE_KEY);
		if (!StringUtilAd.isEmpty(encode)) {
			encode = encode.replaceAll("  ", "\n");
			user = (User) SerializerUtil.restoreBase64Decode(encode);
		}
		return user;
	}
}
