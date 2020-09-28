package org.zk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zk.dubbo.api.DemoService;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference
    DemoService demoService;


    @RequestMapping("/sayHi3")
    public String sayHi(){
        return demoService.sayHi3("hi");
    }
}
