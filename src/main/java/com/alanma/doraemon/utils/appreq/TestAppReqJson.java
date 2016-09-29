package com.alanma.doraemon.utils.appreq;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.alanma.doraemon.utils.file.ReadFile;
import com.alanma.doraemon.utils.md5.TestMD5;
import com.alanma.doraemon.utils.secret.RSAEncrypt;
import com.alanma.doraemon.utils.secret.zl.AES;
import com.alanma.doraemon.utils.secret.zl.Base64Utils;
import com.alanma.doraemon.utils.secret.zl.RSAUtilsZL;
import com.alibaba.fastjson.JSONObject;

public class TestAppReqJson {

	/**
	 * 对称秘钥
	 */
	private static String sesSK = "b0f3fa645d96858543c45203909bd609";
	/**
	 * 后台公钥
	 */
	private static String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC0V5iLt0eKYPVQpvDvOX1j07jj8GSsxl9eIEzFv3xOZrxIXYCwxrmtpvjlsvt5c8gx2o8kvZxdBdoG5sVSbEawz3A6XfRSEPj6do/ucJkNZcBQJ+DfruHOpE1EVidco6g5t1Wy09muMHAIs6jibJGvQI4hW2QgfG7827JRYwj+rQIDAQAB";
	/**
	 * URL
	 */
	private static String url = "http://192.168.101.247" + "/restful/idencode/sendMsgCode";

	private static String inputStrReqHead = "{\"random\":\"168456\",\"timestamp\":\"20160606:12:00:00\",\"os\":\"v1.0.6\",\"deviceID\":\"DID123987654\",\"phoneNo\":\"13999999999\",\"phoneIMEI\":\"IMEI4989163\"}";
	private static String inputStrData = "";

	public static void main(String[] args) {

		getAppResMsg();

	}

	private static String getDataInMsg(String inputStr) {

		List<String> dataKeys = new ArrayList<String>();
		JSONObject jsonObj = (JSONObject) JSONObject.parse(inputStr);
		Set<Entry<String, Object>> set = jsonObj.entrySet();
		for (Entry<String, Object> entry : set) {
			dataKeys.add(entry.getKey());
		}
		Collections.sort(dataKeys);

		return spliceElements(dataKeys, jsonObj);
	}

