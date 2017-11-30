package com.alanma.doraemon.utils.jedis.queue;

import com.alanma.doraemon.utils.jedis.JedisUtil;
import com.alanma.doraemon.utils.multhread.pool.ThreadPoolProcessor;

public class TestMsgQueue {

	public static void main(String[] args) throws InterruptedException {
		// 模拟启动监听
		ThreadPoolProcessor pool = ThreadPoolProcessor.getInstanceFixed(5);
		RedisMQTask msgSender = new RedisMQTask(new RedisMQHandler(), "Channel001");
		pool.execute(msgSender);

		// 放入消息队列
		JedisUtil.publish("Channel001", "001test111111111");
	}
}
