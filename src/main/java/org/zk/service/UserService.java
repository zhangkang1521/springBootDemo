package org.zk.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by zhangkang on 2019/3/15.
 */
@Service
@Slf4j
public class UserService {

    @Async
    public void save() {

        log.info("异步调用开始");
        throw new RuntimeException();
//        try {
//            Thread.sleep(10000);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info("异步调用end");
    }
}