	private static String spliceElements(List<String> keys, JSONObject jsonObj) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (int i = 0; i < keys.size(); i++) {
			sb.append("\"");
			sb.append(keys.get(i));
			sb.append("\"");
			sb.append(":");
			if (jsonObj.get(keys.get(i)) instanceof String) {
				sb.append("\"");
			}
			sb.append(jsonObj.get(keys.get(i)));
			if (jsonObj.get(keys.get(i)) instanceof String) {
				sb.append("\"");
			}
			if (i == keys.size() - 1) {
				sb.append("}");
			} else {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	private static String getAppResMsg() {
		String signature = null;
		String signMethod = "02";
		String encryKey = null;
		String origData = null;
		String encyData = null;
		String origHead = null;
		String encyHead = null;
		String encyDataUrl = null;
		String encyHeadUrl = null;
		String signUrl = null;
		String signMethodUrl = null;
		String encryKeyUrl = null;

		// encry对称秘钥-encryKey
		RSAPublicKey publicKey;
		try {
			publicKey = RSAEncrypt.loadPublicKeyByStr(pubKey);
			// encryKey = Base64Utils.encode(RSAUtils.encryptByPublicKey(sesSK,
			// publicKey).getBytes());
			encryKey = Base64Utils.encode(RSAUtilsZL.encryptByPublicKey(sesSK.getBytes(), pubKey));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("pubKey 明文：" + sesSK);
		System.out.println("pubKey 密文：" + encryKey);
		// reqHead AES对称加密后Base64转码
		origHead = getDataInMsg(inputStrReqHead);
		System.out.println("orig head is:" + origHead);
		try {
			// encyHead = QEncodeUtil.aesEncrypt(origHead, sesSK);
			encyHead = Base64Utils.encode(AES.encrypt(origHead, sesSK));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("encypt head is:" + encyHead);

		// data AES对称加密后Base64转码
		origData = getDataInMsg(inputStrData);
		System.out.println("orig data is:" + origData);
		try {
			// encyData = QEncodeUtil.aesEncrypt(origData, sesSK);
			encyData = Base64Utils.encode(AES.encrypt(origData, sesSK));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("encypt data is:" + encyData);
		// sign签名信息-signature
		signature = TestMD5.getMessageDigest(origHead + "&" + origData + "&" + sesSK);

		// 组装请求地址
		// {"data":"LNk5LrUk8cjdUGAevc0rt8djL%2FEt44gOTec7wdCUnqiSS60B6F6YVQ5jAHPk0fvzuk6kyHFYCafQymTq%2Byr9nbazF2BQgJwua0hRQ9Xx6TU%3D","sign":{"signature":"e7264157d9e25175620da3f7fda4d2a8","signMethod":"02"},"encry":"VfHQB4bN6jLp9LUaDIj2QbKZCyckayFGfHrdr1gYu9IOex+zLTNM5HWnO7ouC5JzEv6r94dPiHw\/XAZo+vx+Iddoqe56kyOUcDheORxnw9wIGXuATcZXofac50rgGYbVy4YVSFJzwRWROTl6W5MWFmxP0qBkyi3OOttIzW8DdZ4=","reqHead":"60tBm7hEnTlu2TkbtB75yzOiigijEf8r1n%2FUIhuvgE0gUsjwPh82ViYZSUM4x4b7ZAPv84ksX6pbPgK0xEJJcCMbAe714D89PJovCqDCwysFNtk%2BcJLIsb%2F%2FKjlxDMKx80Z2qEkFwVShln6W4CUJa%2BP6WQWLN1icLsfN9SZp8T9RM72In4CACjcSW%2BXYAi%2B9e6isu78PuCQ0guMU9BgRww%3D%3D"}
		try {
			encyDataUrl = URLEncoder.encode(encyData, "UTF-8");
			encyHeadUrl = URLEncoder.encode(encyHead, "UTF-8");
			signUrl = URLEncoder.encode(signature, "UTF-8");
			signMethodUrl = URLEncoder.encode(signMethod, "UTF-8");
			encryKeyUrl = URLEncoder.encode(encryKey, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Sign sign = new Sign(signUrl, signMethodUrl);
		AppReqMsg msfInfo = new AppReqMsg(JSONObject.toJSONString(sign), encryKeyUrl, encyHeadUrl, encyDataUrl);
		String resp = JSONObject.toJSONString(msfInfo);
		System.out.println("\n" + resp);
		return resp;
	}

	// public static void main(String[] args) {
	// Map<String, String> headMap = new HashMap<String, String>();
	// headMap.put("random", "168456");
	// headMap.put("timestamp", DateUtil.DateToString(new Date(),
	// DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
	// headMap.put("os", "v1.0.6");
	// headMap.put("deviceID", "DID123987654");
	// headMap.put("phoneNo", "13999999999");
	// headMap.put("phoneIMEI", "IMEI4989163");
	//
	// String inputStr =
	// "{\"random\":\"168456\",\"timestamp\":\"20160606:12:00:00\",\"os\":\"v1.0.6\",\"deviceID\":\"DID123987654\",\"phoneNo\":\"13999999999\",\"phoneIMEI\":\"IMEI4989163\"}";
	// System.out.println(inputStr);
	// JSONObject jsonObj = (JSONObject) JSONObject.parse(inputStr);
	// Set<Entry<String, Object>> set = jsonObj.entrySet();
	// for (Entry<String, Object> entry : set) {
	// System.out.println(entry.getKey() + "=" + entry.getValue());
	// }
	// System.out.println(jsonObj.toJSONString());
	// }

}
