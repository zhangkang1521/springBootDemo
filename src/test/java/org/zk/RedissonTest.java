package org.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedissonTest {

	@Autowired
	RedissonClient redissonClient;

	private static Logger logger = LoggerFactory.getLogger(RedissonTest.class);

	@Test
	public void testLock() throws Exception {
		new Thread(new Task(redissonClient, "bb")).start();
		new Thread(new Task(redissonClient, "bb")).start();
		System.in.read();
	}

	@Test
	public void testLockMultiply() throws Exception {
		new Thread(new MultiplyTask(redissonClient, Arrays.asList("a", "b", "c"))).start();
		new Thread(new MultiplyTask(redissonClient, Arrays.asList("c"))).start();
		System.in.read();
	}

	static class MultiplyTask implements Runnable {
		RedissonClient redissonClient;
		private List<String> keys;

		private Logger logger = LoggerFactory.getLogger(MultiplyTask.class);

		public MultiplyTask(RedissonClient redissonClient, List<String> keys) {
			this.keys = keys;
			this.redissonClient = redissonClient;
		}

		@Override
		public void run() {
			RLock[] locks = new RLock[keys.size()];
			for (int i = 0; i < keys.size(); i++) {
				locks[i] = redissonClient.getLock(keys.get(i));
			}
			RedissonMultiLock lock = new RedissonMultiLock(locks);
			boolean lockSuccess = false;
			try {
				logger.info("try lock {}", keys);
				lockSuccess = lock.tryLock(3, 60*30, TimeUnit.SECONDS);
				if (lockSuccess) {
					logger.info("lock success {}", keys);
					Thread.sleep(5000);
				} else {
					logger.error("lock fail {}", keys);
				}
			} catch (InterruptedException e){
				e.printStackTrace();
			} finally {
				if (lockSuccess) {
					lock.unlock();
				}
			}
		}
	}



	static class Task implements Runnable {

		RedissonClient redissonClient;
		private String key;

		private Logger logger = LoggerFactory.getLogger(Task.class);

		public Task(RedissonClient redissonClient, String key) {
			this.redissonClient = redissonClient;
			this.key = key;
		}

		@Override
		public void run() {
			Lock lock = redissonClient.getLock(key);
			boolean lockSuccess = false;
			try {
				lockSuccess = lock.tryLock();
				if(lockSuccess) {
					logger.info("get lock {} success", key);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					logger.info("get lock {} fail", key);
				}
			} finally {
				if (lockSuccess) {
					lock.unlock();
				}
			}
		}
	}
}
