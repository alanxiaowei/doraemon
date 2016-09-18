package com.alanma.doraemon.utils.appreq;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alanma.doraemon.utils.md5.QEncodeUtil;
import com.alanma.doraemon.utils.md5.TestMD5;
import com.alanma.doraemon.utils.rsa.Base64;
import com.alanma.doraemon.utils.rsa.RSAEncrypt;
import com.alanma.doraemon.utils.rsa.RSAUtils;
import com.alanma.doraemon.utils.time.DateStyle;
import com.alanma.doraemon.utils.time.DateUtil;

public class TestAppReq {

	/**
	 * 对称秘钥
	 */
	private static String sesSK = "123456";
	/**
	 * 后台公钥
	 */
	private static String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC0V5iLt0eKYPVQpvDvOX1j07jj8GSsxl9eIEzFv3xOZrxIXYCwxrmtpvjlsvt5c8gx2o8kvZxdBdoG5sVSbEawz3A6XfRSEPj6do/ucJkNZcBQJ+DfruHOpE1EVidco6g5t1Wy09muMHAIs6jibJGvQI4hW2QgfG7827JRYwj+rQIDAQAB";
	/**
	 * URL
	 */
	private static String url = "http://192.168.101.247" + "/restful/idencode/sendMsgCode";

	public static void main(String[] args) {
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
		String encryKeyUrl = null;

		// encry对称秘钥-encryKey
		RSAPublicKey publicKey;
		try {
			publicKey = RSAEncrypt.loadPublicKeyByStr(pubKey);
			encryKey = Base64.encode(RSAUtils.encryptByPublicKey(sesSK, publicKey).getBytes());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("pubKey 明文：" + sesSK);
		System.out.println("pubKey 密文：" + encryKey);
		// reqHead AES对称加密后Base64转码
		Map<String, String> headMap = new HashMap<String, String>();
		headMap.put("random", "168456");
		headMap.put("timestamp", DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
		headMap.put("os", "v1.0.6");
		headMap.put("deviceID", "DID123987654");
		headMap.put("phoneNo", "13999999999");
		headMap.put("phoneIMEI", "IMEI4989163");
		origHead = getDataInMsg(headMap);
		System.out.println("orig head is:" + origHead);
		try {
			encyHead = QEncodeUtil.aesEncrypt(origHead, sesSK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("encypt head is:" + encyHead);

		// data AES对称加密后Base64转码
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("phoneNum", "13999999999");
		dataMap.put("certBusCase", "01");
		origData = getDataInMsg(dataMap);
		System.out.println("orig data is:" + origData);
		try {
			encyData = QEncodeUtil.aesEncrypt(origData, sesSK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("encypt data is:" + encyData);
		// sign签名信息-signature
		signature = TestMD5.getMessageDigest(origHead + origData + sesSK);

		// 组装请求地址
		// http//xxxxxx?data=URL编码&reqHead=URL编码&sign=URL编码&encry=URL编码
		try {
			encyDataUrl = URLEncoder.encode(encyData, "UTF-8");
			encyHeadUrl = URLEncoder.encode(encyHead, "UTF-8");
			signUrl = URLEncoder.encode(signature + "&" + signMethod, "UTF-8");
			encryKeyUrl = URLEncoder.encode(encryKey, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String reqStr = url + "?" + "data=" + encyDataUrl + "&reqHead=" + encyHeadUrl + "&sign=" + signUrl + "&encry="
				+ encryKeyUrl;

		System.out.println("\n" + reqStr);
	}

	private static String getDataInMsg(Map<String, String> dataMap) {

		List<String> dataKeys = new ArrayList<String>();
		for (Entry<String, String> entry : dataMap.entrySet()) {
			dataKeys.add(entry.getKey());
		}
		Collections.sort(dataKeys);

		return spliceElements(dataKeys, dataMap);
	}

	private static String spliceElements(List<String> keys, Map<String, String> dataMap) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < keys.size(); i++) {
			sb.append(keys.get(i));
			sb.append("=");
			sb.append(dataMap.get(keys.get(i)));
			sb.append("&");
		}
		return sb.toString();
	}

}
