package test;

import java.io.File;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.setvect.literatureboy.boot.EnvirmentInit;

/**
 * 테스트 하기위한 어플리케이션 bootup 과정 진행
 * 
 * @version $Id$
 */
public class TestSystem {
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
}
