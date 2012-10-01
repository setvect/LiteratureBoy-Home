package test;

import static org.hamcrest.CoreMatchers.is;

import java.util.Date;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;

import com.setvect.common.date.DateUtil;
import com.setvect.literatureboy.util.DateDiff;

/**
 * DB연동 테스트
 * 
 * @version $Id$
 */
public class MessageTestCase extends TestSystem {
	@Autowired
	private MessageSourceAccessor context;

	/**
	 * CRUD 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMessage() {
		String message = context.getMessage("hi", Locale.getDefault());
		Assert.assertThat(message, is("안녕"));
	}

	@Test
	public void testDateDiff() {
		Date nowDate;
		Date sourceDate;

		nowDate = DateUtil.getDate("2012-10-01 10:00:00", "yyyy-MM-dd HH:mm:ss");
		sourceDate = DateUtil.getDate("2012-10-01 09:59:51", "yyyy-MM-dd HH:mm:ss");

		String message = DateDiff.diff(nowDate, sourceDate);
		Assert.assertThat(message, is("바로 전"));

		nowDate = DateUtil.getDate("2012-10-01 10:00:00", "yyyy-MM-dd HH:mm:ss");
		sourceDate = DateUtil.getDate("2012-10-01 09:50:51", "yyyy-MM-dd HH:mm:ss");
		message = DateDiff.diff(nowDate, sourceDate);

		Assert.assertThat(message, is("9분 전"));

		nowDate = DateUtil.getDate("2012-10-01 10:00:00", "yyyy-MM-dd HH:mm:ss");
		sourceDate = DateUtil.getDate("2012-10-01 00:50:51", "yyyy-MM-dd HH:mm:ss");
		message = DateDiff.diff(nowDate, sourceDate);

		Assert.assertThat(message, is("9시간 전"));

		nowDate = DateUtil.getDate("2012-10-01 10:00:00", "yyyy-MM-dd HH:mm:ss");
		sourceDate = DateUtil.getDate("2012-05-01 00:50:51", "yyyy-MM-dd HH:mm:ss");
		message = DateDiff.diff(nowDate, sourceDate);

		Assert.assertThat(message, is("153일 전"));
		
		nowDate = DateUtil.getDate("2012-10-01 10:00:00", "yyyy-MM-dd HH:mm:ss");
		sourceDate = DateUtil.getDate("2010-05-01 00:50:51", "yyyy-MM-dd HH:mm:ss");
		message = DateDiff.diff(nowDate, sourceDate);

		Assert.assertThat(message, is("884일 전"));

	}
}