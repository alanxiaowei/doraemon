package com.alanma.doraemon.utils.rsa.zl;

public class RSATmp {

	static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJrEM2dOpaKTF7qLloxfk9Nzn61fEN8iTqL3dkxUv08k/F7l38+MrKzKcu8cC0xew1rQHWDHRofGbxbr5kLuZlEf51/j0gEm5qmUQN3iVjlaE10nOR6JQtTmxAxnZfXHtaXZtjY4muzplWimYL+Zr6K+zDXAlVbUEItVfltcaQGQIDAQAB";
	static String privateKey = "MIIBNgIBADANBgkqhkiG9w0BAQEFAASCASAwggEcAgEAAoGBAImsQzZ06lopMXuouWjF+T03OfrV8Q3yJOovd2TFS/TyT8XuXfz4ysrMpy7xwLTF7DWtAdYMdGh8ZvFuvmQu5mUR/nX+PSASbmqZRA3eJWOVoTXSc5HolC1ObEDGdl9ce1pdm2Njia7OmVaKZgv5mvor7MNcCVVtQQi1V+W1xpAZAgEAAoGAb9hMul79TMYOd0G5bw72zu6g4dERBp1jtAVf37QRYyu/9zMRlESiq2Lbp12rirEIlwq4ZSa1I3V9REMN1eHsNZnBTBXmWgRqQ1ShS5g6dGQoDBNJBb5Tt4phfCoaSMFcI4sdr6TCOeP3C1CMx+9Gxatk+HB8XFFkp1lgGwWWN6ECAQACAQACAQACAQACAQA=";


	public static void main(String[] args) throws Exception {
		//test();

		// testSign();
		//data();
		data();
	}

	static void data() throws Exception {

		//String str = "AfMztDpgNKxW6iGKW+cbmoA1/x9dcCYLck0SFuosgqNeA6zS/sFgIS+DGwn1lHmvZC3zhw8cHud+EsKAu4YqoXTzwzsevzmAf+4J5oxB41ZB6UBHGMCGQltVxizLvHDiKczWDY7b1+rdgWJu5TXAndKjkvecDLnfflwfmPe3TRiVgBqElIxmeGKEnAg8GaRafFYx8ZbArhzmPUyhRz+uVdmtme6l07SjkkjQXPfMo5mfoWDYXD72tkVJJcYe+O+CuEhpggd3D94+QWtbdqk41/O+fLZ8gb7gfkV0HHt+zBpO0xNdj29xbp0qKPCI8mjyB66itTmd/rT5LRJEcdfEwlRDexRE+TYv1vJiYHGTaJm7g36wjrOxXTUiEv/AwcrlHa1QYv0oWUsC58YexAAYU0o/5Wxp12kEYlwZrfuW47FZCyWG6Ey4i+6mMeGTAyu8UrBfoclnYHXFRapxEppEm3cWCOE2IR3e/wV009HqCwN4ypS5OLDcijvKX/8onol9Al8lPV+bVhf1gi4vIEwHAG3qiPbrg7cgyM8KhkGCrz+t1HylTdvChSiek9DnRsWQ6TO1wAEkkGN8c4IdJavSOS3w2DfNQBoIoGclSof+vazca0nbnDr/k2qwmG0MrX7X9nV2YaKGd8i89axtBAXf+CXeeK4mgix/wA+hx6ZEdigv1BPN3JICqFFaU5GeSZpTk+jvpqP73MsSC1rtLz/YRK/EK4lan1OeqTdZguRUxoSro1inLkzeuyQKf1rhzK3Yq91pw9rJsIPBNQ4j3Rwsb05u+xsiNuPeRcHZQtpBF5gNhQmLmXiWaQMFGiK9Rr/q9q/k94Gh4ZzpcWduZYEikw==";

		String str = "Uzajhi1kTjYwRMLwymWkfwX2nwRoOXQBGjnFQawt7CKrX20Aok+TlBwcrln5LzpsM9r0YQBSDqCshKX9q08vhoiylchBlyHtvOJb2WbgNLbv6IlZqaKeb7IahOSVUmayPqBoXFhdEjzSdCoekaY0jsw8kqNDcyD0ueqqq7Cf2us=";
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
