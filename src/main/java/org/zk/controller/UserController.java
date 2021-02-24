package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zk.params.UserParam;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/validate")
    public String validate(@Valid @RequestBody UserParam userParam){
        return "test";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String msg = applicationContext.getMessage("test", null, Locale.US);
        String msg2 = applicationContext.getMessage("test", null, Locale.CHINA);
    }
}
