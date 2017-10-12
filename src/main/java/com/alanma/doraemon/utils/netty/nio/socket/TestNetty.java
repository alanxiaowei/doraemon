package com.alanma.doraemon.utils.netty.nio.socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestNetty {
	public static void main(String[] args) {

		try {
			long t0 = System.nanoTime();
			ExecutorService executors = Executors.newFixedThreadPool(30);
			for (int i = 0; i < 1; i++) {
				final int ii = i;
				executors.execute(new Runnable() {

					@Override
					public void run() {
						try {
							NettyClientBootstrap bootstrap = new NettyClientBootstrap(7799, "192.168.2.17");
							byte[] value = (ii + ",你好").getBytes();
							bootstrap.sendMessage(value);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
			long t1 = System.nanoTime();
			System.out.println("=========================【处理时间】：" + (t1 - t0) / 1000000.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
