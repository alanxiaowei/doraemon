package com.mxw.doraemon.utils.secret;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ReadPFX {
	public ReadPFX() {
	}

	// 转换成十六进制字符串
	public static String byte2String(byte[] b) {
		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			// if (n<b.length-1) hs=hs+":";
		}
		return hs.toUpperCase();
	}

	public static byte[] stringToByte(int number) {
		int temp = number;
		byte[] b = new byte[4];
		for (int i = b.length - 1; i > -1; i--) {
			b[i] = new Integer(temp & 0xff).byteValue();// 将最高位保存在最低位
			temp = temp >> 8; // 向右移8位
		}
		return b;
	}

	public static List getPvkformPfx(String strPfx, String strPassword) {
		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream fis = new FileInputStream(strPfx);
			// If the keystore password is empty(""), then we have to set
			// to null, otherwise it won't work!!!
			char[] nPassword = null;
			if ((strPassword == null) || strPassword.trim().equals("")) {
				nPassword = null;
			} else {
				nPassword = strPassword.toCharArray();
			}
			ks.load(fis, nPassword);
			fis.close();
			// System.out.println("keystore type=" + ks.getType());
			// Now we loop all the aliases, we need the alias to get keys.
			// It seems that this value is the "Friendly name" field in the
			// detals tab <-- Certificate window <-- view <-- Certificate
			// Button <-- Content tab <-- Internet Options <-- Tools menu
			// In MS IE 6.
			Enumeration enumas = ks.aliases();
			String keyAlias = null;
			if (enumas.hasMoreElements())// we are readin just one certificate.
			{
				keyAlias = (String) enumas.nextElement();
				// System.out.println("alias=[" + keyAlias + "]");
			}
			// Now once we know the alias, we could get the keys.
			// System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));
			RSAPrivateKey prikey = (RSAPrivateKey) ks.getKey(keyAlias, nPassword);
			Certificate cert = ks.getCertificate(keyAlias);
			RSAPublicKey pubkey = (RSAPublicKey) cert.getPublicKey();

			// System.out.println("cert class = " + cert.getClass().getName());
			// System.out.println("cert = " + cert);
			// System.out.println("public key = " + pubkey);
			// System.out.println("private key = " + prikey);
			// System.out.println("public key base64 Str= " +
			// Base64.encode(pubkey.getEncoded()));
			// System.out.println("private key base64 Str= " +
			// Base64.encode(prikey.getEncoded()));

			List pk = new ArrayList();
			pk.add(pubkey);
			pk.add(prikey);
			return pk;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		List pks = getPvkformPfx("C:\\Users\\AlanMa\\Desktop\\hj.pfx", "751c5c9323a441a7216fde6740cff656");
		try {
			System.out.println("公钥：" + RSAHelper.getKeyString((RSAPublicKey) pks.get(0)));
			System.out.println("私钥：" + RSAHelper.getKeyString((RSAPrivateKey) pks.get(1)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
