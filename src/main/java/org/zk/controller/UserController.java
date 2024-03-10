package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/addDelay")
    public String index(String taskName, Integer delay){
        logger.info("add task {} {}", taskName, delay);
       redisTemplate.opsForZSet().add(DelayQueueRunner.QUEUE_KEY, taskName, System.currentTimeMillis() + delay*1000);
       return "ok";
    }


}
