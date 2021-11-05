package com.mxw.doraemon.utils.secret.zl;

public class RSATmp {

	static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVSAUm+WRCRQN5SHpuMGmIYD4eBNqomhj18s29S1bg2ImlvA05vKpg4JrSBSld/00f2SWe1RfosI5zCbSSRP+oTccJ4Z+pUFCdng97zqKrqdjI//xRI5C7jt93bT0IM8yZ+w2cCYxLem1pySskB9oJVT3UWFDHrVMqN8TDYdao5QIDAQAB";
	static String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJVIBSb5ZEJFA3lIem4waYhgPh4E2qiaGPXyzb1LVuDYiaW8DTm8qmDgmtIFKV3/TR/ZJZ7VF+iwjnMJtJJE/6hNxwnhn6lQUJ2eD3vOoqup2Mj//FEjkLuO33dtPQgzzJn7DZwJjEt6bWnJKyQH2glVPdRYUMetUyo3xMNh1qjlAgMBAAECgYBsMkLpuKAeF/8ojIuQCa6zlW3wO/xvbyDgQhc0N8LEPK2nXeEIE+I5TRFpXyxYYI35o11M/Y3SrQTSBBrIWRIiQW3yJBEOg0BBzIfH/mGjpeeE8FdjVFVpVyjW9Y4YypMpM911zclbWAFwzim9oaOH6ghVXLN7aVpYrPuh5eQXEQJBANcuQzGWY2D1oZDMXt7j9+ncTHdW7V05UHc85Kr+F1tpK1ELIVXI5fXkUj/985e9MCnQQWx180VtpGD2/fZoE2MCQQCxmYLrmuIDMf9TCVtKtd/Ah5sVAfuQvpthAaHWHdY3t7mPvOGlLFAPbcYSGTpLEej8Fea2FbmraAuAa24aGtkXAkEAjJ2rxVzchoEd2qzjFk7tNEMFKTuCE9ifb7w4RlGTW+YHK4fVeeS1+hEBetXueGtmbPyttD4EAovazo65eWubtwJBAIEeiZd/eHg4PlgBgnTTu8Q/Etn4Igx+93pUdHO2J4tEsM021pp+mApPleSMoNv5Pnkf9ydErkNXFp2b/WVPRasCQA7Vm3xvIJx5lA5pd+gmcaa9IhNM0vgMVfAdqv92+NUKP8Db+InqvOgS3l7kz3Bjj3oVV5KBLA07O7UQm4kO1Co=";

	public static void main(String[] args) throws Exception {
		test();
		// testSign();
		// data();
		// data();
	}

	static void data() throws Exception {

		// String str =
		// "AfMztDpgNKxW6iGKW+cbmoA1/x9dcCYLck0SFuosgqNeA6zS/sFgIS+DGwn1lHmvZC3zhw8cHud+EsKAu4YqoXTzwzsevzmAf+4J5oxB41ZB6UBHGMCGQltVxizLvHDiKczWDY7b1+rdgWJu5TXAndKjkvecDLnfflwfmPe3TRiVgBqElIxmeGKEnAg8GaRafFYx8ZbArhzmPUyhRz+uVdmtme6l07SjkkjQXPfMo5mfoWDYXD72tkVJJcYe+O+CuEhpggd3D94+QWtbdqk41/O+fLZ8gb7gfkV0HHt+zBpO0xNdj29xbp0qKPCI8mjyB66itTmd/rT5LRJEcdfEwlRDexRE+TYv1vJiYHGTaJm7g36wjrOxXTUiEv/AwcrlHa1QYv0oWUsC58YexAAYU0o/5Wxp12kEYlwZrfuW47FZCyWG6Ey4i+6mMeGTAyu8UrBfoclnYHXFRapxEppEm3cWCOE2IR3e/wV009HqCwN4ypS5OLDcijvKX/8onol9Al8lPV+bVhf1gi4vIEwHAG3qiPbrg7cgyM8KhkGCrz+t1HylTdvChSiek9DnRsWQ6TO1wAEkkGN8c4IdJavSOS3w2DfNQBoIoGclSof+vazca0nbnDr/k2qwmG0MrX7X9nV2YaKGd8i89axtBAXf+CXeeK4mgix/wA+hx6ZEdigv1BPN3JICqFFaU5GeSZpTk+jvpqP73MsSC1rtLz/YRK/EK4lan1OeqTdZguRUxoSro1inLkzeuyQKf1rhzK3Yq91pw9rJsIPBNQ4j3Rwsb05u+xsiNuPeRcHZQtpBF5gNhQmLmXiWaQMFGiK9Rr/q9q/k94Gh4ZzpcWduZYEikw==";

		String str = "aLsNcZN7IjqlIiLCwh6c+RHsZHXiFHj3gVFyAF1La9v/HDgX9JFFz9jMlUa6qgISsddRoANVqf/Wym71t5CrifUN8N5LzS8fsHWOYJJrdeYmYBjw1uKZ8y00RXS2XkCAqsLgJRbX6wSJ4ZjwV87qHRlkA2v2MP9TNd5dv1wfoN8=";
		// byte[] data = str.getBytes();
		// byte[] encodedData = RSAUtilsZL.encryptByPublicKey(data, publicKey);
		// System.out.println("加密后文字：" + Base64Utils.encode(encodedData));

		byte[] decodedData = RSAUtilsZL.decryptByPrivateKey(Base64Utils.decode(str), privateKey);
		String target = new String(decodedData);
		System.out.println("解密后文字: " + target);

	}

	static void test() throws Exception {
		System.out.println("公钥加密——私钥解密");
		String source = "123456";
		System.out.println("\r加密前文字：" + source);

		byte[] data = source.getBytes();
		byte[] encodedData = RSAUtilsZL.encryptByPublicKey(data, publicKey);
		System.out.println("加密后文字：" + Base64Utils.encode(encodedData));

		byte[] decodedData = RSAUtilsZL.decryptByPrivateKey(encodedData, privateKey);
		String target = new String(decodedData);
		System.out.println("解密后文字: " + target);

	}

	static void testSign() throws Exception {
		System.out.println("私钥加密——公钥解密");
		String source = "9876543";
		System.out.println("原文字：" + source);

		byte[] data = source.getBytes();
		byte[] encodedData = RSAUtilsZL.encryptByPrivateKey(data, privateKey);
		System.out.println("加密后：" + Base64Utils.encode(encodedData));

		byte[] decodedData = RSAUtilsZL.decryptByPublicKey(encodedData, publicKey);
		String target = new String(decodedData);
		System.out.println("解密后: " + target);

		// =======
		System.out.println("公钥加密——私钥解密");
		data = source.getBytes();
		encodedData = RSAUtilsZL.encryptByPublicKey(data, publicKey); // encryptByPrivateKey(data,
																		// privateKey);
		System.out.println("加密后：" + Base64Utils.encode(encodedData));

		decodedData = RSAUtilsZL.decryptByPrivateKey(encodedData, privateKey); // decryptByPublicKey(encodedData,
																				// publicKey);
		target = new String(decodedData);
		System.out.println("解密后: " + target);

		System.out.println("私钥签名——公钥验证签名");
		String sign = RSAUtilsZL.sign(data, privateKey);
		System.out.println("签名:" + sign);
		boolean status = RSAUtilsZL.verify(data, publicKey, sign);
		System.out.println("验证结果:" + status);
	}


}
