package com.mxw.doraemon.ga;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

/**
 * @program: doraemon
 * @description:
 * @author: AlanMa
 * @create: 2020-03-16 16:09
 */
public class GAServer {


	static boolean checkCode(String secretKey,int code) {
		GoogleAuthenticator gAuth = new GoogleAuthenticator();
		boolean isCodeValid = gAuth.authorize(secretKey, code);
		return isCodeValid;
	}

	static String getSecretKey() {
		GoogleAuthenticator gAuth = new GoogleAuthenticator();
		final GoogleAuthenticatorKey key = gAuth.createCredentials();
		return key.getKey();
	}

	public static void main(String[] args) {
		String secretKey=getSecretKey();
		System.out.println(secretKey);

		// boolean isPass=checkCode("HAKSO5QDBP6DB6AU", 993273);
		// System.out.println(isPass);
	}
}
