package com.setvect.literatureboy.service.ctmemo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setvect.literatureboy.db.CtmemoDao;
import com.setvect.literatureboy.vo.ctmemo.CtmemoConstant;
import com.setvect.literatureboy.vo.ctmemo.CtmemoSearchCondition;
import com.setvect.literatureboy.vo.ctmemo.CtmemoVo;

@Service
public class CtmemoService {
	@Autowired
	private CtmemoDao ctmemoDao;

	public CtmemoVo getCtmemo(int ctmemoId) {
		return ctmemoDao.getCtmemo(ctmemoId);
	}

	/**
	 * z-index 최대 값
	 * 
	 * @return
	 */
	public int getMaxZindex() {
		return ctmemoDao.getMaxZindex();
	}

	public List<CtmemoVo> listCtmemo(CtmemoSearchCondition condition) {
		return ctmemoDao.listCtmemo(condition);
	}

	public void insert(CtmemoVo ctmemo) {
		ctmemoDao.insert(ctmemo);
	}

	/**
	 * 새로운 메모장 생성<br>
	 * 생성과 동시에 DB에 저장
	 * 
	 * @return
	 */
	public CtmemoVo newMemo() {
		CtmemoVo ctmemo = new CtmemoVo();
		ctmemo.setContent(" ");
		ctmemo.setBgCss(CtmemoConstant.Style.BGSTYLE_1);
		ctmemo.setFontCss(CtmemoConstant.Style.FONTSTYLE_1);
		ctmemo.setWidth(150);
		ctmemo.setHeight(150);
		ctmemo.setPositionX(1);
		ctmemo.setPositionY(1);
		ctmemo.setzIndex(getMaxZindex());
		Date date = new Date();
		ctmemo.setRegDate(date);
		ctmemo.setUptDate(date);
		insert(ctmemo);
		return ctmemo;
	}

	/**
	 * 삭제 취소
	 * 
	 * @param ctmemoSeq
	 * @return
	 */
	public CtmemoVo undeleteCtmemo(int ctmemoSeq) {
		CtmemoVo memo = getCtmemo(ctmemoSeq);
		memo.setDeleteF(false);
		updateCtmemo(memo);
		return memo;
	}

	public void updateCtmemo(CtmemoVo ctmemo) {
		ctmemoDao.updateCtmemo(ctmemo);
	}

	public void removeCtmemo(int ctmemoId) {
		CtmemoVo ctmemo = getCtmemo(ctmemoId);
		ctmemo.setDeleteF(true);
		updateCtmemo(ctmemo);
	}

}
