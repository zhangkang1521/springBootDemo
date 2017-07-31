package org.zk.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.zk.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangkang on 2017/7/31.
 */
@Primary
@Service
public class HotSwapUserService implements UserService,ApplicationContextAware{

    private static final Logger logger = LoggerFactory.getLogger(HotSwapUserService.class);

    HotSwappableTargetSource target;

    ApplicationContext applicationContext;

    @Autowired
    @Qualifier("userServiceImpl1")
    UserService userServiceImpl1;

    @Autowired
    @Qualifier("userServiceImpl2")
    UserService userServiceImpl2;


    @PostConstruct
    private void init() {
        target = new HotSwappableTargetSource(userServiceImpl1);
    }

    public void swap(String beanName) {
        UserService userService = (UserService) applicationContext.getBean(beanName);
        target = new HotSwappableTargetSource(userService);
        logger.debug("swap userService:{}", beanName);
    }


    @Override
    public String sayHello() {
        return ((UserService)target.getTarget()).sayHello();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
