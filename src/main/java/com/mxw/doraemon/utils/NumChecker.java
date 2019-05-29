package com.mxw.doraemon.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumChecker {
	/**
	 * 检查整数
	 * 
	 * @param num
	 * @param type
	 *            "1":非负整数<br>
	 *            "2":正整数<br>
	 *            "3":非正整数<br>
	 *            "4":负整数<br>
	 *            "5":整数
	 * @return
	 */
	public static boolean checkNumber(String num, String type) {
		String eL = "";
		if (type.equals("1"))
			eL = "^\\d+$";// 非负整数
		else if (type.equals("2"))
			eL = "^\\d*[1-9]\\d*$";// 正整数
		else if (type.equals("3"))
			eL = "^((-\\d+)|(0+))$";// 非正整数
		else if (type.equals("4"))
			eL = "^-\\d*[1-9]\\d*$";// 负整数
		else
			eL = "^-?\\d+$";// 整数
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(num);
		boolean b = m.matches();
		return b;
	}

	public static void main(String[] args) {
		String num = "5.00";
		System.out.println(checkNumber(num, "+"));
	}
}
