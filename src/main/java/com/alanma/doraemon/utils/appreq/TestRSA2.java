package com.alanma.doraemon.utils.appreq;

import com.alanma.doraemon.utils.rsa.zl.Base64Utils;
import com.alanma.doraemon.utils.rsa.zl.RSAUtilsZL;

public class TestRSA2 {
	private static String priKey = "MIIBNgIBADANBgkqhkiG9w0BAQEFAASCASAwggEcAgEAAoGBAImsQzZ06lopMXuouWjF+T03OfrV8Q3yJOovd2TFS/TyT8XuXfz4ysrMpy7xwLTF7DWtAdYMdGh8ZvFuvmQu5mUR/nX+PSASbmqZRA3eJWOVoTXSc5HolC1ObEDGdl9ce1pdm2Njia7OmVaKZgv5mvor7MNcCVVtQQi1V+W1xpAZAgEAAoGAb9hMul79TMYOd0G5bw72zu6g4dERBp1jtAVf37QRYyu/9zMRlESiq2Lbp12rirEIlwq4ZSa1I3V9REMN1eHsNZnBTBXmWgRqQ1ShS5g6dGQoDBNJBb5Tt4phfCoaSMFcI4sdr6TCOeP3C1CMx+9Gxatk+HB8XFFkp1lgGwWWN6ECAQACAQACAQACAQACAQA=";
	private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJrEM2dOpaKTF7qLloxfk9Nzn61fEN8iTqL3dkxUv08k/F7l38+MrKzKcu8cC0xew1rQHWDHRofGbxbr5kLuZlEf51/j0gEm5qmUQN3iVjlaE10nOR6JQtTmxAxnZfXHtaXZtjY4muzplWimYL+Zr6K+zDXAlVbUEItVfltcaQGQIDAQAB";
	private static String encry = "encry=OTQ1NUVGN0FCQzcwN0E0Q0IyQURCQ0M1RjNBNzg2QkIwM0E0RTdDNDIzQUE4MzUyQ0VBREU3OEI5NEZDQjM0M0UzRTFGMDM0RjgwN0E4NEEzQkNGMjUwQkFGODIxMjFCRDlDNTczNjdFRkVENTg4MkE2NEI3MEM1RTczMDBGMzRERUI2N0FCRjI4OEY0NkJFMzM5ODFBRTVEOTY0REM5M0IxNkNDRkNCMjk3MjJBMzkwMkJGNUMzQjgyNThGRjJCRTlBMzZBMTc4MTcxQjkxRDAyOTkwNjZDM0YzM0E1MUZGMjI1M0U4MzZDRkM1QTRFNjQwRDEwMzU4OTZENzczQQ%3D%3D&data=QYduF291CCeqRLYehVOtkgy%2FEcJMrLz9rQ%2FEqOW3ONRvpn7ZeIXqYTIaVwSR%2Bm9SeUAD5xqE4c9WQ0SkPH27aw%3D%3D&reqHead=oylTQVFfI8dx6EffSgaghjDPugztrZPvEy0H1k8r12Qua14ruVISkoPAXEv0hlJBjgIDtxgcWjiSvw975R7ME22dpHBy5J9pflWs0%2FCYD25Oej9ZZ2FFvHrrixkSXdp1JN37aWT%2BUiqDIRU6R2WQtQKzKR4cPEH0M1fFIgKSIlM%3D&sign=15e247ef0d4ea574004058514526993c02";

	public static void main(String[] args) {
		decodeRSA();
		encodeRSA();
	}

	private static void encodeRSA() {

		String mw = null;
		try {
			mw = new String(RSAUtilsZL.encryptByPublicKey("c352c4766f54a067b729e370ed403263".getBytes(), publicKey));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("未做Base64编码：" + mw);
		try {
			System.out.println("经过Base64编码：" + Base64Utils.encode(mw.getBytes()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void decodeRSA() {
		String mw = "0935d455771d36f6575d8cc4ed338f9a";
		String sesSK = null;
		try {
			sesSK = new String(RSAUtilsZL.decryptByPrivateKey(Base64Utils.decode(encry), priKey));
		} catch (Exception e) {

			e.printStackTrace();
		}

		System.out.println("对称秘钥明文：" + sesSK);
	}
}
