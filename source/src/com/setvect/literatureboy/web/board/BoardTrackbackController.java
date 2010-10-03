package com.setvect.literatureboy.web.board;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.setvect.common.log.LogPrinter;
import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.service.board.BoardService;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardTrackback;

/**
 * 트래백 제어<br>
 * 신기하게도 annotation으로 하면 * 패턴을 인식하지 못해 xml에 명시하였음
 */
public class BoardTrackbackController extends AbstractController {

	@Autowired
	private BoardService boardService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String vp = request.getPathInfo();
		String[] param = vp.substring(1).split("/");
		// 트래백
		try {
			int articleID = Integer.parseInt(param[1]);
			boolean result = trackback(articleID, request);
			responseTrack(result, response);
			return null;

		} catch (Exception e) {
  			LogPrinter.out.warn("게시판  트래백 에러(" + vp + ")");
			sendNotFoundError(response, vp);
		}
		return null;
	}

	/**
	 * 다른 블로그로 부터 들어오는 트래백 정보를 등록처리하고, 성공여부를 알려줌
	 * 
	 * @param articleID
	 *            트래백이 등록될 콘텐츠 아이디
	 * @param req
	 *            request
	 * @return 트래백 틍록이 성공 true, 아니면 false
	 */
	private boolean trackback(int articleID, HttpServletRequest req) {
		String blogName = req.getParameter("blog_name");
		String title = req.getParameter("title");
		String url = req.getParameter("url");
		String excerpt = req.getParameter("excerpt");

		// 필수 데이터 점검
		if (StringUtilAd.isEmpty(blogName) || StringUtilAd.isEmpty(title) || StringUtilAd.isEmpty(url)) {
			return false;
		}

		BoardTrackback trackback = new BoardTrackback();
		BoardArticle b = boardService.getArticle(articleID);
		// 해당 글에 게시물 정보가 없으면 트래백 불허
		if (b == null) {
			return false;
		}
		trackback.setArticleSeq(articleID);
		trackback.setBlogName(blogName);
		trackback.setTitle(title);
		trackback.setUrl(url);
		trackback.setExcerpt(excerpt);
		trackback.setRegDate(new Date());
		try {
			 boardService.createTrackback(trackback);
		} catch (Exception e) {
			LogPrinter.out.warn(e.getMessage(), e);
			return false;
		}
		return true;
	}

	/**
	 * 트래백 등록 성공 여부에 따른 응답 메세지 전달
	 * 
	 * @param result
	 *            트래백 등록 성공 경부
	 * @param res
	 *            response
	 * @throws IOException
	 */
	protected void responseTrack(boolean result, HttpServletResponse res) throws IOException {
		StringBuffer output = new StringBuffer();

		output.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\" ?>");
		output.append("<response>");
		if (result) {
			output.append("<error>0</error>");
			output.append("<message>success</message>");
		}
		else {
			output.append("<error>1</error>");
			output.append("<message>fail</message>");
		}
		output.append("</response>");

		ServletOutputStream out = res.getOutputStream();
		out.print(output.toString());
	}

	/**
	 * @param res
	 *            응탐
	 * @param vp
	 *            경로
	 * @throws IOException
	 */
	protected void sendNotFoundError(HttpServletResponse res, String vp) throws IOException {
		String mesg = "유효하지 않은 가상 경로 : [VPATH=" + vp + "]";
		LogPrinter.out.warn(mesg);
		res.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
}