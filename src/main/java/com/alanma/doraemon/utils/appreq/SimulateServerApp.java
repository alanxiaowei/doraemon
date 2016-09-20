package com.alanma.doraemon.utils.appreq;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.alanma.doraemon.utils.md5.TestMD5;
import com.alanma.doraemon.utils.rsa.zl.AES;
import com.alanma.doraemon.utils.rsa.zl.Base64Utils;
import com.alanma.doraemon.utils.rsa.zl.RSAUtilsZL;
import com.alibaba.fastjson.JSONObject;

public class SimulateServerApp {
	private static String priKey = "MIIBNgIBADANBgkqhkiG9w0BAQEFAASCASAwggEcAgEAAoGBAImsQzZ06lopMXuouWjF+T03OfrV8Q3yJOovd2TFS/TyT8XuXfz4ysrMpy7xwLTF7DWtAdYMdGh8ZvFuvmQu5mUR/nX+PSASbmqZRA3eJWOVoTXSc5HolC1ObEDGdl9ce1pdm2Njia7OmVaKZgv5mvor7MNcCVVtQQi1V+W1xpAZAgEAAoGAb9hMul79TMYOd0G5bw72zu6g4dERBp1jtAVf37QRYyu/9zMRlESiq2Lbp12rirEIlwq4ZSa1I3V9REMN1eHsNZnBTBXmWgRqQ1ShS5g6dGQoDBNJBb5Tt4phfCoaSMFcI4sdr6TCOeP3C1CMx+9Gxatk+HB8XFFkp1lgGwWWN6ECAQACAQACAQACAQACAQA=";
	// private static String request =
	// "data=i3X%2BymAmo%2FzkOJhqFcN2dCCt59%2Fx9o9N4xa4oQLAyOT6SkpErFmaY58F7WTaU97rIFPbQwjp5QQ8AQR5JIPbLku4%2BkFsySmDDjtxShVYrYY%3D&reqHead=6gDh%2BjYWyx06te01ttDrKytf7zWORbGjW91N8RfP8425u14TkzhBjs7D3kAiRbpHpRD2kmin%2FLT%2BmuSMvxfN4iAQiB0syf620XqNGfB0Br6Ms0dD%2FFVWM1mZKQ9xfOVf%2FOwaPdhXZzaJiQgsmQVE%2B8HtgOCfe%2Bp9LzxCAkE%2FPcIw%2BsywCLGwlubSIPckbcRB99w13nD%2BdK4hfBpbOJ1E8Q%3D%3D&sign=15741cd63de02ba56e729b74d04a250402&encry=bCe7n4yA2nOqbam4VVrR%2BPh%2Fpifhg490oez67acEw2pq3kdfDJuU00sTbeC2ouEOHn8GfHsb8CuHyX8j2AFQ3YAj1SM%2FDrDU018wY3D33vD0vQH%2F88vSG7jOlkTK6CBlFN8xXGudmtUMqULh6cMpbQgowFJ40nvag6k%2FcjiB4SQ%3D";
	/**
	 * 本地
	 */
	private static String request = "{\"data\":\"5qU%2Bf0JdgA%2FSUJU0s5iJNSwXv9M5TVyYVW0DWL55G43frKtxO%2BcbzKvuHFeh3Z%2Fa\",\"encryKey\":\"M5IOBBMhksfeOv94Z3A4ataf9sgtSLg5Jkv0t1gL2jrSk0aquSA9u0R2vGc0VipYrN%2B1kJygK6V%2BnO0gvEl4u0jeqUO5cX%2FpAdilm85zu2VIDoaglkX70C0j9MopV72ZGt9xWPIJ4Z0kaVrrQhJ4Rsm9HnrFAwt4PO0mbHf867Q%3D\",\"reqHead\":\"q4dz3%2FLQSL207xFMXSe3A%2BBgkkvBjFV3agi7lwHjYGVetKAPqVlWAxtPoCMbMehdjVeF0uoaHJt1ERLHOAT2vq8uOa%2Bf4ckG0gbrqVAwHbTZK52lXBMoZU0uCmOj1LMUHlIcQcWzLs7xypN2aCYSx%2Fk3e8OgLceGHuO5E1zGp2BTAZGQdo0%2BX1NDYJuFxfFc\",\"sign\":{\"signMethod\":\"02\",\"signature\":\"09ad9aa86645005e43a134f45d7898f6\"}}";

