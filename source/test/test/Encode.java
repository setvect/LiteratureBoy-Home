package test;

import java.io.IOException;

import com.setvect.common.util.StringUtilAd;

public class Encode {
	public static void main(String args[]) throws IOException {
		String a = StringUtilAd.encodePassword("1234", "MD5");
		System.out.println(a);
	}
}