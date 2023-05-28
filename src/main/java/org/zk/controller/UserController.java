package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zk.service.UserService;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/")
    public String index(){
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        return "hello";
    }
}
