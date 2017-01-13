package com.alanma.doraemon.utils.appreq.payment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.xerces.impl.dv.util.Base64;

import com.alanma.doraemon.utils.md5.TestMD5;
import com.alanma.doraemon.utils.secret.RSAEncrypt;
import com.alanma.doraemon.utils.secret.zl.AES;
import com.alanma.doraemon.utils.secret.zl.Base64Utils;
import com.alanma.doraemon.utils.secret.zl.RSAUtilsZL;
import com.alanma.doraemon.utils.time.DateStyle;
import com.alanma.doraemon.utils.time.DateUtil;
import com.alibaba.fastjson.JSONObject;

public class TestHJ {
	/**
	 * 证联金融公钥
	 */
	private static String payPubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCEIVS2yZuH9HB/YBGSLVdIkHYttCduoWnAJybhp+EM6juCauJJ71WZaLY2GSq6GJmeZVi3241auI3vk6XZSlMZ2uUjO/15k5x/sEtQiY9U0ELyQFdCoIz4mPX9R5N5IBzKqAflPuQ5NCkcRUxOFESghRj5LLZLPtwdWLD9qubjuwIDAQAB";
	/**
	 * 互金平台公钥
	 */
	private static String hjPubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCPVC2I7ofaEwve5bXgOpGdsHAOInN7O8Q+Mfjjl5Go2on50bfhwOiJ8SZdbmEuyGg0i/arpbiPN4dJ7L1VcD9kPlv8eAVvPwZo0nu58rE5nCVgE0/x6yshLUWB+SrbQFrKS016+p0GMon0VyzkQxJUF8/GMR/4plyre1TyF1mCAwIDAQAB";
	/**
	 * 互金平台私钥
	 */
	private static String hjPriKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI9ULYjuh9oTC97lteA6kZ2wcA4ic3s7xD4x+OOXkajaifnRt+HA6InxJl1uYS7IaDSL9quluI83h0nsvVVwP2Q+W/x4BW8/BmjSe7nysTmcJWATT/HrKyEtRYH5KttAWspLTXr6nQYyifRXLORDElQXz8YxH/imXKt7VPIXWYIDAgMBAAECgYBBeEIUemCzzF9vwZ4Cfr5lOWL2EeaONA7AGjOGQoXWi3zbEGDeTi/qUq0joHfj1sdCuWxDm79iLAswSWNy04MgpmqcPgoT+xVrEXiDGMg/YlcZx5KpgSAvU2m9ED2xTiCIRx4zgaYgM/kNfMu+v7Q89sjAGwrxclwjdiEZ7jtkAQJBANwfhgjAApa0BucElYroFgahErr3DdLGReBo17BhG/St+1AG6uIbNCCac++rqu64BcUwIvbOtOZf7L6Q3JQhKOECQQCmsHe6m5toWBOYytcWjSPEZHR9C/xq9BvZ2eyyMDdTu2TgZwlS/GZwZxMH6/xYKIex8SBj9GPtTA3NjnKyPhNjAkEAznTSpgpRYz/e6XanO+2KGxWgF7P4RySoDV7ITioGqms0N08FpqSVM68p5jVA0T3QS65mXM5rZVe7GNGN0XF4gQJABgppHFsdQ6fFdgkLyGZvAGP/kLSJfjrAc6GhIUf8CU3GNxekz9wTvkkN+ICF0ZAOctVhLh4iLOxPp75cy7bSLwJAH0A7Fd3DQWOwYfVCbsDgJ8UdcwZTAvRGPPsBZbZupRhwkCoT0945n2uobuL2nDsa5V7Dvn4GAhzexbT3OBFvow==";

	private static String[] getRequestInfo() throws Exception {
		String[] msgInfo = new String[3];
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
		String aesKey = AES.generateKey();
		String addit = null;
		String data = null;
		String sign = null;
		String riskInfo = null;
		// 2.2.4.2. 附加数据(addit) 明文
		Map<String, String> riskInfoMap = new HashMap<String, String>();
		riskInfoMap.put("random", aesKey);
		riskInfoMap.put("timestamp", Long.toString(System.currentTimeMillis()));
		Map<String, String> additMap = new HashMap<String, String>();
		additMap.put("accessType", "0");
		additMap.put("coopInstiId", "300000000000006");
		additMap.put("merId", "");
		additMap.put("encryKey", Base64Utils.encode(RSAUtilsZL.encryptByPublicKey(aesKey.getBytes("utf-8"), hjPriKey)));
		additMap.put("encryMethod", "01");
		riskInfo = Base64.encode(AES.encrypt(JSONObject.toJSONString(riskInfoMap), aesKey));
		additMap.put("riskInfo", riskInfo);
		addit = JSONObject.toJSONString(additMap);

		// 业务数据明文组装
		Map<String, String> dataMap = new HashMap<String, String>();
		// 版本
		dataMap.put("version", "1.0");
		// 编码方式
		dataMap.put("encoding", "utf-8");
		// 交易类型
		dataMap.put("version", "17");
		// 交易子类
		dataMap.put("txnType", "00");
		// 产品类型
		dataMap.put("txnSubType ", "000207");
		// 渠道类型
		dataMap.put("channelType", "00");
		dataMap.put("memberId", "200000000001496");
		dataMap.put("tn", "200000000001496");
		data = JSONObject.toJSONString(dataMap);

		System.out.println("[addit]：" + addit);
		System.out.println("[data明文]：" + data);

		signature = RSAUtilsZL.sign((addit + data).getBytes("utf-8"), hjPriKey);
		System.out.println("[signature]：" + signature);

		Map<String, String> signMap = new HashMap<String, String>();
		signMap.put("signature", signature);
		signMap.put("signMethod", "01");
		sign = JSONObject.toJSONString(signMap);
		System.out.println("[sign]：" + sign);

		encyData = Base64Utils.encode(AES.encrypt(data, aesKey));
		System.out.println("[data密文]：" + encyData);

		msgInfo[0] = sign;
		msgInfo[1] = addit;
		msgInfo[2] = encyData;
		return msgInfo;
	}
}
