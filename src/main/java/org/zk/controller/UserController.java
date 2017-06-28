package org.zk.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zk.commons.PageParam;
import org.zk.commons.Result;
import org.zk.controller.param.UserParam;
import org.zk.dao.UserRepository;
import org.zk.service.UserService;

/**
 * Created by zhangkang on 2017/6/15.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    @ApiOperation(value="获取用户列表", notes="获取用户列表，后面还有很多说明")
    @PostMapping("/list")
    public Result list(@RequestBody PageParam<UserParam> pageParam){
        return Result.success(userService.queryPage(pageParam));
    }
}
