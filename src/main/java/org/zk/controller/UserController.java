package org.zk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zk.service.UserService;
import org.zk.service.impl.HotSwapUserService;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/sayHello")
    public String sayHello(){
       return userService.sayHello();
    }

    @RequestMapping("/swap")
    public String swap(String bean){
        ((HotSwapUserService)userService).swap(bean);
        return "已经切换到"+bean;
    }
}
