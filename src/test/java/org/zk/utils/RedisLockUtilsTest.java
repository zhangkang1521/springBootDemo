package org.zk.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisLockUtilsTest {

    @Autowired
    private RedisLockUtils redisLockUtils;

    @Test
    public void acquireLock() {
        boolean acquired = redisLockUtils.acquireLock("aa", Duration.ofSeconds(10), Duration.ofMinutes(1));
        System.out.println(acquired);
    }
}