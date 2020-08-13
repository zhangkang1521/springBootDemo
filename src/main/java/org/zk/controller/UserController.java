package org.zk.controller;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.rpc.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController implements ApplicationContextAware {

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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       // Object protocol = applicationContext.getBean("dubbo");
    }
}
