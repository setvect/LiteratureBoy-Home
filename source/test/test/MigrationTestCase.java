package test;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.setvect.literatureboy.service.memo.MemoService;

/**
 * 마이그레이션 프로그램
 * 
 * @version $Id: MemoTestCase.java 63 2010-08-16 12:24:44Z setvect@naver.com $
 */
public class MigrationTestCase extends TestSystem {
	@Resource
	private MemoService service;

	/**
	 * CRUD 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	@Rollback(false)
	public void migration() throws Exception {
		Class.forName("org.gjt.mm.mysql.Driver");
		Connection sourceConnecton = DriverManager.getConnection("jdbc:mysql://localhost:3306/setvect", "root", "7805");
	}
}