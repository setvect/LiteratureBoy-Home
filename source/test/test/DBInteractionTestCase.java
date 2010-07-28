package test;

import java.io.File;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalSpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import com.setvect.common.spring.SpringBeanFactory;
import com.setvect.literatureboy.boot.EnvirmentInit;
import com.setvect.literatureboy.service.MemoService;
import com.setvect.literatureboy.vo.Memo;

/**
 * DB연동 테스트
 * 
 * @version $Id$
 */
@Transactional
public class DBInteractionTestCase extends AbstractTransactionalSpringContextTests {

	private static final String WEB_ROOT_NAME = "WebContent";
	private static final String CONFIG_PROPERTIES = "WEB-INF/config.properties";

	@BeforeClass
	public static void load() {
		URL a = TestSystem.class.getResource("");
		File currentPath = new File(a.getFile());

		File projectRoot = currentPath.getParentFile().getParentFile().getParentFile();
		File webRoot = new File(projectRoot, WEB_ROOT_NAME);
		EnvirmentInit.bootUp(webRoot, CONFIG_PROPERTIES);
	}

	@AfterClass
	public static void afterClass() {
		// nothing
	}

	protected String[] getConfigLocations() {
		return new String[] { "classpath:applicationContext.xml" };
	}

	/**
	 * CRUD 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCRUD() throws Exception {
		MemoService service = (MemoService) SpringBeanFactory.getGeneralFactory().getBean("service.memo");
		Memo m1 = new Memo();
		m1.setId(1);
		m1.setTitile("hi ");
		service.addMemo(m1);

		Memo m2 = service.getUser(1);
		System.out.println(m2.getId() + ", " + m2.getTitile());
	}
}
