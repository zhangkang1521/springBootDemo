package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping("/")
    public String index(){
        byte[] bytes = new byte[100*1024*1024];
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }
}
