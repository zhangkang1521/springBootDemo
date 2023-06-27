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


    @RequestMapping("/send")
    public void index() {
        rocketMQTemplate.convertAndSend("demo-topic", "hello");
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
