package com.alanma.doraemon.utils.arithmetic;

public class TestArithmetic {

	public static void main(String[] args) {
		testInt();
		testLongAdd();
	}

	private static void testInt() {
		int count = 6;
		int maxrow = 3;
		int pageno;

		if (count % maxrow == 0) {
			pageno = count / maxrow;
		} else {
			pageno = count / maxrow + 1;
		}

		System.out.println(pageno);
	}
	
	private static void testLongAdd() {
		Long long1=new Long(100);
		Long long2=new Long(10);
		System.out.println(long1-long2);
	}
}
