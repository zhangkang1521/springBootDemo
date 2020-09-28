package org.zk.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import org.zk.dubbo.api.DemoService;

/**
 * Created by zhangkang on 2019/3/6.
 */
@Service()
public class DemoServiceImpl implements DemoService {

    @Reference
    org.zk.dubbo.interfaces.DemoService demoService;

    @Override
    public String sayHi3(String message) {
        return demoService.sayHello(message);
//        return "hi," + message;
    }
}
