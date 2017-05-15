package com.alanma.doraemon.utils.exception;

public class TestException {
	public static void main(String[] args) {
		System.out.println("=======beginning");
		try {
			sendMsg();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void sendMsg() {
		int i = 0;
		try {
			i = 1 / 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			System.out.println("=======进来了");
		}
	}
}
