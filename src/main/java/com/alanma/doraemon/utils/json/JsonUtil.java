package com.alanma.doraemon.utils.json;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	public static <T> T readJson(byte[] src, Class<T> clazz) {
		return JSONObject.parseObject(src, clazz);
	}
	
	public static void main(String[] args) {
		Person person=new Person("Alan","25");
		String personStr=JSONObject.toJSONString(person);
		System.out.println("~~~:"+personStr);
		Person person2=readJson(personStr.getBytes(), Person.class);
		System.out.println("===:"+person2.toString());
	}
}
