package com.alanma.doraemon.utils.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
	private static final String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;

		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; ++i) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	public static String encode(String code) {
		String resultString = null;
		try {
			resultString = new String(code);
			MessageDigest md = MessageDigest.getInstance("MD5");

			resultString = byteToString(md.digest(code.getBytes()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	public static String encodeDouble(String code) {
		String resultString = null;
		try {
			resultString = encode(code);
			resultString = encode(resultString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	public static void main(String[] args) {
		// e10adc3949ba59abbe56e057f20f883e
		System.out.println(encode("hj654321"));
	}
}