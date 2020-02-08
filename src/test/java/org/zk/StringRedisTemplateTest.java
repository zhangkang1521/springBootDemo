package org.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StringRedisTemplateTest {

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Test
	public void testValue() {
		ValueOperations valueOperations = stringRedisTemplate.opsForValue();
		valueOperations.set("a", "1");
		System.out.println(valueOperations.get("a"));
	}

	@Test
	public void testList() {
		ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();
		listOperations.rightPushAll("list","1", "2", "3");
		listOperations.leftPop("list");
	}
}
