package org.zk.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

	@Value("${redis.address}")
	private String[] redisAddress;

//	@Bean
//	public RedissonClient createProcessRedisClient(){
//		Config config = new Config();
//		config.useSingleServer().setAddress("redis://10.200.6.99:6379");
//		return Redisson.create(config);
//	}

	@Bean
	public RedissonClient createProcessRedisClient(){
		Config config = new Config();
		config.useClusterServers().addNodeAddress(redisAddress);
		return Redisson.create(config);
	}
}
