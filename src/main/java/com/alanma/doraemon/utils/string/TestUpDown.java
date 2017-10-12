package com.alanma.doraemon.utils.string;

public class TestUpDown {
	public static void main(String[] args) {
		String str1="txnseqno";
		String str2="txnSeqNo";
		System.out.println(str1.equals(str2));
		System.out.println(str1.toUpperCase().equals(str2.toUpperCase()));
	}
}
