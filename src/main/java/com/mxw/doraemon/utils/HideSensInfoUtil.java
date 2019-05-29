package com.mxw.doraemon.utils;

/**
 * @author AlanMa
 *
 */
public class HideSensInfoUtil {

	/**
	 * 隐藏身份证敏感信息
	 * 
	 * @param idNum
	 * @return
	 */
	public static String hideIdNum(String idNum) {
		String regex = "(\\w{6})(\\w+)(\\w{4})";
		return idNum.replaceAll(regex, "$1********$3");
	}

	/**
	 * 隐藏电话敏感
	 * 
	 * @param idNum
	 * @return
	 */
	public static String hidePhoneNum(String phoneNum) {
		String regex = "(\\w{3})(\\w+)(\\w{4})";
		return phoneNum.replaceAll(regex, "$1****$3");
	}

	/**
	 * 隐藏银行卡敏感信息
	 * 
	 * @param idNum
	 * @return
	 */
	public static String hideCardNum(String cardNum) {
		int length = cardNum.length();
		String regex = "(\\w{8})(\\w+)(\\w{6})";
		String hideCardNum = null;
		switch (length) {
		case 18:
			hideCardNum = cardNum.replaceAll(regex, "$1****$3");
			break;
		case 19:
			hideCardNum = cardNum.replaceAll(regex, "$1*****$3");
			break;
		case 20:
			hideCardNum = cardNum.replaceAll(regex, "$1******$3");
			break;
		case 21:
			hideCardNum = cardNum.replaceAll(regex, "$1*******$3");
			break;

		default:
			hideCardNum = cardNum.replaceAll(regex, "$1****$3");
			break;
		}
		return hideCardNum;
	}
}
