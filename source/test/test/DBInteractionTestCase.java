package test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.setvect.literatureboy.service.MemoService;
import com.setvect.literatureboy.vo.Memo;

/**
 * DB���� �׽�Ʈ
 * 
 * @version $Id$
 */
public class DBInteractionTestCase extends TestSystem {
	@Autowired
	private MemoService service;

	/**
	 * CRUD �׽�Ʈ
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCRUD() throws Exception {
		Memo m1 = new Memo();
		m1.setId(1);
		m1.setTitile("hi ");
		service.addMemo(m1);

		Memo m2 = service.getUser(1);
		System.out.println(m2.getId() + ", " + m2.getTitile());
	}
}
