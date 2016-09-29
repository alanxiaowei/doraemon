package com.alanma.doraemon.utils.appreq;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.alanma.doraemon.utils.file.ReadFile;
import com.alanma.doraemon.utils.md5.EncryptUtil;
import com.alanma.doraemon.utils.secret.zl.AES;
import com.alanma.doraemon.utils.secret.zl.Base64Utils;
import com.alanma.doraemon.utils.secret.zl.RSAUtilsZL;
import com.alibaba.fastjson.JSONObject;

public class SimulateServerApp {
	private static String priKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALRXmIu3R4pg9VCm8O85fWPTuOPwZKzGX14gTMW/fE5mvEhdgLDGua2m+OWy+3lzyDHajyS9nF0F2gbmxVJsRrDPcDpd9FIQ+Pp2j+5wmQ1lwFAn4N+u4c6kTURWJ1yjqDm3VbLT2a4wcAizqOJska9AjiFbZCB8bvzbslFjCP6tAgMBAAECgYEAjkKzUTOpOZ22poKqeOvO4Bq6NQUDRc/Olz+/kBa3hllAmY044TpCapvWp8wC2BHfkIQqev8gA7mD/gG5EoXbVHevlko7LotpBARodhPmlo+T6k8rbuTOeqzklDaN7R4ORXku1ufPzHzQhOgQ2cP/+LAWY3biS32jRokWrJwp1nkCQQDXq+zvaSlILTGRpxdB+dvs7/D/4V1Rb9wWDZ1zMlkn5ZiSvgWEYaFcG7sj2zu/FvHzYiI3YnuqXBSYylWC1YxDAkEA1hB4wuLIfO+jjN+ZGB4OlyL6kbYNqztNNQKmU4qUmh44kBhlAqmYwV5i5C8N9XXsQSS6xPE4wIfZVa/9ckISTwJAOZspeTUsmqO/prjrxGqZrKrWQ3KNu2/WaGmQkbF0EO0JmMB6tsZhFDov/T7UsZDOZYTjwMlkYLehpMH+nyitAwJBALEM7KTcJ9DV7+LraVd+PLkasD5mYkXXBZOrvl/oheI2zre3xYv/NB3zcD2lAZmc0CxNMcEEdtkfQ03RAwJGzZcCQGWc0Ie4NwxU/jiAXTVdPS4/merWQliKlz8kfMdVN7PoO+1s7WVW799BW/UOxOwGFWh2odtOA2gxkePh8w0NyUA=";
	// private static String request =
	// "data=i3X%2BymAmo%2FzkOJhqFcN2dCCt59%2Fx9o9N4xa4oQLAyOT6SkpErFmaY58F7WTaU97rIFPbQwjp5QQ8AQR5JIPbLku4%2BkFsySmDDjtxShVYrYY%3D&reqHead=6gDh%2BjYWyx06te01ttDrKytf7zWORbGjW91N8RfP8425u14TkzhBjs7D3kAiRbpHpRD2kmin%2FLT%2BmuSMvxfN4iAQiB0syf620XqNGfB0Br6Ms0dD%2FFVWM1mZKQ9xfOVf%2FOwaPdhXZzaJiQgsmQVE%2B8HtgOCfe%2Bp9LzxCAkE%2FPcIw%2BsywCLGwlubSIPckbcRB99w13nD%2BdK4hfBpbOJ1E8Q%3D%3D&sign=15741cd63de02ba56e729b74d04a250402&encry=bCe7n4yA2nOqbam4VVrR%2BPh%2Fpifhg490oez67acEw2pq3kdfDJuU00sTbeC2ouEOHn8GfHsb8CuHyX8j2AFQ3YAj1SM%2FDrDU018wY3D33vD0vQH%2F88vSG7jOlkTK6CBlFN8xXGudmtUMqULh6cMpbQgowFJ40nvag6k%2FcjiB4SQ%3D";
	/**
	 * 本地
	 */
	private static String request = null;

	private static String[] decodeReqInfo(String reqInfo) throws Exception {
		// 0-reqHead;1-data;3-sesSK
		String[] reqJson = new String[3];
		String data = null;
		String reqHead = null;
		String signature = null;
		String newSignature = null;
		String signMethod = null;
		String encry = null;
		String sesSK = null;
		String sign = null;

		AppReqMsg msgInfo = JSONObject.parseObject(reqInfo, AppReqMsg.class);

		data = URLDecoder.decode(msgInfo.getData(), "UTF-8");
		reqHead = URLDecoder.decode(msgInfo.getReqHead(), "UTF-8");
		sign = msgInfo.getSign();
		JSONObject jObj = (JSONObject) JSONObject.parse(sign);
		signature = URLDecoder.decode(jObj.getString("signature"), "UTF-8");
		signMethod = URLDecoder.decode(jObj.getString("signMethod"), "UTF-8");
		encry = URLDecoder.decode(msgInfo.getEncry(), "UTF-8");
		System.out.println("data:" + data + "\n" + "reqHead:" + reqHead + "\n" + "signature:" + signature + "\n"
				+ "signMethod:" + signMethod + "\n" + "encry:" + encry);
		// 1.对encry进行Base64解码后，进行RSA解密，得到对称秘钥
		sesSK = new String(RSAUtilsZL.decryptByPrivateKey(Base64Utils.decode(encry), priKey));
		System.out.println("对称秘钥明文：" + sesSK);
		// 2.对reqHead、data先Base64解码，然后使用对称秘钥分别解密
		reqHead = URLDecoder.decode(new String(AES.decrypt(Base64Utils.decode(reqHead), sesSK)),"UTF-8");
		data = URLDecoder.decode(new String(AES.decrypt(Base64Utils.decode(data), sesSK)),"UTF-8");

		System.out.println("reqHead明文：" + reqHead);
		System.out.println("data明文：" + data);

		// MD5签名，将签名值与signature对比
		newSignature = EncryptUtil.encode(reqHead + "&" + data + "&" + sesSK);
		if (!newSignature.equals(signature)) {
			System.err.println("签名不符，服务器签名：" + newSignature);
			throw new Exception(URLDecoder.decode("验签失败", "UTF-8"));
		} else {
			System.out.println("【签名校验通过！！！】:"+newSignature);
		}

		// 0-reqHead,1-data,2-sesSK
		reqJson[0] = reqHead;
		reqJson[1] = data;
		reqJson[2] = sesSK;

		System.out.println("Jason reqHead:" + reqJson[0]);
		System.out.println("Jason data:" + reqJson[1]);
		System.out.println("sesSK:" + sesSK);

		return reqJson;

	}

	public static void main(String[] args) {
		request = ReadFile.getInputParams("src/main/java/com/alanma/doraemon/utils/appreq/params/input.js");
		try {
			decodeReqInfo(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// String str = "{\"phoneNum\":\"13999999999\",\"certBusCase\":\"01\"}";
		// str = str.replace("}", ",\"reqChl\":\"01\"}");
		// System.out.println(str);
		// JSONObject jObj = JSONObject.parseObject(str);
		// System.out.println(jObj.toJSONString());
	}
}
