package org.zk.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@Slf4j
public class RedisLockUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @SneakyThrows
    public boolean acquireLock(String lockName, Duration wait, Duration timeout) {
        LocalDateTime end = LocalDateTime.now().plus(wait);
        while (LocalDateTime.now().isBefore(end)) {
            log.info("尝试获取锁 {}", lockName);
            Boolean result = redisTemplate.opsForValue().setIfAbsent("lock:" + lockName, "1", timeout);
            if (result) {
                return true;
            }
            Thread.sleep(50);
        }
        return false;
    }
}
