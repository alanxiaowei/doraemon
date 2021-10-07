package com.mxw.doraemon.redisson;

import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.codec.SerializationCodec;

import java.util.Arrays;
import java.util.List;

/**
 * @program: doraemon
 * @description: publish
 * @author: AlanMa
 * @create: 2020-03-13 18:04
 */
public class Subscribe {

	List<String> nodes = Arrays.asList("redis://127.0.0.1:6379");
	RedissonClient redisClient = RedisClientBuilder.buildRedissionClient("single", nodes, null);

	void subscribe() {
		RTopic topic = redisClient.getTopic("test-scribe", new SerializationCodec());


		topic.addListener((MessageListener<User>) (channel, user) -> {
			System.out.println("onMessage:" + channel + "; Thread: " + Thread.currentThread().toString());
			System.out.println("name : " + user.getName() + " address : " + user.getAddress());
		});
	}

	public static void main(String[] args) {
		Subscribe subscribe = new Subscribe();
		subscribe.subscribe();
	}

}
