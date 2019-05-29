package com.mxw.doraemon.utils;

import java.math.BigDecimal;

public class AmtParseUtil {

	/**
	 * 金额转换String（元）转Long（分）
	 * 
	 * @param amount
	 * @return
	 */
	public static Long strToLongAmt(String amount) {
		BigDecimal amt = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal amtUnit = new BigDecimal("100");
		amt = amt.multiply(amtUnit);
		return amt.longValue();
	}

	/**
	 * 金额转换Long（分）转String（元）
	 * 
	 * @param amount
	 * @return
	 */
	public static String longToStrAmt(Long amount) {
		BigDecimal amt = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal amtUnit = new BigDecimal("100");
		amt = amt.divide(amtUnit);
		return amt.toString();
	}
	
	/**
	 * 百分数转换保留1位小数
	 * 
	 * @param amount
	 * @return
	 */
	public static String longToStrPercent(Long amount) {
		BigDecimal amt = new BigDecimal(amount).setScale(1, BigDecimal.ROUND_HALF_UP);
		return amt.toString();
	}
	
	/**
	 * 金额转换String（分）转String（元）
	 * 
	 * @param amount
	 * @return
	 */
	public static String strToStrAmt(String amount) {
		BigDecimal amt = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal amtUnit = new BigDecimal("100");
		amt = amt.divide(amtUnit);
		return amt.toString();
	}

}
