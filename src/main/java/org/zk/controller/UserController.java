package org.zk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zk.dao.UserRepository;
import org.zk.entity.User;

import java.util.List;

/**
 * Created by zhangkang on 2017/6/15.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;


    @RequestMapping("/list")
    @ResponseBody
    public List<User> list(){
//        List<User> userList = new ArrayList<User>();
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("zk");
//        userList.add(user);
        List<User> userList = userRepository.findAll();
        return userList;
    }
}
