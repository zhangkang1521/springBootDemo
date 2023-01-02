package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/")
    public String index(){
        // 无序
//        for (int i = 1; i <= 10; i++) {
//             kafkaTemplate.send("demo-topic", "hello-" + i);
//        }

        // 顺序消费，一般是指定key落到不同分区，总体并发，单个分区顺序
        for (int i = 1; i <= 10; i++) {
            kafkaTemplate.send("demo-topic",  0, "hello-a-" + i);
        }
        for (int i = 1; i <= 10; i++) {
            kafkaTemplate.send("demo-topic", 1, "hello-b-" + i);
        }
        return "ok";
    }
}
