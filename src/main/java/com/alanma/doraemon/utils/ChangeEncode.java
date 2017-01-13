package com.alanma.doraemon.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.http.client.utils.URLEncodedUtils;

public class ChangeEncode {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String content = "测";
		System.out.println(getEncoding(content) + ":" + content);
		byte[] gb = content.getBytes("GB2312");
		for (int i = 0; i < gb.length; i++) {
			System.out.print(gb[i] + "\t");
		}
		System.out.println();
		String contentUTF8 = new String(content.getBytes("UTF-8"),"UTF-8");;
		System.out.println(getEncoding(contentUTF8) + ":" + contentUTF8);
		byte[] utf = contentUTF8.getBytes("UTF-8");
		for (int i = 0; i < utf.length; i++) {
			System.out.print(utf[i] + "\t");
		}
		System.out.println();

	}

	public static String gb2312ToUtf8(String str) {

		String urlEncode = "";

		try {

			urlEncode = URLEncoder.encode(str, "UTF-8");

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

		}

		return urlEncode;

	}

	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是GB2312
				String s = encode;
				return s; // 是的话，返回“GB2312“，以下代码同理
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是ISO-8859-1
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是UTF-8
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是GBK
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return ""; // 如果都不是，说明输入的内容不属于常见的编码格式。
	}
}
