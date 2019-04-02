package com.alanma.doraemon.utils.math;

import java.math.BigDecimal;

public class BigDecimalTest {
	public static void main(String[] args) {
		String price1="9230.24";
		String price2="9230.24000000";
		BigDecimal bd1=new BigDecimal(price1);
		BigDecimal bd2=new BigDecimal(price2);
		
		System.out.println(bd1);
		System.out.println(bd2);
		
		System.out.println(bd1.divide(new BigDecimal(1), 8, BigDecimal.ROUND_HALF_UP));
		System.out.println(bd2.divide(new BigDecimal(1), 8, BigDecimal.ROUND_HALF_UP));
		
		
	}
}
