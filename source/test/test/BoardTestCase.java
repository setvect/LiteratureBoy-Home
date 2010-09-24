package test;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.setvect.common.util.GenericPage;
import com.setvect.literatureboy.service.board.BoardArticleSearch;
import com.setvect.literatureboy.service.board.BoardManagerSearch;
import com.setvect.literatureboy.service.board.BoardService;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;
import com.setvect.literatureboy.vo.board.BoardComment;

/**
 * DB연동 테스트
 * 
 * @version $Id$
 */
public class BoardTestCase extends TestSystem {
	private static final String CODE = "BDAAAA01";
	@Autowired
	private BoardService service;

	/**
	 * CRUD 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	// @Rollback(false)
	public void testCRUD() throws Exception {
		Board bd = new Board();
		bd.setBoardCode(CODE);
		bd.setCommentF(false);
		bd.setEncodeF(false);
		bd.setName("연습");
		bd.setAttachF(false);
		bd.setReplyF(false);
		bd.setUploadLimit(500000);
		service.createBoard(bd);

		Board bd1 = (Board) BeanUtils.cloneBean(service.getBoard(bd.getBoardCode()));
		Assert.assertEquals(bd.getBoardCode(), bd1.getBoardCode());

		bd.setName("Hi");
		service.updateBoard(bd);
		bd1 = (Board) BeanUtils.cloneBean(service.getBoard(bd.getBoardCode()));
		Assert.assertEquals(bd.getName(), bd1.getName());

		BoardManagerSearch boardSearch = new BoardManagerSearch(1);

		GenericPage<Board> list = service.getBoardPagingList(boardSearch);
		Assert.assertEquals(1, list.getSize());

		BoardArticle article = new BoardArticle();
		article.setBoardCode(CODE);

		String content = "";
		for (int i = 0; i < 2000; i++) {
			content += "무궁화꽃이피었습니다.";
		}
		article.setContent(content);
		article.setEncodeF(false);
		article.setIp("127.0.0.1");
		article.setName("복슬이");
		article.setTitle("제목임");
		article.setRegDate(new Date());

		service.createArticle(article);

		BoardArticle article2 = (BoardArticle) BeanUtils.cloneBean(article);
		article2.setTitle("hi~");

		service.createArticleReply(article2, article.getArticleSeq());

		article.setTitle("메렁");
		service.updateArticle(article);

		BoardArticleSearch pageCondition = new BoardArticleSearch(1);

		GenericPage<BoardArticle> articleList = service.getArticlePagingList(pageCondition);
		Assert.assertEquals(2, articleList.getSize());

		BoardComment cmt = new BoardComment();
		cmt.setArticleSeq(article.getArticleSeq());
		cmt.setContent("AAA");
		cmt.setIp("127.0.0.1");
		cmt.setName("Hi");
		cmt.setRegDate(new Date());
		service.createComment(cmt);

		BoardComment cmt2 = (BoardComment) BeanUtils.cloneBean(cmt);
		cmt2.setUserId("jjh");
		service.createComment(cmt2);

		List<BoardComment> cmtList = service.listComment(article.getArticleSeq());
		Assert.assertEquals(2, cmtList.size());

		BoardAttachFile file = new BoardAttachFile();
		file.setArticleSeq(article.getArticleSeq());
		file.setOriginalName("hi.gif");
		file.setSaveName("2123.tmp");
		file.setSize(565656);

		service.createAttachFile(file);

		List<BoardAttachFile> fileList = service.listAttachFile(article.getArticleSeq());

		Assert.assertEquals(1, fileList.size());
	}
}