package test;

import java.io.File;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.setvect.literatureboy.boot.EnvirmentInit;

/**
 * �׽�Ʈ �ϱ����� ���ø����̼� bootup ���� ����
 * 
 * @version $Id$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "hibernateTxManager", defaultRollback = true)
@Transactional
public class TestSystem {
	private static final String WEB_ROOT_NAME = "WebContent";

	@BeforeClass
	public static void load() {
		URL a = TestSystem.class.getResource("");
		File currentPath = new File(a.getFile());

		// ���� Ŭ���� ��θ� ������ ����Ʈ ���丮�� ã��. �ļ���
		File projectRoot = currentPath.getParentFile().getParentFile().getParentFile().getParentFile();
		EnvirmentInit.bootUp();
	}

	@AfterClass
	public static void afterClass() {
		// nothing
	}
}
