package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.setvect.literatureboy.boot.EnvirmentInit;

/**
 * 테스트 하기위한 어플리케이션 bootup 과정 진행
 * 
 * @version $Id$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "hibernateTxManager", defaultRollback = true)
@Transactional
public class TestSystem {

	@BeforeClass
	public static void load() {
		// 현재 클래스 경로를 추적해 웹루트 디렉토리를 찾음. 꼼수임
		EnvirmentInit.bootUp();
	}

	@AfterClass
	public static void afterClass() {
		// nothing
	}
}
