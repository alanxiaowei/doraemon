package com.alanma.doraemon.utils.jedis.queue;

import redis.clients.jedis.JedisPubSub;

public class RedisMQHandler extends JedisPubSub {

	@Override
	public void onMessage(String channel, String message) {
		System.out.println(channel + "," + message);
		if ("Channel001".equals(channel)) {
			System.out.println("接收到队列消息：" + message);
		}
	}
}