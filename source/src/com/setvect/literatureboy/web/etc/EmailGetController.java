package com.setvect.literatureboy.web.etc;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.setvect.common.util.NumberFormat;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.config.EnvirmentProperty;

/**
 * 이메일 주소 알아 내기
 * 
 * @version $Id$
 */
@Controller
public class EmailGetController extends HttpServlet {
	private static final String EMAIL = EnvirmentProperty.getString("com.setvect.literatureboy.email");
	/** */
	private static final long serialVersionUID = -7894468662893018437L;

	/**
	 * 서브 명령어 정의
	 */
	public static enum Mode {
		INPUT_FORM, VIEW_FORM,
	}

	/**
	 * 뷰에 전달할 객체를 가르키는 키
	 */
	public static enum AttributeKey {
		MODE, EMAIL_NUMBER, EMAIL
	}

	/**
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/emailget.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView();

		String mode = request.getParameter("mode");

		Mode m;
		if (StringUtilAd.isEmpty(mode)) {
			m = Mode.INPUT_FORM;
		}
		else {
			m = Mode.valueOf(mode);
		}

		if (m == Mode.INPUT_FORM) {
			Random rnd = new Random();
			int inputNum = rnd.nextInt(10000);
			mav.addObject(AttributeKey.MODE.name(), Mode.VIEW_FORM);
			String inputStr = NumberFormat.getNumberString("0000", inputNum);
			request.getSession().setAttribute(AttributeKey.EMAIL_NUMBER.name(), inputStr);
			mav.setViewName("/app/emailget/emailget_form");
		}
		else if (m == Mode.VIEW_FORM) {
			String input = request.getParameter("inputNumber");
			String compare = (String) request.getSession().getAttribute(AttributeKey.EMAIL_NUMBER.name());
			if (!StringUtilAd.isEmpty(input) && input.equals(compare)) {
				mav.addObject(AttributeKey.EMAIL.name(), EMAIL);
			}
			else {
				// 입력값이 틀리면 null
				mav.addObject(AttributeKey.EMAIL.name(), null);
			}
			mav.setViewName("/app/emailget/emailget_view");
		}
		return mav;
	}
}