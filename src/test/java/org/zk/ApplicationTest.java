package org.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        ValueOperations<String,String> ops1 = stringRedisTemplate.opsForValue();
        ops1.set("name", "hello");
        System.out.println(ops1.get("a"));

    }
}
