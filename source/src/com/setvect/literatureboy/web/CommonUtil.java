package com.setvect.literatureboy.web;

import javax.servlet.http.HttpServletRequest;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import com.setvect.common.util.StringUtilAd;

/**
 * ������Ʈ �������� ���� �޼ҵ� ����
 * 
 * @version $Id$
 */
public class CommonUtil {
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
}
