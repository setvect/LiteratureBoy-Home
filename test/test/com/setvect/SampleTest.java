package test.com.setvect;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.setvect.Sample;

public class SampleTest {

	@Test
	public void testReverse() {
		String actual = Sample.reverse("hello");
		String expected = "olleh";
		assertEquals(expected, actual);
	}

}