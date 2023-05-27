package org.zk.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * RedisCacheConfiguration 注册CacheManager
 */
@EnableCaching
@Configuration
public class CacheConfig {

//	@Bean
//	public CacheManager cacheManager() {
//		return new ConcurrentMapCacheManager("user");
//	}


	/**
	 * 如果配置了，自动配置就不会生效
	 * @see org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
		// 配置每个缓存不同的缓存时间
		Map<String, RedisCacheConfiguration> cacheNamesConfigurationMap = new HashMap<>();
		cacheNamesConfigurationMap.put("user",
				RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(100)));
		cacheNamesConfigurationMap.put("order",
				RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(200)));

		RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
				.builder(connectionFactory)
				.withInitialCacheConfigurations(cacheNamesConfigurationMap)
				.cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(300)));
		return builder.build();
	}

}
