package org.zk.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.zk.dubbo.api.DemoService;

/**
 * Created by zhangkang on 2019/3/6.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHi(String message) {
        return "hi," + message;
    }
}
