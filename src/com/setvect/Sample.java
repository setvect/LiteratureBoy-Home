package com.setvect;

import org.apache.commons.lang.StringUtils;

public class Sample {

	public static String reverse(String s) {
		return StringUtils.reverse(s);
	}

	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			System.out.println(reverse(args[i]));
		}
	}

}