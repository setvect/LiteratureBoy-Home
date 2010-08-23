package com.setvect.literatureboy.web.user;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.common.http.CookieProcess;
import com.setvect.common.util.SerializerUtil;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.service.user.UserService;
import com.setvect.literatureboy.vo.user.User;
import com.setvect.literatureboy.web.CommonUtil;
import com.setvect.literatureboy.web.ConstraintWeb;

/**
 * ȯ�漳��>��� ���� �޴� ��Ʈ�ѷ�
 */
@Controller
public class LoginController {
	/**
	 * ���� ��ɾ� ����
	 */
	public static enum Mode {
		LOGIN_FORM, LOGIN_ACTION
	}

	/**
	 * �信 ������ ��ü�� ����Ű�� Ű
	 */
	public static enum AttributeKey {
		// �α��� ���
		LOGIN_FAIL,
	}

	@Resource
	private UserService userService;

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/login.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		response.setCharacterEncoding(request.getCharacterEncoding());
		String mode = request.getParameter("mode");

		Mode m;
		if (StringUtilAd.isEmpty(mode)) {
			m = Mode.LOGIN_FORM;
		}
		else {
			m = Mode.valueOf(mode);
		}

		mav.setViewName(ConstraintWeb.INDEX_VIEW);
		if (m == Mode.LOGIN_ACTION) {
			String userId = request.getParameter("userId");
			String passwd = request.getParameter("passwd");

			User user = userService.getUser(userId);
			boolean loginStat = false;
			if (user != null) {
				String passwdEncode = StringUtilAd.encodePassword(passwd, ConstraintWeb.PASSWD_ALGORITHM);
				loginStat = user.getPasswd().equals(passwdEncode);
			}
			// �α��� ����
			if (loginStat) {
				// ��й�ȣ�� ���� ���� �ʰ� �ϱ� ����
				user.setPasswd(null);

				String cookieData = SerializerUtil.makeBase64Encode(user);

				// iis������ �ٹٲ� ������ ������ ��Ű�� ������ �ȵȴ�. �׷��� �� �ٲ��� ����
				cookieData = cookieData.replaceAll("\r", "");
				cookieData = cookieData.replaceAll("\n", "");

				Cookie loginCookie = new Cookie(ConstraintWeb.USER_SESSION, cookieData);
				loginCookie.setPath("/");
				response.addCookie(loginCookie);
			}
			// �α��� ����
			else {
				mav.addObject(AttributeKey.LOGIN_FAIL.name(), true);
				m = Mode.LOGIN_FORM;
			}
		}

		if (m == Mode.LOGIN_FORM) {
			User user = CommonUtil.getLoginSession(request);
			mav.addObject(ConstraintWeb.INCLUDE_PAGE, "/app/user/login/login.jsp");
		}

		return mav;
	}

}