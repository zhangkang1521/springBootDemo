package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zk.params.UserParam;

import java.util.Locale;

/**
 * Created by zhangkang on 2017/7/31.
 */
@Controller
public class UserController implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String msg = applicationContext.getMessage("test", null, Locale.US);
        String msg2 = applicationContext.getMessage("test", null, Locale.CHINA);
    }
}
