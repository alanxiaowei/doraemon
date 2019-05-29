package com.mxw.doraemon.utils;

/**
 * 
 * @author xw_ma
 * @version 1.0, 2016年4月21日
 * @description 验证码生成工具类
 */
public class CertCodeUtil {
	/**
	 * 获取注册短信验证码(6位)
	 *
	 * @param calendar
	 *            日历对象
	 */
	public static String getRegMsgCertCode() {
		String regMsgCertCode = (int) ((Math.random() * 9 + 1) * 100000) + "";
		return regMsgCertCode;
	}

	public static void main(String[] args) {
		System.out.println(getRegMsgCertCode());
	}
	
}
