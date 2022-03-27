package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zk.service.UserService;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController implements ApplicationContextAware {


    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private ApplicationContext applicationContext;

    public UserController() {
        logger.info("UserController()");
    }


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/")
    public String index(){
        logger.info("userController:{}", applicationContext.getBean("userController"));
        logger.info("this:{} userService:{}", this, userService);
        return "hello";
    }

    @RequestMapping("/2")
    private String index2(){

        logger.info("this:{} userService:{}", this, userService);
        return "hello";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
