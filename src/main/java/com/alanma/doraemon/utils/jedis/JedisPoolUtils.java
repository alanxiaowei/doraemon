package com.alanma.doraemon.utils.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {

	// private static ResourceBundle bundle;

	private static JedisPool pool;

	private static int MAX_ACTIVE = 20;
	private static int MAX_WAIT = 15000;
	private static int MAX_IDLE = 20;
	private static String HOST = "192.168.2.12";
	private static int PORT = 6379;

	/**
	 * 建立连接池 真实环境，一般把配置参数缺抽取出来。
	 */
	private static void createJedisPool() {

		// bundle = ResourceBundle.getBundle("redis");

		// 建立连接池配置参数
		JedisPoolConfig config = new JedisPoolConfig();

		// 设置最大连接数
		config.setMaxTotal(MAX_ACTIVE);

		// 设置最大阻塞时间，记住是毫秒数milliseconds
		config.setMaxWaitMillis(MAX_WAIT);

		// 设置空间连接
		config.setMaxIdle(MAX_IDLE);

		// 创建连接池
		pool = new JedisPool(config, HOST, PORT);

	}

	/**
	 * 在多线程环境同步初始化
	 */
	private static synchronized void poolInit() {
		if (pool == null)
			createJedisPool();
	}

	/**
	 * 获取一个jedis 对象
	 * 
	 * @return
	 */
	public static Jedis getJedis() {

		if (pool == null)
			poolInit();
		return pool.getResource();
	}

	/**
	 * 归还一个连接
	 * 
	 * @param jedis
	 */
	public static void returnRes(Jedis jedis) {
		jedis.close();
	}

}