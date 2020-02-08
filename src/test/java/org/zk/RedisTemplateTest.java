package org.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {
	@Autowired
	RedisTemplate redisTemplate;

	@Test
	public void test1() {
		ValueOperations<String, Integer> valueOperations = redisTemplate.opsForValue();
		valueOperations.set("a", 10);
		//valueOperations.increment("a", 1);
		System.out.println(valueOperations.get("a"));
	}
}