	private static String[] decodeReqInfo(String reqInfo) {
		// 0-reqHead;1-data
		String[] reqJson = new String[2];
		String data = null;
		String reqHead = null;
		String sign = null;
		String signature = null;
		String newSignature = null;
		String signMethod = null;
		String encry = null;
		String sesSK = null;
		String[] elements = new String[4];
		String[] temp = reqInfo.split("&");

		MsgInfo msgInfo = JSONObject.parseObject(reqInfo, MsgInfo.class);

		// 顺序0-data,1-reqHead,2-sign,3-encry
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].indexOf("data=") != -1) {
				elements[0] = temp[i];
			}
			if (temp[i].indexOf("reqHead=") != -1) {
				elements[1] = temp[i];
			}
			if (temp[i].indexOf("sign=") != -1) {
				elements[2] = temp[i];
			}
			if (temp[i].indexOf("encry=") != -1) {
				elements[3] = temp[i];
			}
		}

		try {
			data = URLDecoder.decode(msgInfo.getData(), "UTF-8");
			reqHead = URLDecoder.decode(msgInfo.getReqHead(), "UTF-8");
			signature = URLDecoder.decode(msgInfo.getSign().getSignature(), "UTF-8");
			signMethod = URLDecoder.decode(msgInfo.getSign().getSignMethod(), "UTF-8");
			encry = URLDecoder.decode(msgInfo.getEncryKey(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("data:" + data + "\n" + "reqHead:" + reqHead + "\n" + "signature:" + signature + "\n"
				+ "signMethod:" + signMethod + "\n" + "encry:" + encry);

		// 1.对encry进行Base64解码后，进行RSA解密，得到对称秘钥
		// RSAPrivateKey priKey =
		// ReadPFX.getPvkformPfx("C:\\Users\\AlanMa\\Desktop\\hj.pfx",
		// "hj2016");
		// System.out.println("encry base64 decode:" + Base64.decode(encry));
		try {
			// sesSK = RSAUtils.decryptByPrivateKey(new
			// String(Base64.decode(encry)), priKey);
			sesSK = new String(RSAUtilsZL.decryptByPrivateKey(Base64Utils.decode(encry), priKey));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("对称秘钥明文：" + sesSK);

		// 2.对reqHead、data先Base64解码，然后使用对称秘钥分别解密
		try {
			// reqHead = QEncodeUtil.aesDecrypt(reqHead, sesSK);
			// data = QEncodeUtil.aesDecrypt(data, sesSK);
			reqHead = new String(AES.decrypt(Base64Utils.decode(reqHead), sesSK));
			data = new String(AES.decrypt(Base64Utils.decode(data), sesSK));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		System.out.println("reqHead明文：" + reqHead);
		System.out.println("data明文：" + data);

		// MD5签名，将签名值与signature对比
		newSignature = TestMD5.getMessageDigest(reqHead + "&" + data + "&" + sesSK);
		if (!newSignature.equals(signature)) {
			System.err.println("签名不符");
			System.err.println("服务器签名：" + newSignature);
		}else{
			System.out.println("【签名校验通过！！！】");
		}

		// 解析reqHead和data
		reqJson[0] = reqHead;
		reqJson[1] = data;

		System.out.println("Jason reqHead:" + reqJson[0]);
		System.out.println("Jason data:" + reqJson[1]);

		return reqJson;

	}

	public static void main(String[] args) {
		decodeReqInfo(request);
		// String str = "{\"phoneNum\":\"13999999999\",\"certBusCase\":\"01\"}";
		// str = str.replace("}", ",\"reqChl\":\"01\"}");
		// System.out.println(str);
		// JSONObject jObj = JSONObject.parseObject(str);
		// System.out.println(jObj.toJSONString());
	}
}
