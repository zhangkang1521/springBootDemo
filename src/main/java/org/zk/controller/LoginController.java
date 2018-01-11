package org.zk.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zk.commons.Result;
import org.zk.controller.param.LoginParam;
import org.zk.entity.User;
import org.zk.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * Created by zhangkang on 2017/6/28.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    @ApiOperation("登录")
    @GetMapping("/login")
    public Result login(LoginParam loginParam) {
        Result<User> result = userService.login(loginParam);
        if(result.isSuccess()) {
            User user = result.getBody();
            session.setAttribute(session.getId(), user);
        }
        return result;
    }

    @ApiOperation("test")
    @GetMapping("/test")
    public void test(){
        Object obj = session.getAttribute(session.getId());
        session.setAttribute("a", "aaa");
        System.out.println(obj);
    }
}
