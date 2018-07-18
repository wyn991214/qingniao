package com.qingniao.core.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class StringUtil {

	/**
	 * 判断字符串为空或者为"" 
	 */
	public static boolean isNullorEmpty(Object obj) {
		if (null == obj)
			return true;
		if (("").equals(obj.toString().trim()))
			return true;
		return false;
	}

	/**
	 * 获取UUID
	 * 
	 * @return 获取UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 获取md5
	 * 
	 * @param inStr
	 * @return
	 */
	public static String ToMD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 获取md5
	 * 
	 * @param str
	 * @return
	 */
	public static String string2MD5(String str) { 
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] strByte = str.getBytes();
		byte[] buff = md5.digest(strByte);
		StringBuffer md5str = new StringBuffer();
		int digital;
		for (int i = 0; i < buff.length; i++) {
			digital = buff[i];
			if (digital < 0) {
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		// return md5str.toString().toUpperCase();
		return md5str.toString();
	}
}
