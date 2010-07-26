package test;

import org.junit.Test;

import com.setvect.common.spring.SpringBeanFactory;
import com.setvect.literatureboy.service.MemoService;
import com.setvect.literatureboy.vo.Memo;

/**
 * DB연동 테스트
 * 
 * @version $Id$
 */
public class DBInteractionTestCase extends TestSystem {
	private MemoService service = (MemoService) SpringBeanFactory.getGeneralFactory().getBean("service.memo");

	/**
	 * CRUD 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCRUD() throws Exception {
		Memo m1 = new Memo();
		m1.setId(1);
		m1.setTitile("hi 안녕");
		service.addMemo(m1);

		Memo m2 = service.getUser(1);
		System.out.println(m2.getId() + ", " + m2.getTitile());

	}
}
