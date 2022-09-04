package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

    @Autowired
    private LoggingSystem loggingSystem;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/")
    public String index(){
        for (int i = 0; i < 1000; i++) {
            logger.trace("trace");
            logger.debug("debug");
            logger.info("info");
            logger.warn("warn");
            logger.error("error");
        }
        return "hello";
    }

    @RequestMapping("setLoggerLevel")
    public void setLoggerLevel(@RequestParam String level) {
        // 监听apollo配置，设置日志级别
        loggingSystem.setLogLevel("org.zk", LogLevel.valueOf(level.toUpperCase()));
    }
}
