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
import org.zk.entity.User;
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


    @ApiOperation(value="用户列表查询")
    @PostMapping("/search")
    public Result search(@RequestBody PageParam<UserParam> pageParam){
//        if(pageParam.getPageNo() == 2) {
//            Thread.sleep(1000);
//        }
        return Result.success(userService.queryPage(pageParam));
        //return Result.fail("错误！");
//        throw new RuntimeException("xxx");
    }

    @ApiOperation(value="查询单个用户")
    @GetMapping("/{id}")
    public Result findOne(@PathVariable("id") Long id) {
        return Result.success(userService.findOne(id));
    }

    @ApiOperation(value="保存单个用户")
    @PostMapping("")
    public Result save(@RequestBody User user) {
        return Result.success(userService.save(user));
    }

    @ApiOperation(value="删除单个用户")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return Result.success(null);
    }
}
