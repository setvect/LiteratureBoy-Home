package com.setvect.common.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * ����ȭ ��ü�� �����, ����ȭ�� ��ü�� ���� ��Ų��.
 * 
 * @version $Id: SerializerUtil.java,v 1.1 2006/07/12 05:33:59 setvect Exp $
 */
public class SerializerUtil {

	/**
	 * not instance
	 */
	private SerializerUtil() {

	}

	/**
	 * ��ü�� ������ ���Ϸ� ����ȭ�� �����.
	 * 
	 * @param obj
	 *            ����ȭ �� ��ü
	 * @param path
	 *            ����ȭ�� ������ ������ ������ Ǯ �н�(���ϸ� ����)
	 * @throws IOException
	 */
	public static void makeSerializer(Object obj, String path) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		makeSerializer(obj, fos);
		fos.close();
	}

	/**
	 * ��ü�� ������ ����ȭ �Ѵ�.
	 * 
	 * @param obj
	 *            ����ȭ �� ��ü
	 * @param os
	 *            ����ȭ �����Ͱ� ���� ��Ʈ��
	 * @throws IOException
	 */
	public static void makeSerializer(Object obj, OutputStream os) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}

	/**
	 * ��ü�� ����ȭ �Ͽ� �� Byte �����͸� BASE64�ڵ�� ������ ���ڿ��� �����Ѵ�.
	 * 
	 * @param obj
	 *            ����ȭ �� ��ü
	 * @return BASE64�� ���ڵ��� ���ڿ�
	 * @throws IOException
	 */
	public static String makeBase64Encode(Object obj) throws IOException {
		ByteArrayOutputStream os = null;
		BufferedInputStream sourceIn = null;
		ByteArrayOutputStream sourceOut = null;
		String encode;
		try {
			os = new ByteArrayOutputStream();
			// ��ü�� ������ ��Ʈ�� ���·� ������
			SerializerUtil.makeSerializer(obj, os);

			// BASE64 ���·� �����͸� �����͸� TEXT ���·� ������
			BASE64Encoder encoder = new BASE64Encoder();
			sourceIn = new BufferedInputStream(new ByteArrayInputStream(os.toByteArray()));
			sourceOut = new ByteArrayOutputStream();
			encoder.encode(sourceIn, sourceOut);
			encode = new String(sourceOut.toByteArray());
		} finally {
			if (sourceOut != null) {
				sourceOut.close();
			}
			if (sourceIn != null) {
				sourceIn.close();
			}
			if (os != null) {
				os.close();
			}
		}

		return encode;
	}

	/**
	 * ���Ϸ� ������ �ִ� ����ȭ ������ �̿��Ͽ� ��ü�� �����Ѵ�.
	 * 
	 * @param path
	 *            ����ȭ ������ ��� �ִ� ���� ��� (���ϸ� ����)
	 * @return ����ȭ ����Ȱ�
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Object restoreSerializer(String path) throws Exception {
		FileInputStream fis = new FileInputStream(path);
		Object rtn = restoreSerializer(fis);
		fis.close();
		return rtn;
	}

	/**
	 * ��Ʈ������ ���� ����ȭ �����͸� ������ ��ü�� �����.
	 * 
	 * @param is
	 *            ����ȭ �����Ͱ� �ִ� ��Ʈ��
	 * @return ����ȭ ����Ȱ�
	 * @throws Exception
	 */
	public static Object restoreSerializer(InputStream is) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(is);
		Object rtn = ois.readObject();
		ois.close();
		return rtn;
	}

	/**
	 * Base64 �ڵ带 ���� ����Ʈ ������ ���� ��ü ������ ��ȯ�Ѵ�.
	 * 
	 * @param base64
	 *            ��ü ����ȭ ������ ��� �ִ� base64 ������
	 * @return ��ȯ�� ��ü
	 * @throws Exception
	 */
	public static Object restoreBase64Decode(String base64) throws Exception {
		ByteArrayInputStream targetIn = null;
		ByteArrayOutputStream targetOut = null;
		BufferedInputStream sourceIn = null;
		Object obj;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			targetIn = new ByteArrayInputStream(base64.getBytes());
			targetOut = new ByteArrayOutputStream();
			decoder.decodeBuffer(targetIn, targetOut);

			sourceIn = new BufferedInputStream(new ByteArrayInputStream(targetOut.toByteArray()));
			obj = SerializerUtil.restoreSerializer(sourceIn);
		} finally {
			if (sourceIn != null) {
				sourceIn.close();
			}
			if (targetOut != null) {
				targetOut.close();
			}
			if (targetIn != null) {
				targetIn.close();
			}
		}

		return obj;
	}
}
