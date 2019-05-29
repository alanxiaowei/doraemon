package com.mxw.doraemon.reflect;


import com.mxw.doraemon.reflect.domain.RTWithholdRespBean;

import java.lang.reflect.Field;


/**
 * String转bean
 * @author AlanMa
 *
 */
public class StringToBeanUtil {

	public static void main(String[] args) throws Exception {
		String message = "responseCode=00&merId=808080080988041&transDate=20120730&orderNo=2012071440859375&transAmt=000000000005&curyId=156&transType=0003&priv1=\u79c1\u6709\u57df&transStat=1001&gateId=7008&cardType=0&cardNo=6225884004992185&userNme=\u7528\u6237\u540d&certType=01&certId=320925198705081111&chkValue=A752D6EDCB31672333FFF5ABA66A7E422532C58F2C8B2B8A6B1581F4EA6AAD431D450881D5B0ADD3B0CABC20D684E087A22FD9810E731A4B79864167D32FF09BA75A3FF200901FF71C30BB7D0D6163B42B1C7C4D131511D5A7B2FE51A91225D63E828707DEFD65A0091AF5117D9F5C1DA59E95DB5D5E265FAF3EA91A42398B7B";
		RTWithholdRespBean bean = (RTWithholdRespBean) parseToBean(new RTWithholdRespBean(), message);
		System.out.println("[Bean]:" + bean.toString());
	}

	public static Object parseToBean(Object bean, String message) {
		Class cls = null;
		try {
			// 得到类对象
			cls = (Class) bean.getClass();
			/*
			 * 得到类中的所有属性集合
			 */
			Field[] fs = cls.getDeclaredFields();

			String[] arrs = message.split("&");
			for (String arrStr : arrs) {
				String[] kv = arrStr.split("=");
				if (kv.length == 2) {
					for (Field f : fs) {
						f.setAccessible(true); // 设置些属性是可以访问的
						if (f.getName().equals(kv[0])) {
							 System.out.println("["+f.getName()+"]-"+"["+kv[0]+"]");
							f.set(bean, kv[1]);
							continue;
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}
}
