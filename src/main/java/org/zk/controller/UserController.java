package org.zk.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/hello")
    @SentinelResource(value = "hello", blockHandler = "blockHandler", fallback = "fallback")
    public String index(long s){
        logger.info("invoke hello {}", s);
        if (s == 1) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // throw new RuntimeException("xxx");
        }
        return "hello";
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public String fallback(long s) {
        logger.info("降级 {}", s);
        return String.format("降级 %d", s);
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String blockHandler(long s, BlockException ex) {
        logger.info("流控 {}", s);
        return "流控" + s;
    }
}
