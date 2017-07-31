package org.zk.service.impl;

import org.springframework.stereotype.Service;
import org.zk.service.UserService;

/**
 * Created by zhangkang on 2017/7/31.
 */
@Service
public class UserServiceImpl1 implements UserService {
    @Override
    public String sayHello() {
        return "UserServiceImpl1";
    }
}
