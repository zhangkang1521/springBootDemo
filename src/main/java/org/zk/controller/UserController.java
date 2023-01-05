package org.zk.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    @RequestMapping("/")
    public String index() {
        // 设置header无效，topic:xx这种指定tag?
        for (int i = 0; i < 3; i++) {
            Message message = MessageBuilder.withPayload("hello-a-" + i)
//                    .setHeader(RocketMQHeaders.QUEUE_ID, 0)
//                    .setHeader(RocketMQHeaders.KEYS, "a")
//                    .setHeader(RocketMQHeaders.TAGS, "xx")
                    .build();
            rocketMQTemplate.send("demo-topic-2:xx", message);
        }
        return "ok";
    }

    @RequestMapping("/order")
    public String order() {
        for (int i = 0; i < 3; i++) {
            // 会根据hashKey选择队列
            rocketMQTemplate.syncSendOrderly("demo-topic", "order-a-" + i, "a");
            rocketMQTemplate.syncSendOrderly("demo-topic", "order-b-" + i, "b");
        }
        return "ok";
    }
}
