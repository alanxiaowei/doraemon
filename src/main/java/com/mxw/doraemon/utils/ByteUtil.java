package com.mxw.doraemon.utils;

import java.util.regex.Pattern;

public class ByteUtil {

	/**
	 * Convert bytes to hex string (all lower-case).
	 * 
	 * @param b
	 *            Input bytes.
	 * @return Hex string.
	 */
	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length << 2);
		for (byte x : b) {
			int hi = (x & 0xf0) >> 4;
			int lo = x & 0x0f;
			sb.append(HEX_CHARS[hi]);
			sb.append(HEX_CHARS[lo]);
		}
		return sb.toString().trim();
	}

	/**
	 * Convert byte to hex string (all lower-case).
	 * 
	 * @param b
	 *            Input bytes.
	 * @return Hex string.
	 */
	public static String toHex(byte b) {
		int hi = (b & 0xf0) >> 4;
		int lo = b & 0x0f;
		char[] cs = { HEX_CHARS[hi], HEX_CHARS[lo] };
		return new String(cs);
	}

	public static byte fromHex(String s) {
		if (s.length() != 2) {
			throw new IllegalArgumentException("Invalid length of string.");
		}
		char c1 = s.charAt(0);
		char c2 = s.charAt(1);
		int n1 = HEX_STRING.indexOf(c1);
		int n2 = HEX_STRING.indexOf(c2);
		if (n1 == (-1)) {
			throw new IllegalArgumentException("Invalid char in string: " + c1);
		}
		if (n2 == (-1)) {
			throw new IllegalArgumentException("Invalid char in string: " + c2);
		}
		int n = (n1 << 4) + n2;
		return (byte) n;
	}

	public static byte[] fromHexString(String s) {
		if (s.length() % 2 == 1) {
			throw new IllegalArgumentException("Invalid length of string.");
		}
		byte[] data = new byte[s.length() / 2];
		for (int i = 0; i < data.length; i++) {
			char c1 = s.charAt(i * 2);
			char c2 = s.charAt(i * 2 + 1);
			int n1 = HEX_STRING.indexOf(c1);
			int n2 = HEX_STRING.indexOf(c2);
			if (n1 == (-1)) {
				throw new IllegalArgumentException("Invalid char in string: " + c1);
			}
			if (n2 == (-1)) {
				throw new IllegalArgumentException("Invalid char in string: " + c2);
			}
			int n = (n1 << 4) + n2;
			data[i] = (byte) n;
		}
		return data;
	}

	static final String HEX_STRING = "0123456789abcdef";
	static final char[] HEX_CHARS = HEX_STRING.toCharArray();

	static final Pattern PATTERN_SHA1 = Pattern.compile("^[a-f0-9]{40}$");
	static final Pattern PATTERN_SHA256 = Pattern.compile("^[a-f0-9]{64}$");

	public static boolean isSha1(String s) {
		return PATTERN_SHA1.matcher(s).matches();
	}

	public static boolean isSha256(String s) {
		return PATTERN_SHA256.matcher(s).matches();
	}

}
