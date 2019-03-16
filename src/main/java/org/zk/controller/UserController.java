package org.zk.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.zk.service.UserService;
import org.zk.vo.OpenAccount;
import org.zk.vo.OpenAccountResp;
import org.zk.vo.Packet;
import org.zk.vo.SpdbReq;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
@RequestMapping("spdb")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @PostMapping("/")
    @ApiOperation("浦发银行")
    public OpenAccountResp index(@RequestBody SpdbReq<OpenAccount> map) throws Exception{
//        BufferedReader br = request.getReader();
//
//        String str, wholeStr = "";
//        while((str = br.readLine()) != null){
//            wholeStr += str;
//        }
//        System.out.println(wholeStr);
        logger.info("进入");
        OpenAccountResp openAccountResp = new OpenAccountResp();
        List<OpenAccountResp.OpenAccountInfo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            OpenAccountResp.OpenAccountInfo info = new OpenAccountResp.OpenAccountInfo();
            info.setVAcctName("zk" + i);
            list.add(info);
        }
        userService.save();
        openAccountResp.setLists(list);
        openAccountResp.setRstCode("5");
        logger.info("返回");
        return openAccountResp;
    }
}
