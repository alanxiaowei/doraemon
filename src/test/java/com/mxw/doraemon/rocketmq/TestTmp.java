package com.mxw.doraemon.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class TestTmp {

	/**
	 * 当前例子是PushConsumer用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端。<br>
	 * 但是实际P导出                                        ushConsumer内部是使用长轮询Pull方式从MetaQ服务器拉消息，然后再回调用户Listener方法<br>
	 */
	public static void main(String[] args) {
		try {
			/**
			 * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
			 * 注意：ConsumerGroupName需要由应用来保证唯一
			 */
			DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("q_tick_cache");
			// consumer.setNamesrvAddr("192.168.2.172:9876");
			consumer.setNamesrvAddr("127.0.0.1:9876");
			consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
			consumer.setConsumeThreadMin(35);
			consumer.setConsumeThreadMax(35);
			consumer.setVipChannelEnabled(false);
			consumer.subscribe("q_tick", "*");

			consumer.registerMessageListener(new MessageListenerConcurrently() {

				/**
				 * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
				 */
				@Override
				public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
				                                                ConsumeConcurrentlyContext context) {
					System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs.size());

					MessageExt msg = msgs.get(0);

					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
				}
			});

			/**
			 * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
			 */
			consumer.start();

			System.out.println("Consumer Started.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}