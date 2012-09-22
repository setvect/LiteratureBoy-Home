package test.com.util;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.setvect.common.util.FileUtil;
import com.setvect.common.util.Md5Util;

public class FileUtilTestCase {
	@Test
	public void test_getSubFiles() {
		Set<String> ext = new HashSet<String>();
		ext.add("docx");
		ext.add("pptx");
		List<File> files = FileUtil.getSubFiles(new File("D:\\04.문서"), false, ext);
		for (File f : files) {
			System.out.println(f);
		}

		files = FileUtil.getSubFiles(new File("D:\\04.문서"), true, ext);
		for (File f : files) {
			System.out.println(f);
		}
	}

	@Test
	public void test_getMd5() {
		String md5 = Md5Util.getMD5Checksum(new File("D:\\04.문서\\DOR에러 메시지 정리_정호.docx"));
		System.out.println(md5);
	}
}
