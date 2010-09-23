package test;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import javax.annotation.Resource;

import org.junit.Test;

import com.setvect.common.util.GenericPage;
import com.setvect.common.util.SearchListVo;
import com.setvect.literatureboy.service.memo.MemoService;
import com.setvect.literatureboy.vo.Memo;

/**
 * DB���� �׽�Ʈ
 * 
 * @version $Id$
 */
public class MemoTestCase extends TestSystem {
	@Autowired
	private MemoService service;

	/**
	 * CRUD �׽�Ʈ
	 * 
	 * @throws Exception
	 */
	@Test
	// @Rollback(false)
	public void testCRUD() throws Exception {
		Memo m = new Memo();
		m.setId(1);
		m.setTitile("hi 1 ");
		service.addMemo(m);

		m = new Memo();
		m.setId(2);
		m.setTitile("hi 2");
		service.addMemo(m);

		m = new Memo();
		m.setId(3);
		m.setTitile("hi 3");
		service.addMemo(m);

		SearchListVo searchVo = new SearchListVo(2);
		searchVo.setPagePerItemCount(2);
		GenericPage<Memo> page = service.getPageList(searchVo);
		assertEquals(3, page.getTotalCount());
		assertEquals(2, page.getPagesize());
		assertEquals(1, page.getSize());
		assertEquals(2, page.getMaxPage());
		Collection<Memo> c = page.getList();
		for (Memo mm : c) {
			System.out.println(mm.getTitile());
		}

		Memo m2 = service.getUser(3);
		assertEquals(m.getId(), m2.getId());
		assertEquals(m.getTitile(), m2.getTitile());
	}
}