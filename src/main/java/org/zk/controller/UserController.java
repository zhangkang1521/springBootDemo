package org.zk.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zk.controller.param.UserParam;
import org.zk.dao.UserRepository;
import org.zk.entity.User;

import java.util.List;

/**
 * Created by zhangkang on 2017/6/15.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;


    @ApiOperation(value="获取用户列表", notes="获取用户列表，后面还有很多说明")
    @PostMapping("/list")
    public List<User> list(@RequestBody UserParam userParam){
//        List<User> userList = new ArrayList<User>();
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("zk");
//        userList.add(user);
        List<User> userList = userRepository.findAll();
        return userList;
    }
}
