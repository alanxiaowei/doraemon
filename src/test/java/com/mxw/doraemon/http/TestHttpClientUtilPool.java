package com.mxw.doraemon.http;

import java.io.IOException;

public class TestHttpClientUtilPool {
	public static void main(String[] args) {
		// signin();
//		addApiKeys();
		getMagUser();
	}

	private static void signin() {
		String message = "{\"email\":\"wang55201008@qq.com\",\"password\":\"1234567891123456789112345678911234567891\"}";
		try {
			String resp = HttpClientUtilPool.post(message, "http://192.168.2.70:8080/user/signin", "UTF-8");
			System.out.println("=====[resp]:" + resp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void addApiKeys() {
		String message = "{\"ip\": \"192.168.2.70\",\"description\": \"test by mxw\",\"canTrade\": true,\"canWithdraw\": true}";
		try {
			String resp = HttpClientUtilPool.post(message, "http://192.168.2.70:8080/user/security/addApiKey", "UTF-8");
			System.out.println("=====[resp]:" + resp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void getMagUser() {

		String resp = HttpClientUtilPool.get("http://127.0.0.1:8008/manage/users/");
		System.out.println("=====[resp]:" + resp);

	}

}
