package com.setvect.literatureboy.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.service.user.AuthCache;
import com.setvect.literatureboy.vo.user.Auth;
import com.setvect.literatureboy.vo.user.AuthMap;
import com.setvect.literatureboy.vo.user.User;

/**
 * @version $Id$
 */
public class AccessChecker {

	static final String LOGIN_URL = "/user/login.do";

	static final String LOGOUT = "/user/logout.do";
	/**
	 * �α��� üũ ���� �ּ�
	 */
	private static final String[] EXCUSE_URL = { LOGIN_URL, LOGOUT };

	/**
	 * �α��ε� ����ڰ� URL�� �׿� ���� �Ķ���Ϳ� ���� �������� ���� Ȯ��
	 * 
	 * @param request
	 * @param param
	 * @return ���� �����ϸ� true, �ƴϸ� false
	 * @throws Exception
	 */
	public static boolean isAccessToUrl(HttpServletRequest request, Map<String, String> param) throws Exception {
		return isAccessToUrl(request, param, null);
	}

	/**
	 * �α��ε� ����ڰ� URL�� �׿� ���� �Ķ���Ϳ� ���� �������� ���� Ȯ��
	 * 
	 * @param request
	 * @param param
	 * @param appendAccess
	 *            URL üũ ������ ��� ���ݿ� ���� Access üũ ����
	 * @return
	 * @throws Exception
	 */
	public static boolean isAccessToUrl(HttpServletRequest request, Map<String, String> param, AccessRule appendAccess)
			throws Exception {
		User user = CommonUtil.getLoginSession(request);
		String currentUrl = (String) request.getAttribute(ConstraintWeb.AttributeKey.SERVLET_URL.name());
		return isAccessToUrl(user, currentUrl, param, appendAccess);
	}

	/**
	 * �α��ε� ����ڰ� URL�� �׿� ���� �Ķ���Ϳ� ���� �������� ���� Ȯ��
	 * 
	 * @param user
	 * @param currentUrl
	 * @param param
	 *            URL �Ķ����
	 * @return ���� �����ϸ� true, �ƴϸ� false
	 * @throws Exception
	 */
	public static boolean isAccessToUrl(User user, String currentUrl, Map<String, String> param) throws Exception {
		return isAccessToUrl(user, currentUrl, param, null);
	}

	/**
	 * �α��ε� ����ڰ� URL�� �׿� ���� �Ķ���Ϳ� ���� �������� ���� Ȯ��
	 * 
	 * @param user
	 * @param currentUrl
	 * @param param
	 *            URL �Ķ����
	 * @param appendAccess
	 *            URL üũ ������ ��� ���ݿ� ���� Access üũ ����
	 * @return ���� �����ϸ� true, �ƴϸ� false
	 * @throws Exception
	 */
	public static boolean isAccessToUrl(User user, String currentUrl, Map<String, String> param, AccessRule appendAccess)
			throws Exception {
		List<Auth> matchAuthList = getMathAuth(currentUrl, param);
		// ���� ���� ������ ������ ���
		if (StringUtilAd.isInclude(currentUrl, EXCUSE_URL) || matchAuthList.size() == 0) {
			return true;
		}

		if (user == null) {
			return false;
		}

		// ��ü�����ڸ� �ο��� ���� Ȯ�� ����
		if (user.isAdminF()) {
			return true;
		}

		// �ش� ����ڰ� ������ �ִ� ���� ����
		Collection<AuthMap> authMap = AuthCache.getAuthMapCache(user.getUserId());
		for (Auth auth : matchAuthList) {
			for (AuthMap map : authMap) {
				if (auth.getAuthSeq() == map.getAuthSeq()) {
					if (appendAccess != null) {
						return appendAccess.isAccess();
					}
					else {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * URL�� �Ķ���� ������ ��ġ�Ǵ� ���� ������ ã��
	 * 
	 * @param currentUrl
	 *            �� URL �ּ�
	 * @param param
	 *            query string �Ķ����
	 * @return ���� URL�� �´� ���� ���� ����. �ش� ���� ������ �� List
	 */
	private static List<Auth> getMathAuth(String currentUrl, Map<String, String> param) {
		// �� ���� URL�� ���� �ʸ���Ʈ�� ���ԵǾ� �ִ��� ����
		List<Auth> matchAuthList = new ArrayList<Auth>();
		Collection<Auth> authList = AuthCache.getAuthCache();
		for (Auth auth : authList) {
			boolean urlMatch = false;
			boolean paramMath = false;
			urlMatch = isUrlMatch(currentUrl, auth);
			if (urlMatch) {
				paramMath = isParamMatch(param, auth);
			}
			if (urlMatch && paramMath) {
				matchAuthList.add(auth);
			}
		}
		return matchAuthList;
	}

	/**
	 * ���� ���� �Ķ���͸� �������� ���� ���� �������� ��Ī �Ǵ��� �˻�
	 * 
	 * @param param
	 *            �Ķ���� ����
	 * @param auth
	 * @return
	 */
	private static boolean isParamMatch(Map<String, String> param, Auth auth) {
		String paramString = auth.getParameter();
		if (paramString.equals("*")) {
			return true;
		}
		String urlSplit[] = paramString.split("[&]");
		for (String token : urlSplit) {
			String[] t = token.split("[=]");
			// "Ű=��" ���°� �ƴϸ� ����
			if (t.length != 2) {
				continue;
			}
			String name = t[0];
			String value = t[1];
			String paramValue = param.get(name);
			if (paramValue == null) {
				return false;
			}

			if (value.endsWith("*")) {
				String subValue = value.substring(0, value.length() - 1);
				if (!paramValue.startsWith(subValue)) {
					return false;
				}
			}
			else {
				if (!paramValue.equals(value)) {
					return false;
				}
			}

		}
		return true;
	}

	/**
	 * @param currentUrl
	 *            �� URL
	 * @param auth
	 *            ��ġ�� ���� ���� ����
	 * @return URL�� ��ġ �Ǹ� true
	 */
	private static boolean isUrlMatch(String currentUrl, Auth auth) {
		boolean urlMatch = false;
		String url = auth.getUrl();
		if (url.endsWith("*")) {
			url = url.substring(0, url.length() - 1);
			if (currentUrl.startsWith(url)) {
				urlMatch = true;
			}
		}
		else {
			if (currentUrl.equals(url)) {
				urlMatch = true;
			}
		}
		return urlMatch;
	}
}
