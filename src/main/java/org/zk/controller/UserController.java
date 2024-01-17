package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zk.comm.BusinessException;
import org.zk.vo.UserVo;

/**
 * Created by zhangkang on 2017/7/31.
 */
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/test")
    @ResponseBody
    public UserVo index(){
        UserVo userVo = new UserVo();
        userVo.setId(100);
        return userVo;
    }

    @RequestMapping("/businessException")
    @ResponseBody
    public UserVo businessException(){
        throw new BusinessException("501", "security check fail");
    }

    @RequestMapping("/otherException")
    @ResponseBody
    public UserVo otherException(){
        throw new RuntimeException("null point");
    }
}
