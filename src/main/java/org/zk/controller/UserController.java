package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zk.dao.UserDao;
import org.zk.model.User;

import java.util.List;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/")
    public List<User> index() {
        List<User> list = userDao.findList();
        return list;
    }
}
