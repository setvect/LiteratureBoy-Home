package test;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import anyframe.common.Page;

import com.setvect.common.util.PagingCondition;
import com.setvect.literatureboy.service.memo.MemoService;
import com.setvect.literatureboy.vo.Memo;

/**
 * DB연동 테스트
 * 
 * @version $Id$
 */
public class DBInteractionTestCase extends TestSystem {
	@Resource
	public MemoService service;

	/**
	 * CRUD 테스트
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

		PagingCondition searchVo = new PagingCondition(2, 2);
		Page page = service.getPageList(searchVo);
		Assert.assertEquals(3, page.getTotalCount());
		Assert.assertEquals(2, page.getPagesize());
		Assert.assertEquals(1, page.getSize());
		Assert.assertEquals(2, page.getMaxPage());

		Memo m2 = service.getUser(1);
		System.out.println(m2.getId() + ", " + m2.getTitile());
	}
}
