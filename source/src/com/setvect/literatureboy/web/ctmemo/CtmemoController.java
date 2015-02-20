package com.setvect.literatureboy.web.ctmemo;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.setvect.common.date.DateUtil;
import com.setvect.literatureboy.service.ctmemo.CtmemoService;
import com.setvect.literatureboy.vo.ctmemo.CtmemoConstant;
import com.setvect.literatureboy.vo.ctmemo.CtmemoSearchCondition;
import com.setvect.literatureboy.vo.ctmemo.CtmemoVo;

@Controller
public class CtmemoController {

	@Autowired
	private CtmemoService ctmemoService;
	private static boolean init = false;

	@RequestMapping("/ctmemoPage.do")
	public String ctmemoPage() {
		return "/app/ctmemo/main";
	}

	@RequestMapping("/ctmemoMobile.do")
	public String ctmemoMobile() {
		return "/app/ctmemo/mobile";
	}

	@RequestMapping(value = "/listAllCtmemo.json")
	@ResponseBody
	public List<CtmemoVo> listAllCtmemo() {
		// init();
		List<CtmemoVo> result = ctmemoService.listCtmemo(new CtmemoSearchCondition());
		return result;
	}

	private void init() {
		if (!init) {
			ctmemoService.insert(getCtmemoTestData1());
			ctmemoService.insert(getCtmemoTestData2());
			ctmemoService.insert(getCtmemoTestData3());
			init = true;
		}
	}

	@RequestMapping("/newMemo.json")
	@ResponseBody
	public CtmemoVo newMemo() {
		return ctmemoService.newMemo();
	}

	/**
	 * @param ctmemo
	 * @return 변경한 메모의 z-index 값
	 */
	@RequestMapping("/saveMemo.do")
	@ResponseBody
	public int saveMemo(@ModelAttribute("ctmemo") CtmemoVo ctmemo) {
		ctmemo.setUptDate(new Date());
		ctmemo.setzIndex(ctmemoService.getMaxZindex());
		ctmemoService.updateCtmemo(ctmemo);
		return ctmemo.getzIndex();
	}

	@RequestMapping("/deleteMemo.do")
	@ResponseBody
	public String deleteMemo(ServletRequest request) {
		int ctmemoSeq = Integer.parseInt(request.getParameter("ctmemoSeq"));
		ctmemoService.removeCtmemo(ctmemoSeq);
		return "true";
	}

	/**
	 * 삭제 취소
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/undelete.json")
	@ResponseBody
	public CtmemoVo undelete(ServletRequest request) {
		int ctmemoSeq = Integer.parseInt(request.getParameter("ctmemoSeq"));
		CtmemoVo memo = ctmemoService.undeleteCtmemo(ctmemoSeq);
		return memo;
	}

	/**
	 * 사용하는 스타일
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/listUsagestyle.json")
	@ResponseBody
	public Map<String, List<String>> listUsagestyle() {
		Map<String, List<String>> m = new HashMap<String, List<String>>();
		m.put("font", CtmemoConstant.Style.FONTSTYLE_LIST);
		m.put("bg", CtmemoConstant.Style.BGSTYLE_LIST);
		return m;
	}

	public CtmemoVo getCtmemoTestData1() {
		CtmemoVo ctmemo = new CtmemoVo();
		ctmemo.setCtmemoSeq(1);
		ctmemo.setContent("내용1\n복슬이");
		ctmemo.setBgCss(CtmemoConstant.Style.BGSTYLE_1);
		ctmemo.setFontCss(CtmemoConstant.Style.FONTSTYLE_1);
		ctmemo.setWidth(160);
		ctmemo.setHeight(130);
		ctmemo.setPositionX(220);
		ctmemo.setPositionY(220);
		ctmemo.setzIndex(ctmemoService.getMaxZindex());
		Date date = DateUtil.getDateTime("2015-02-13 11:22:11");
		ctmemo.setRegDate(date);
		ctmemo.setUptDate(date);

		return ctmemo;
	}

	public CtmemoVo getCtmemoTestData2() {
		CtmemoVo ctmemo = new CtmemoVo();
		ctmemo.setCtmemoSeq(2);
		ctmemo.setContent("내용2");
		ctmemo.setBgCss(CtmemoConstant.Style.BGSTYLE_2);
		ctmemo.setFontCss(CtmemoConstant.Style.FONTSTYLE_2);
		ctmemo.setWidth(160);
		ctmemo.setHeight(150);
		ctmemo.setPositionX(100);
		ctmemo.setPositionY(100);
		ctmemo.setzIndex(1);
		Date date = DateUtil.getDate("2015-02-14");
		ctmemo.setRegDate(date);
		ctmemo.setUptDate(date);
		return ctmemo;
	}

	public CtmemoVo getCtmemoTestData3() {
		CtmemoVo ctmemo = new CtmemoVo();
		ctmemo.setCtmemoSeq(2);
		ctmemo.setContent("처음 느낀 그대 눈빛은 혼자만의 오해였던가요\n" + "해맑은 미소로 나를 바보로 만들었소\n" + "내 곁을 떠나가던 날\n" + "가슴에 품었던 분홍빛의\n"
				+ "수많은 추억들이 푸르게 바래졌소 ");
		ctmemo.setBgCss(CtmemoConstant.Style.BGSTYLE_2);
		ctmemo.setFontCss(CtmemoConstant.Style.FONTSTYLE_2);
		ctmemo.setWidth(160);
		ctmemo.setHeight(150);
		ctmemo.setPositionX(300);
		ctmemo.setPositionY(350);
		ctmemo.setzIndex(1);
		Date date = DateUtil.getDate("2015-02-15");
		ctmemo.setRegDate(date);
		ctmemo.setUptDate(date);
		return ctmemo;
	}

	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				Date parsedDate = new Date(Long.parseLong(value));
				setValue(new Date(parsedDate.getTime()));
			}
		});
	}
}
