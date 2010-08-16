package com.setvect.literatureboy.web;

import javax.servlet.http.HttpServletRequest;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import com.setvect.common.util.StringUtilAd;

/**
 * 프로젝트 의존적인 공통 메소드 모음
 * 
 * @version $Id$
 */
public class CommonUtil {
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
}
