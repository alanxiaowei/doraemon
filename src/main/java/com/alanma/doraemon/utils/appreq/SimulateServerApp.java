package com.alanma.doraemon.utils.appreq;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.alanma.doraemon.utils.md5.TestMD5;
import com.alanma.doraemon.utils.secret.zl.AES;
import com.alanma.doraemon.utils.secret.zl.Base64Utils;
import com.alanma.doraemon.utils.secret.zl.RSAUtilsZL;
import com.alibaba.fastjson.JSONObject;

public class SimulateServerApp {
	private static String priKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJrEM2dOpaKTF7qLloxfk9Nzn61fEN8iTqL3dkxUv08k/F7l38+MrKzKcu8cC0xew1rQHWDHRofGbxbr5kLuZlEf51/j0gEm5qmUQN3iVjlaE10nOR6JQtTmxAxnZfXHtaXZtjY4muzplWimYL+Zr6K+zDXAlVbUEItVfltcaQGQIDAQAB";
	// private static String request =
	// "data=i3X%2BymAmo%2FzkOJhqFcN2dCCt59%2Fx9o9N4xa4oQLAyOT6SkpErFmaY58F7WTaU97rIFPbQwjp5QQ8AQR5JIPbLku4%2BkFsySmDDjtxShVYrYY%3D&reqHead=6gDh%2BjYWyx06te01ttDrKytf7zWORbGjW91N8RfP8425u14TkzhBjs7D3kAiRbpHpRD2kmin%2FLT%2BmuSMvxfN4iAQiB0syf620XqNGfB0Br6Ms0dD%2FFVWM1mZKQ9xfOVf%2FOwaPdhXZzaJiQgsmQVE%2B8HtgOCfe%2Bp9LzxCAkE%2FPcIw%2BsywCLGwlubSIPckbcRB99w13nD%2BdK4hfBpbOJ1E8Q%3D%3D&sign=15741cd63de02ba56e729b74d04a250402&encry=bCe7n4yA2nOqbam4VVrR%2BPh%2Fpifhg490oez67acEw2pq3kdfDJuU00sTbeC2ouEOHn8GfHsb8CuHyX8j2AFQ3YAj1SM%2FDrDU018wY3D33vD0vQH%2F88vSG7jOlkTK6CBlFN8xXGudmtUMqULh6cMpbQgowFJ40nvag6k%2FcjiB4SQ%3D";
	/**
	 * 本地
	 */
	private static String request = "{\"data\":\"Ldkc\\/bihtJsznCC+msNkqZVAqZSzo0Qi3tD7Yga\\/5GebCliSWMgOW6gS8+aAMmNB8neMX2Ef0dMPw9bUQEZhr2QfLnIDByrmYrahitirIh8=\",\"sign\":{\"signature\":\"65f66d488183b6efd1c716c56daf8a18\",\"signMethod\":\"02\"},\"encry\":\"JUsm\\/ru1SDaYLcqZ3PDGJ8\\/yp0Tq5dF7pVaRI2t\\/B5tLZ9SbZEVtmX0P7BHRrLqWF6uAGkuIyXnPjVdT18hEuGw7\\/XiJnB\\/dDwbpG1G+NxvTTs5cpDI35wlYoEPgDuMSLlzo\\/DOhgyjHWRobv4wTcccEo84LZuH0\\/4DUetLMzcc=\",\"reqHead\":\"E4KCmBNZhAdKsRl8svjLeMpflxBmgaQgDHSj6N0rOWwzK+Saevt\\/8UA7exYf0eOtw6pcgdcc9sUYX0vGD2pjvBKOByQ4OPgTkMjLDX+M6Ovp9HIzs0MMUudI9PCgv82A0PCiklwAjkoU6ljX\\/u1TD2JSEycrkI9muqTd43iVaSILO5gYKCWGZl3yQLaD9US+itfMxo5dEwpLB6K0WRAgtA==\"}";

	private static String[] decodeReqInfo(String reqInfo) {
		// 0-reqHead;1-data
		String[] reqJson = new String[2];
		String data = null;
		String reqHead = null;
		String signature = null;
		String newSignature = null;
		String signMethod = null;
		String encry = null;
		String sesSK = null;

		AppReqMsg msgInfo = JSONObject.parseObject(reqInfo, AppReqMsg.class);

		try {
			data = URLDecoder.decode(msgInfo.getData(), "UTF-8");
			reqHead = URLDecoder.decode(msgInfo.getReqHead(), "UTF-8");
			signature = URLDecoder.decode(msgInfo.getSign().getSignature(), "UTF-8");
			signMethod = URLDecoder.decode(msgInfo.getSign().getSignMethod(), "UTF-8");
			encry = URLDecoder.decode(msgInfo.getEncry(), "UTF-8");
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
