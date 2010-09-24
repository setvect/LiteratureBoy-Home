package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.setvect.literatureboy.service.board.BoardService;
import com.setvect.literatureboy.vo.board.Board;
import com.setvect.literatureboy.vo.board.BoardArticle;
import com.setvect.literatureboy.vo.board.BoardAttachFile;

/**
 * 마이그레이션 프로그램
 * 
 * @version $Id: MemoTestCase.java 63 2010-08-16 12:24:44Z setvect@naver.com $
 */
public class MigrationTestCase extends TestSystem {
	@Autowired
	private BoardService boardService;
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * CRUD 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	@Rollback(false)
	public void migration() throws Exception {
		Session session = sessionFactory.getCurrentSession();

		SQLQuery s = session.createSQLQuery("delete from TBBD_BOARD_FILE");
		s.executeUpdate();

		s = session.createSQLQuery("delete from TBBC_BOARD_COMMENT	");
		s.executeUpdate();

		s = session.createSQLQuery("delete from TBBB_BOARD_ARTICLE");
		s.executeUpdate();

		s = session.createSQLQuery("delete from TBBA_BOARD_MANAGER");
		s.executeUpdate();

		Class.forName("org.gjt.mm.mysql.Driver");
		Connection sourceConnecton = DriverManager.getConnection("jdbc:mysql://localhost:3306/setvect", "root", "7805");
		Statement st = sourceConnecton.createStatement();
		ResultSet rs = st.executeQuery("select * from TBBA_BOARD_MANAGER");
		while (rs.next()) {
			Board board = new Board();
			board.setBoardCode(rs.getString("BOARD_CODE"));
			board.setName(rs.getString("NAME"));
			board.setUploadLimit(rs.getInt("FILESIZE_LM"));
			board.setReplyF(rs.getString("REPLY_F").equals("Y"));
			board.setCommentF(rs.getString("COMMENT_F").equals("Y"));
			board.setAttachF(rs.getString("PDS_F").equals("Y"));
			board.setEncodeF(rs.getString("ENCODE_F").equals("Y"));
			board.setDeleteF(false);
			boardService.createBoard(board);
		}
		rs.close();
		st.close();

		st = sourceConnecton.createStatement();
		rs = st.executeQuery("select count(*) from TBBB_BOARD_ARTICLE");
		rs.next();
		int total = rs.getInt(1);

		rs.close();
		st.close();

		st = sourceConnecton.createStatement();
		rs = st.executeQuery("select * from TBBB_BOARD_ARTICLE order by IDX2 ");
		int count = 0;
		while (rs.next()) {

			BoardArticle article = new BoardArticle();
			article.setBoardCode(rs.getString("BOARD_CODE"));
			article.setUserId(rs.getString("MEMBER_ID"));
			article.setIdx1(rs.getInt("IDX1"));
			article.setIdx2(rs.getInt("IDX2"));
			article.setIdx3(rs.getInt("IDX3"));
			article.setDepthLevel(rs.getInt("DEPTH_LEV"));
			article.setTitle(rs.getString("Title"));
			article.setName(rs.getString("NAME"));
			article.setEmail(rs.getString("EMAIL"));
			article.setPasswd(rs.getString("PASSWD"));
			article.setContent(rs.getString("CONTENT"));
			article.setIp(rs.getString("IP"));
			article.setHitCount(rs.getInt("HIT_COUNT"));
			article.setEncodeF(rs.getString("ENCODE_F").equals("Y"));
			article.setRegDate(rs.getDate("REG_DATE"));
			article.setDeleteF(false);
			boardService.createArticleMigration(article);

			int articleSeq = rs.getInt("ARTICLE_SEQ");

			Statement stComment = sourceConnecton.createStatement();
			ResultSet rsComment = stComment.executeQuery("select * from TBBD_BOARD_FILE where ARTICLE_SEQ = "
					+ articleSeq + " order by ARTICLE_SEQ");
			while (rsComment.next()) {
				BoardAttachFile attach = new BoardAttachFile();
				attach.setArticleSeq(article.getArticleSeq());
				attach.setOriginalName(rsComment.getString("ORG_FILENAME"));
				attach.setSaveName(rsComment.getString("SAV_FILENAME"));
				attach.setSize(rsComment.getInt("FILE_SIZE"));
				boardService.createAttachFile(attach);
			}
			rsComment.close();
			stComment.close();

			count++;
			if (count % 10 == 0) {
				System.out.println(count + " / " + total + " Running");
			}
		}
		System.out.println(total + " Complete");
		rs.close();
		st.close();

		sourceConnecton.close();
	}
}