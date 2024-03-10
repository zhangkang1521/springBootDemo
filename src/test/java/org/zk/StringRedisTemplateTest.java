package org.zk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StringRedisTemplateTest {

	@Autowired
	StringRedisTemplate redisTemplate;

	@Test
	public void testValue() {
		ValueOperations valueOperations = redisTemplate.opsForValue();
		valueOperations.set("a", "1");
		System.out.println(valueOperations.get("a"));
	}

	@Test
	public void testList() {
		ListOperations<String, String> listOperations = redisTemplate.opsForList();
		listOperations.rightPushAll("list","1", "2", "3");
		listOperations.leftPop("list");
	}

	@Test
	public void blpop() {
		while (true) {
			log.info("blpop:");
			String result = redisTemplate.opsForList().leftPop("list", 5, TimeUnit.SECONDS);
			log.info("result:{}", result);
		}
	}

	@Test
	public void createChat() {
		redisTemplate.opsForZSet().add("chat:100", "zx", 0);
		redisTemplate.opsForZSet().add("chat:100", "zk", 0);
		redisTemplate.opsForZSet().add("chat:200", "zx", 0);
		redisTemplate.opsForZSet().add("chat:200", "rrm", 0);

		redisTemplate.opsForZSet().add("seen:zx", "100", 0);
		redisTemplate.opsForZSet().add("seen:zx", "200", 0);

		redisTemplate.opsForZSet().add("seen:zk", "100", 0);
		redisTemplate.opsForZSet().add("seen:rrm", "200", 0);
	}

	@Test
	public void sendMsg() {
		redisTemplate.opsForZSet().add("msg:100", "hello,100,first", 0);
	}

	@Test
	public void receive() {
		String userName = "zk";
		Iterator<ZSetOperations.TypedTuple<String>>  seenIt = redisTemplate.opsForZSet().rangeWithScores("seen:" + userName, 0 , -1).iterator();
		while (seenIt.hasNext()) {
			ZSetOperations.TypedTuple<String> seen = seenIt.next();
			String chatId = seen.getValue();
			int curPos = seen.getScore().intValue();
			Iterator<String> msgIt = redisTemplate.opsForZSet().range("msg:" + chatId, curPos, curPos + 1).iterator();
			while (msgIt.hasNext()) {
				String msg = msgIt.next();
				log.info("{} in chat {} receive msg {}", userName, chatId, msg);
				redisTemplate.opsForZSet().incrementScore("chat:" + chatId, userName, 1);
				redisTemplate.opsForZSet().incrementScore("seen:" + userName, chatId, 1);
			}
		}
	}
}
