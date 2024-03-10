package org.zk.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

@Component
@Slf4j
public class DelayQueueRunner implements ApplicationRunner {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public static final String QUEUE_KEY = "delayQueue";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Thread(new Runnable() {
            @Override
            @SneakyThrows
            public void run() {
                while (true) {
                    Set<ZSetOperations.TypedTuple<String>> task = redisTemplate.opsForZSet().rangeWithScores(QUEUE_KEY, 0, 0);
                    Iterator<ZSetOperations.TypedTuple<String>> it = task.iterator();
                    if (!it.hasNext()) {
                        Thread.sleep(50);
                        continue;
                    }
                    ZSetOperations.TypedTuple tuple = it.next();
                    if (tuple.getScore().longValue() > System.currentTimeMillis()) {
                        // 时间未到
                        Thread.sleep(50);
                        continue;
                    }

                    log.info("execute task:{} score:{}", tuple.getValue(), tuple.getScore());
                    redisTemplate.opsForZSet().remove(QUEUE_KEY, tuple.getValue());
                }
            }
        }).start();
    }
}
