package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zk.vo.UserVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangkang on 2017/7/31.
 */
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/")
    public String index(Model model, HashMap<String,Object> map){
       // model.addAttribute("username", "zk");
        map.put("username", "zy");
        List<UserVO> userList = new ArrayList<>();
        for (int i=0; i<5; i++) {
            UserVO userVO = new UserVO();
            userVO.setId(i);
            userVO.setUsername("zk" + i);
            userVO.setAge(10 + i);
            userList.add(userVO);
        }
        map.put("userList", userList);
        return "hello";
    }
}
