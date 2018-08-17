package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zk.mq.Producer;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    Producer producer;

    @RequestMapping("/")
    public String index(String msg){
        producer.send(msg);
        return "send!";
    }

    @RequestMapping("/send2")
    public String send2(String msg){
        for (int i = 0; i<100; i++) {
            producer.send2("foo", "foo1-" + i);
            if (i % 10 == 0) {
                producer.send2("foo2", "foo2-"+i);
            }
        }
        return "send2!";
    }
}
