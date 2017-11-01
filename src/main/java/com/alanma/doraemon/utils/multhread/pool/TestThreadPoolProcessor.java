package com.alanma.doraemon.utils.multhread.pool;

import java.util.Random;

import com.alanma.doraemon.utils.jedis.CurrentLimitUtil;

public class TestThreadPoolProcessor {

	public static void main(String[] args) {
		TestThreadPoolProcessor pool = new TestThreadPoolProcessor();
		for (int i = 1; i <= 20; i++) {
			System.out.print(CurrentLimitUtil.currentLimit("clmxw001") + "|");
			pool.threadPool();
		}
	}

	public void threadPool() {
		ThreadPoolProcessor pool = ThreadPoolProcessor.getInstanceFixed(20);
		Random randomNum = new Random();
		int random = randomNum.nextInt();
		MsgSender msgSender = new MsgSender(Integer.toString(random));
		pool.execute(msgSender);
	}

	public class MsgSender implements Runnable {

		private String message;

		public MsgSender(String message) {
			super();
			this.message = message;
		}

		@Override
		public void run() {
			System.out.println("[" + Thread.currentThread().getName() + "]:" + message);
		}
	}
}
