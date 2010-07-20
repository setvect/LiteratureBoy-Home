package com.setvect.literatureboy.config;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * ������Ʈ ���� ���� ����
 * 
 * @version $Id$
 */
public class EnvProperty {
	private static Configuration config;

	/**
	 * @param propertise
	 *            propertise ����
	 */
	public static void init(File propertise) {
		try {
			PropertiesConfiguration conf = new PropertiesConfiguration(
					propertise);
			// ���� ���� �ڵ� ����
			conf.setReloadingStrategy(new FileChangedReloadingStrategy());
			config = conf;
		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	// Delegate
	public static boolean containsKey(String paramString) {
		return config.containsKey(paramString);
	}

	public static Iterator getKeys(String paramString) {
		return config.getKeys(paramString);
	}

	public static Iterator getKeys() {
		return config.getKeys();
	}

	public static boolean getBoolean(String paramString) {
		return config.getBoolean(paramString);
	}

	public static boolean getBoolean(String paramString, boolean paramBoolean) {
		return config.getBoolean(paramString, paramBoolean);
	}

	public static byte getByte(String paramString) {
		return config.getByte(paramString);
	}

	public static byte getByte(String paramString, byte paramByte) {
		return config.getByte(paramString, paramByte);
	}

	public static double getDouble(String paramString) {
		return config.getDouble(paramString);
	}

	public static double getDouble(String paramString, double paramDouble) {
		return config.getDouble(paramString, paramDouble);
	}

	public static float getFloat(String paramString) {
		return config.getFloat(paramString);
	}

	public static float getFloat(String paramString, float paramFloat) {
		return config.getFloat(paramString, paramFloat);
	}

	public static Float getFloat(String paramString, Float paramFloat) {
		return config.getFloat(paramString, paramFloat);
	}

	public static int getInt(String paramString) {
		return config.getInt(paramString);
	}

	public static int getInt(String paramString, int paramInt) {
		return config.getInt(paramString, paramInt);
	}

	public static long getLong(String paramString) {
		return config.getLong(paramString);
	}

	public static long getLong(String paramString, long paramLong) {
		return config.getLong(paramString, paramLong);
	}

	public static short getShort(String paramString) {
		return config.getShort(paramString);
	}

	public static short getShort(String paramString, short paramShort) {
		return config.getShort(paramString, paramShort);
	}

	public static BigDecimal getBigDecimal(String paramString) {
		return config.getBigDecimal(paramString);
	}

	public static BigDecimal getBigDecimal(String paramString,
			BigDecimal paramBigDecimal) {
		return config.getBigDecimal(paramString, paramBigDecimal);
	}

	public static BigInteger getBigInteger(String paramString) {
		return config.getBigInteger(paramString);
	}

	public static BigInteger getBigInteger(String paramString,
			BigInteger paramBigInteger) {
		return config.getBigInteger(paramString, paramBigInteger);
	}

	public static String getString(String paramString) {
		return config.getString(paramString);
	}

	public static String getString(String paramString1, String paramString2) {
		return config.getString(paramString1, paramString2);
	}

	public static String[] getStringArray(String paramString) {
		return config.getStringArray(paramString);
	}

	public static List getList(String paramString) {
		return config.getList(paramString);
	}

	public static List getList(String paramString, List paramList) {
		return config.getList(paramString, paramList);
	}

}
