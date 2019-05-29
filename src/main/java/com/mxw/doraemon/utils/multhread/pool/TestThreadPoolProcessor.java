package com.mxw.doraemon.utils.multhread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class TestThreadPoolProcessor {

	// public static void main(String[] args) {
	// TestThreadPoolProcessor pool = new TestThreadPoolProcessor();
	// for (int i = 1; i <= 30; i++) {
	// // System.out.print(CurrentLimitUtil.currentLimit("clmxw001") + "|");
	// pool.threadPool();
	// }
	// System.out.println("=============================");
	// }

	public static void main(String[] args) {
		TestThreadPoolProcessor poolProcessor = new TestThreadPoolProcessor();
		ThreadPoolProcessor pool = ThreadPoolProcessor.getInstanceFixed(20);
		List<MsgSenderCall> tasks = new ArrayList<MsgSenderCall>();
		for (int i = 1; i <= 30; i++) {
			tasks.add(poolProcessor.threadPoolCall());
		}
		pool.invokeAll(tasks);
		System.out.println("=============================");
	}

	public void threadPool() {
		ThreadPoolProcessor pool = ThreadPoolProcessor.getInstanceFixed(20);
		Random randomNum = new Random();
		int random = randomNum.nextInt();
		MsgSender msgSender = new MsgSender(Integer.toString(random));
		pool.execute(msgSender);
	}

	public MsgSenderCall threadPoolCall() {

		Random randomNum = new Random();
		int random = randomNum.nextInt();
		MsgSenderCall msgSender = new MsgSenderCall(Integer.toString(random));

		return msgSender;
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

	public class MsgSenderCall implements Callable<String> {

		private String message;

		public MsgSenderCall(String message) {
			super();
			this.message = message;
		}

		@Override
		public String call() throws Exception {
			System.out.println("[" + Thread.currentThread().getName() + "]:" + message);
			return message;
		}
	}

}
