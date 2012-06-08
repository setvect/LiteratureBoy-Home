package com.setvect.common.util;

public class MathUtil {
	/**
	 * 해당 수의를 이진수로 표현했을때 '1'의 갯수를 구함<br>
	 * 예) n = 0, 1<br>
	 * n=3, 2<br>
	 * n=11, 3
	 * 
	 * @param num
	 *            양수값
	 * @return '1' 갯수
	 */
	public static int getSetBitCount(int num) {
		int bitCount = 0;
		while (num > 0) {
			if ((num & 1) == 1) {
				bitCount++;
			}
			num = num >> 1;
		}
		return bitCount;
	}
}
