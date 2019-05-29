package com.mxw.doraemon.jedis;

import com.mxw.doraemon.utils.jedis.JedisUtil;
import com.mxw.doraemon.utils.jedis.queue.RedisMQHandler;
import com.mxw.doraemon.utils.jedis.queue.RedisMQTask;
import com.mxw.doraemon.utils.multhread.pool.ThreadPoolProcessor;

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
