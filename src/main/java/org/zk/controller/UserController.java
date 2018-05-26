package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zk.config.PrivConfig;
import org.zk.properties.CcbProp;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${gcb.url}")
    private String gcbUrl;

    @Autowired
    private PrivConfig privConfig;
    @Autowired
    private CcbProp ccbProp;

    @Value("${ccb.url}")
    private String ccbUrl;

    @RequestMapping("/")
    public String index(){
         logger.trace(gcbUrl);
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error(gcbUrl);
        return "hello";
    }
}
