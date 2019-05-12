package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

//    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    Executor executor;

    @RequestMapping("/")
    public String index() throws Exception{
        for (int i = 0; i < 36073; i++) {
            executor.execute(new Demo(i));
            Thread.sleep(10);
        }
        return "hello";
    }
}

class Demo implements Runnable {

    private int id;

    public Demo(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread() + ":" + id);
    }
}
