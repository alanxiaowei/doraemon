package com.alanma.doraemon.utils.appreq;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.util.Base64Utils;

import com.alanma.doraemon.utils.secret.RSAHelper;
import com.alanma.doraemon.utils.secret.RSAUtils;
import com.alanma.doraemon.utils.secret.zl.RSAUtilsZL;

public class TestRSA {
	private static String priKey = "MIIBNgIBADANBgkqhkiG9w0BAQEFAASCASAwggEcAgEAAoGBAImsQzZ06lopMXuouWjF+T03OfrV8Q3yJOovd2TFS/TyT8XuXfz4ysrMpy7xwLTF7DWtAdYMdGh8ZvFuvmQu5mUR/nX+PSASbmqZRA3eJWOVoTXSc5HolC1ObEDGdl9ce1pdm2Njia7OmVaKZgv5mvor7MNcCVVtQQi1V+W1xpAZAgEAAoGAb9hMul79TMYOd0G5bw72zu6g4dERBp1jtAVf37QRYyu/9zMRlESiq2Lbp12rirEIlwq4ZSa1I3V9REMN1eHsNZnBTBXmWgRqQ1ShS5g6dGQoDBNJBb5Tt4phfCoaSMFcI4sdr6TCOeP3C1CMx+9Gxatk+HB8XFFkp1lgGwWWN6ECAQACAQACAQACAQACAQA=";

	public static void main(String[] args) {
		String mw = "0935d455771d36f6575d8cc4ed338f9a";
		String encry = "Uzajhi1kTjYwRMLwymWkfwX2nwRoOXQBGjnFQawt7CKrX20Aok+TlBwcrln5LzpsM9r0YQBSDqCshKX9q08vhoiylchBlyHtvOJb2WbgNLbv6IlZqaKeb7IahOSVUmayPqBoXFhdEjzSdCoekaY0jsw8kqNDcyD0ueqqq7Cf2us=";
		// RSAPrivateKey priKey =
		// ReadPFX.getPvkformPfx("C:\\Users\\AlanMa\\Desktop\\hj.pfx",
		// "hj2016");

		// System.out.println("encry base64 decode:" + new
		// String(Base64Utils.decodeFromString(encry)));
		// try {
		// System.out.println("私钥：" + RSAHelper.getKeyString(priKey));
		// } catch (Exception e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		String sesSK = null;
		try {
//			sesSK = RSAUtils.decryptByPrivateKey(new String(Base64Utils.decodeFromString(encry)),
//					(RSAPrivateKey) RSAHelper.getPrivateKey(priKey));
			sesSK=new String(RSAUtilsZL.decryptByPrivateKey(encry.getBytes(), priKey));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("对称秘钥明文：" + sesSK);

//		System.out.println(new String(Base64Utils.decodeFromString("MTIzNDU2")));

		encodeRSA();
	}

	private static void encodeRSA() {
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJrEM2dOpaKTF7qLloxfk9Nzn61fEN8iTqL3dkxUv08k/F7l38+MrKzKcu8cC0xew1rQHWDHRofGbxbr5kLuZlEf51/j0gEm5qmUQN3iVjlaE10nOR6JQtTmxAxnZfXHtaXZtjY4muzplWimYL+Zr6K+zDXAlVbUEItVfltcaQGQIDAQAB";
		String mw = null;
		try {
			mw = RSAUtils.encryptByPublicKey("c352c4766f54a067b729e370ed403263", (RSAPublicKey) RSAHelper.getPublicKey(publicKey));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("未做Base64编码：" + mw);
		System.out.println("经过Base64编码：" + Base64Utils.encodeToString(mw.getBytes()));
	}
}
