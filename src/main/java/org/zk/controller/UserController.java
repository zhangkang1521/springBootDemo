package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zk.config.AuthorSetting;
import org.zk.config.BookSetting;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    AuthorSetting authorSetting;
    @Autowired
    BookSetting bookSetting;

    @RequestMapping("/")
    public String index(){
        System.out.println(authorSetting);
        return "hello";
    }
}
