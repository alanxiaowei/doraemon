package com.mxw.doraemon.fastjson;

import com.alibaba.fastjson.JSONObject;

public class TestJsonNested {
	public static void main(String[] args) {
		String json = "{\"BusiText\":{\"CdtrAcct\":\"6226666688886666\",\"CdtrBk\":\"102795040010\",\"CdtrNm\":\"测试电网公司\",\"DbtrAcct\":\"6226000011112222\",\"DbtrBk\":\"102795040024\",\"DbtrCnsn\":\"20170329000001\",\"DbtrNm\":\"测试Ma\",\"Prtry\":\"12345\",\"TxId\":\"20170331253688\",\"amt\":\"10560\"},\"MsgId\":\"2017033100000047\"}";
		CMT384Bean bean = (CMT384Bean) JSONObject.parseArray(json,
				CMT384Bean.class);

		System.out.println(bean.toString());
	}
}
