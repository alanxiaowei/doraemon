package com.mxw.doraemon.utils.jedis.queue;

import com.mxw.doraemon.utils.jedis.JedisUtil;
import redis.clients.jedis.JedisPubSub;

public class RedisMQTask implements Runnable {
	private String[] channels = null;// 监听的消息通道
	private JedisPubSub jedisPubSub = null;// 消息处理任务

	public RedisMQTask(JedisPubSub jedisPubSub, String... channels) {
		super();
		this.jedisPubSub = jedisPubSub;
		this.channels = channels;
	}

	@Override
	public void run() {
		System.out.println("准备订阅消息，线程开启阻塞");
		JedisUtil.subscribe(jedisPubSub, channels);
	}
}
