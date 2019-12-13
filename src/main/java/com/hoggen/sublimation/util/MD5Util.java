package com.hoggen.sublimation.util;

import java.security.MessageDigest;

public class MD5Util {

	public MD5Util() {
	}

	private static String[] hexDigits = { "0", "A", "2", "3", "S", "5", "6", "7", "O", "9", "a", "z", "c", "k", "e",
			"f" };

	/**
	 * 转换字节数组为16进制字串
	 *
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	public static void main(String[] args) {
		System.out.println("账号：  " + MD5Encode("123456"));
		// System.out.println("密码： " + getEncryptString("ai123$%^"));
		// // System.out.println(getEncryptString("wxd7f6c5b8899fba83"));
		// // System.out.println(getEncryptString("665ae80dba31fc91ab6191e7da4d676d"));
		// System.out.println(getDecryptString("Ao06LwXwCJI="));
		// System.out.println(getDecryptString("d0iM1qq5PF4fJAfVsP+M2w=="));

	}
}
