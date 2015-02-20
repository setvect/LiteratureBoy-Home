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
import org.springframework.web.bind.annotation.ResponseBody;

import com.setvect.literatureboy.service.ctmemo.CtmemoService;
import com.setvect.literatureboy.vo.ctmemo.CtmemoConstant;
import com.setvect.literatureboy.vo.ctmemo.CtmemoSearchCondition;
import com.setvect.literatureboy.vo.ctmemo.CtmemoVo;

@Controller
public class CtmemoController {

	@Autowired
	private CtmemoService ctmemoService;

	@RequestMapping("/ctmemo/page.do")
	public String ctmemoPage() {
		return "/app/ctmemo/main";
	}

	@RequestMapping("/ctmemo/mobile.do")
	public String ctmemoMobile() {
		return "/app/ctmemo/mobile";
	}

	@RequestMapping(value = "/ctmemo/listAllCtmemo.do")
	@ResponseBody
	public List<CtmemoVo> listAllCtmemo() {
		// init();
		List<CtmemoVo> result = ctmemoService.listCtmemo(new CtmemoSearchCondition());
		return result;
	}

	@RequestMapping("/ctmemo/newMemo.do")
	@ResponseBody
	public CtmemoVo newMemo() {
		return ctmemoService.newMemo();
	}

	/**
	 * @param ctmemo
	 * @return 변경한 메모의 z-index 값
	 */
	@RequestMapping("/ctmemo/saveMemo.do")
	@ResponseBody
	public int saveMemo(@ModelAttribute("ctmemo") CtmemoVo ctmemo) {
		ctmemo.setUptDate(new Date());
		ctmemo.setzIndex(ctmemoService.getMaxZindex());
		ctmemoService.updateCtmemo(ctmemo);
		return ctmemo.getzIndex();
	}

	@RequestMapping("/ctmemo/deleteMemo.do")
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
	@RequestMapping("/ctmemo/undelete.do")
	@ResponseBody
	public CtmemoVo undelete(ServletRequest request) {
		int seq = Integer.parseInt(request.getParameter("ctmemoSeq"));
		CtmemoVo memo = ctmemoService.undeleteCtmemo(seq);
		return memo;
	}

	/**
	 * 사용하는 스타일
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/ctmemo/listUsagestyle.do")
	@ResponseBody
	public Map<String, List<String>> listUsagestyle() {
		Map<String, List<String>> m = new HashMap<String, List<String>>();
		m.put("font", CtmemoConstant.Style.FONTSTYLE_LIST);
		m.put("bg", CtmemoConstant.Style.BGSTYLE_LIST);
		return m;
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
