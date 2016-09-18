package com.alanma.doraemon.utils.appreq;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

import com.alanma.doraemon.utils.md5.QEncodeUtil;
import com.alanma.doraemon.utils.md5.TestMD5;
import com.alanma.doraemon.utils.rsa.Base64;
import com.alanma.doraemon.utils.rsa.RSAUtils;
import com.alanma.doraemon.utils.rsa.ReadPFX;
import com.alibaba.fastjson.JSONObject;

public class SimulateServer {

	private static String request = "http://192.168.101.247/restful/idencode/sendMsgCode?data=dnQ3iH6AnDdKOgo20IpJPBBkMYCOz%2BQGb0rId7mhhyW4Lhpf6NjqatZ7R8k50bCJ&reqHead=oylTQVFfI8dx6EffSgaghjDPugztrZPvEy0H1k8r12Qua14ruVISkoPAXEv0hlJBjgIDtxgcWjiS%0D%0Avw975R7ME22dpHBy5J9pflWs0%2FCYD25Oej9ZZ2FFvHrrixkSXdp1ZRCeYhEtiHSJRuRUDzHgonuk%0D%0ABl%2FGocub%2B2PL9%2BAeKMU%3D&sign=aefc08e90d8e50f5c442a2557c501ce7%2602&encry=OUI2OTJBOUY0RkFFQTFEMEYyQzE0OEVBNjZENEMwNDBEODQ1M0FCQjE3QjIxNTRBNzc4ODcwRUZCRDlDMTg0QUE0M0E3MTFFRDU4NDA0ODgzNUYyQjM5M0YxODFENUNEQzIzNkNBMTEyODkyODgyQzk1RjM3OUI0MDU0NzA2NjFBODk0RTIyREUwQzI0NTRBNTJCRTEzNEJGMUI0RjdENTdGQUFEMTc5MTEyNzgxOEQ1RDQwMTY3Q0Q4QjA0QjIwQTVCRUIzMDI2MEQ5ODYxN0QwRDA2Q0YzMDNCMDIzQTZFNDEwRjQ1NUIxOTA2MjdFNjhFNTAxMjM3MEU3QTA4Nw%3D%3D";

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
		String[] elements = reqInfo.split("&");

		try {
			data = URLDecoder.decode(elements[0].substring(elements[0].indexOf("=") + 1, elements[0].length()),
					"UTF-8");
			reqHead = URLDecoder.decode(elements[1].substring(elements[1].indexOf("=") + 1, elements[1].length()),
					"UTF-8");
			sign = URLDecoder.decode(elements[2].substring(elements[2].indexOf("=") + 1, elements[2].length()),
					"UTF-8");
			signature = sign.substring(0, sign.lastIndexOf("&"));
			signMethod = sign.substring(sign.lastIndexOf("&") + 1, sign.length());
			encry = URLDecoder.decode(elements[3].substring(elements[3].indexOf("=") + 1, elements[3].length()),
					"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("data:" + data + "\n" + "reqHead:" + reqHead + "\n" + "signature:" + signature + "\n"
				+ "signMethod:" + signMethod + "\n" + "encry:" + encry);

		// 1.对encry进行Base64解码后，进行RSA解密，得到对称秘钥
		RSAPrivateKey priKey = ReadPFX.getPvkformPfx("C:\\Users\\AlanMa\\Desktop\\hj.pfx", "hj2016");
		System.out.println("encry base64 decode:" + Base64.decode(encry));
		try {
			sesSK = RSAUtils.decryptByPrivateKey(new String(Base64.decode(encry)), priKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("对称秘钥明文：" + sesSK);

		// 2.对reqHead、data先Base64解码，然后使用对称秘钥分别解密
		try {
			reqHead = QEncodeUtil.aesDecrypt(reqHead, sesSK);
			data = QEncodeUtil.aesDecrypt(data, sesSK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("reqHead明文：" + reqHead);
		System.out.println("data明文：" + data);

		// MD5签名，将签名值与signature对比
		newSignature = TestMD5.getMessageDigest(reqHead + data + sesSK);
		if (!newSignature.equals(signature)) {
			System.err.println("签名不符");
			System.err.println("服务器签名：" + newSignature);
		}

		// 解析reqHead和data
		reqJson[0] = pareUrlParams(reqHead);
		reqJson[1] = pareUrlParams(data);

		System.out.println("Jason reqHead:" + reqJson[0]);
		System.out.println("Jason data:" + reqJson[1]);

		return reqJson;

	}

	private static String pareUrlParams(String params) {
		Map paramMap = new HashMap();
		String[] paramStr = params.split("&");
		for (int i = 0; i < paramStr.length; i++) {
			paramMap.put(paramStr[i].split("=")[0], paramStr[i].split("=")[1]);
		}
		return JSONObject.toJSONString(paramMap);
	}

	public static void main(String[] args) {
		decodeReqInfo(request);
	}
}
