package org.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 8/5/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1() {
        ValueOperations ops1 = stringRedisTemplate.opsForValue();
        ops1.set("name", "1");
        ops1.increment("name", 10);
        System.out.println(ops1.get("name"));
    }

    @Test
    public void testDistributeLock() throws Exception {
        new Thread(new Task(stringRedisTemplate)).start();
        new Thread(new Task(stringRedisTemplate)).start();
        new Thread(new Task(stringRedisTemplate)).start();
        new Thread(new Task(stringRedisTemplate)).start();
        new Thread(new Task(stringRedisTemplate)).start();
        Thread.sleep(50000);
    }

    static class Task implements Runnable {
        StringRedisTemplate redisTemplate;

        public Task(StringRedisTemplate redisTemplate) {
            this.redisTemplate = redisTemplate;
        }

        @Override
        public void run() {
            ValueOperations setOperations = redisTemplate.opsForValue();
            boolean succ = setOperations.setIfAbsent("a", "1");
            if (succ) {
                System.out.println("ok");
            } else {
                System.out.println("fail");
            }
        }
    }
}
