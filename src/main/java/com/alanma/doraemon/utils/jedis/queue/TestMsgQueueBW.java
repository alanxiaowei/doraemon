package com.alanma.doraemon.utils.jedis.queue;


import org.apache.commons.lang.math.RandomUtils;

import com.alanma.doraemon.utils.jedis.JedisUtil;
import com.alanma.doraemon.utils.multhread.pool.ThreadPoolProcessor;
import com.alanma.doraemon.utils.random.RandomTest;
import com.alibaba.fastjson.JSONObject;

public class TestMsgQueueBW {

	public static void main(String[] args) throws InterruptedException {
		// 模拟启动监听
		ThreadPoolProcessor pool = ThreadPoolProcessor.getInstanceFixed(5);
		//RedisMQTask msgSender = new RedisMQTask(new RedisMQHandler(), "topic_bar");
		//pool.execute(msgSender);
		
		//生成消息
		String message="{\"symbol\":\"BTC\",\"price\":\"98765.36\",\"amount\":\"0.06\",\"createdAt\":\"1515507013096\"}";
		
		TickTopic tick=JSONObject.parseObject(message, TickTopic.class);
//		System.out.println(tick.toString());
		
		for(int i=0;i<100;i++) {
			tick.setAmount(i+1);
			tick.setPrice(RandomTest.rand(5));
			message=JSONObject.toJSONString(tick);
			System.out.println(message);
			// 放入消息队列
			JedisUtil.publish("topic_bar", message);
			System.out.println("~~~~~~~~~end~~~~~~~~~");
		}

		
	}
	
	
	 
}
