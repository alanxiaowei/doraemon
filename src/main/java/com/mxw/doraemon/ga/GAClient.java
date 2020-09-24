package com.mxw.doraemon.ga;

import com.warrenstrange.googleauth.GoogleAuthenticator;

/**
 * @program: doraemon
 * @description:
 * @author: AlanMa
 * @create: 2020-03-16 16:14
 */
public class GAClient {

	static int getVerifCode(String secretKey) {
		GoogleAuthenticator gAuth = new GoogleAuthenticator();
		int code = gAuth.getTotpPassword(secretKey);
		return code;
	}

	public static void main(String[] args) {
		int code = getVerifCode("2100000445572");
		System.out.println(code);
	}
}
