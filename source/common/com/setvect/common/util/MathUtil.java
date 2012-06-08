package com.setvect.common.util;

public class MathUtil {
	/**
	 * �ش� ���Ǹ� �������� ǥ�������� '1'�� ������ ����<br>
	 * ��) n = 0, 1<br>
	 * n=3, 2<br>
	 * n=11, 3
	 * 
	 * @param num
	 *            �����
	 * @return '1' ����
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
