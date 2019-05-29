package com.mxw.doraemon.utils.secret;


import com.mxw.doraemon.utils.secret.zl.RSAUtilsZL;

import java.io.UnsupportedEncodingException;

public class CheckPFX {

	private static final String url = "C:\\Users\\AlanMa\\Desktop\\hj.pfx";

	private static final String password = "751c5c9323a441a7216fde6740cff656";
	
	private static final String privateKey="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANstL8qgtCckJGyyG1vv6PvZ6P5kXoptFz9Ck3uM4UWoaU2Q0tUIGnAV346e4dID3sdIMq2Ud0l/mn/J3UzjyC8xC4wtmV1BHoekRO883B3PekXWicX7sMzUFrnsc97318ZLKqEjj95+PD4Ust6/ZG+GaHbe0YAQnxDQRP/DTT+JAgMBAAECgYEAlnGw4Vxn7IoReo7S2suV1/IezSemAUyabXDMfT0ZkE/mYDitj07tnsxU1LfYd+jyLUYwixdivg+ZxGeSwOmFiSGsGDAEcgFTf13aNIt9iAXzE/np0fk/fQ/28MkCyT5Y21aSew4SNgDSxqmDd65cHj+qAnJfmrUdRsvIgC/XyPUCQQD2CLx7e7QitClrMcat8Qjt9hu+HYJayKX5hqpUYoeLpwjN6MQX16R9DI6xsCOGwaOjn2TTxQej6pX8M7VJu9GnAkEA5A3y8CuXbuLToKe/jy9xKmiyCAAd+82aPNBrYBKmIBwVnUZQ1rhHAgS0nW0EXDXNk8jTPMqlBmEKwPNUP+urTwJBAOjGSTV3S23OEKXXmUb2lmE1iPftSWiFD+suCrb95YouRbky8VwxbPHW0KaypFoZmK3031swvdLK3o6XiXyyU18CQHS4BuyRAvJHuVRyRoJ8H4k7CFM77ByiABy79xi2srugZBeYk2sx6XXI/r/Luf2Ps+W9VQ+6D3gSVrSmVSpZ7dUCQQDGgZCUFd+HNANkNTjrzu20EV5ww/5KAe4ahbRwD7e8cQjhYs6scj5lYDHKCWdB/j+3vPAw8xe2lvXUbr9wq5jA";
	private static final String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDbLS/KoLQnJCRsshtb7+j72ej+ZF6KbRc/QpN7jOFFqGlNkNLVCBpwFd+OnuHSA97HSDKtlHdJf5p/yd1M48gvMQuMLZldQR6HpETvPNwdz3pF1onF+7DM1Ba57HPe99fGSyqhI4/efjw+FLLev2Rvhmh23tGAEJ8Q0ET/w00/iQIDAQAB";

	public static boolean checkSign(String content, String priKey, String pubKey) {
		String signInfo = null;
		boolean isPassCheck = false;
		try {
			signInfo = RSAUtilsZL.sign(content.getBytes("utf-8"), priKey);
			isPassCheck = RSAUtilsZL.verify(content.getBytes("utf-8"), pubKey, signInfo);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isPassCheck;
	}

	public static boolean checkEncode(String content, String priKey, String pubKey) {

		byte[] encodeInfo;
		byte[] decodeInfo;
		String contentDecode = null;
		try {
			encodeInfo = RSAUtilsZL.encryptByPublicKey(content.getBytes("utf-8"), pubKey);
			decodeInfo = RSAUtilsZL.decryptByPrivateKey(encodeInfo, priKey);
			contentDecode = new String(decodeInfo, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.equals(contentDecode);
	}

	public static void main(String[] args) {

//		List pks = ReadPFX.getPvkformPfx(url, password);
//		String privateKey = null;
//		String publicKey = null;
//		try {
//			privateKey = RSAHelper.getKeyString((RSAPrivateKey) pks.get(1));
//			publicKey = RSAHelper.getKeyString((RSAPublicKey) pks.get(0));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		System.out.println("公钥：" + privateKey);
		System.out.println("私钥：" + publicKey);

		System.out.println("签名校验：" + checkSign("123456", privateKey, publicKey));
		System.out.println("加密校验：" + checkEncode("654321", privateKey, publicKey));
	}

}
