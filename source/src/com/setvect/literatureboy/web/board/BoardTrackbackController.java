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
 * Ʈ���� ����<br>
 * �ű��ϰԵ� annotation���� �ϸ� * ������ �ν����� ���� xml�� ����Ͽ���
 */
public class BoardTrackbackController extends AbstractController {

	@Autowired
	private BoardService boardService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String vp = request.getPathInfo();
		String[] param = vp.substring(1).split("/");
		// Ʈ����
		try {
			int articleID = Integer.parseInt(param[0]);
			boolean result = trackback(articleID, request);
			responseTrack(result, response);
			return null;

		} catch (Exception e) {
  			LogPrinter.out.warn("�Խ���  Ʈ���� ����(" + vp + ")");
			sendNotFoundError(response, vp);
		}
		return null;
	}

	/**
	 * �ٸ� ��α׷� ���� ������ Ʈ���� ������ ���ó���ϰ�, �������θ� �˷���
	 * 
	 * @param articleID
	 *            Ʈ������ ��ϵ� ������ ���̵�
	 * @param req
	 *            request
	 * @return Ʈ���� �v���� ���� true, �ƴϸ� false
	 */
	private boolean trackback(int articleID, HttpServletRequest req) {
		String blogName = req.getParameter("blog_name");
		String title = req.getParameter("title");
		String url = req.getParameter("url");
		String excerpt = req.getParameter("excerpt");

		// �ʼ� ������ ����
		if (StringUtilAd.isEmpty(blogName) || StringUtilAd.isEmpty(title) || StringUtilAd.isEmpty(url)) {
			return false;
		}

		BoardTrackback trackback = new BoardTrackback();
		BoardArticle b = boardService.getArticle(articleID);
		// �ش� �ۿ� �Խù� ������ ������ Ʈ���� ����
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
	 * Ʈ���� ��� ���� ���ο� ���� ���� �޼��� ����
	 * 
	 * @param result
	 *            Ʈ���� ��� ���� ���
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
	 *            ��Ž
	 * @param vp
	 *            ���
	 * @throws IOException
	 */
	protected void sendNotFoundError(HttpServletResponse res, String vp) throws IOException {
		String mesg = "��ȿ���� ���� ���� ��� : [VPATH=" + vp + "]";
		LogPrinter.out.warn(mesg);
		res.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
}