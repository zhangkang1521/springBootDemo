package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.zk.domain.User;

import java.util.Date;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // RequestResponseBodyMethodProcessor
    // 寻找合适的HttpMessageConverter进行转换
    @PostMapping("/requestBody")
    public void requestBody(@RequestBody User user){


    }

    // RequestParamMethodArgumentResolver
    @PostMapping("/requestParam")
    public User requestParam(@RequestParam("user") User user){
        return user;
    }

    // ServletModelAttributeMethodProcessor
    @PostMapping("/none")
    public void none(User user){

    }

    // RequestParamMethodArgumentResolver
    @PostMapping("/nonePrimary")
    public void nonePrimary(Integer id){

    }

    @RequestMapping("date")
    public Date dateTest(@RequestParam("date") Date date) {
        return date;
    }
}
