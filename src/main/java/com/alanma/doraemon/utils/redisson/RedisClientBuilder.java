package com.alanma.doraemon.utils.redisson;

import java.util.List;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

public class RedisClientBuilder {

	public static RedissonClient buildRedissionClient(String mode, List<String> nodes, String password) {
		Config config = new Config().setCodec(StringCodec.INSTANCE);
		if ("single".equals(mode)) {
			SingleServerConfig ssc = config.useSingleServer();
			ssc.setAddress(nodes.get(0));
			if (password != null) {
				ssc.setPassword(password);
			}
			return Redisson.create(config);
		} else if ("cluster".equals(mode)) {
			ClusterServersConfig csc = config.useClusterServers();
			nodes.forEach(node -> {
				csc.addNodeAddress(node);
			});
			if (password != null) {
				csc.setPassword(password);
			}
			return Redisson.create(config);
		}
		return null;
	}
}
