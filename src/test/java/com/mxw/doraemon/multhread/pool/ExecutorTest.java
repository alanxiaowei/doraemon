package com.mxw.doraemon.multhread.pool;

import com.mxw.doraemon.utils.multhread.pool.ThreadPoolProcessor;

public class ExecutorTest {
	public static void main(String[] args) {
		ExecutorTest test = new ExecutorTest();
		test.disabledTradeOrderES("1234567890");
	}

	public void disabledTradeOrderES(String orderId) {
		ThreadPoolProcessor pool = ThreadPoolProcessor.getInstanceScheduled();
		OrderCancel orderCancel = new OrderCancel(orderId);
		// pool.scheduleAtFixedRate(orderCancel, 1, 5);
		pool.schedule(orderCancel, 5);
	}

	public class OrderCancel implements Runnable {
		private String orderId;

		public OrderCancel() {
			super();
		}

		public OrderCancel(String orderId) {
			super();
			this.orderId = orderId;
		}

		@Override
		public void run() {
			System.out.println("[disable order :]" + orderId);
		}

	}
}
