package org.zk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Async
    public void createOrder(int i) {
        logger.info("createOrder start " + i);
        for (int j = 0; j < 10000; j++) {
            for (int k = 0; k < 100000; k++) {
                Math.pow(2 + i, 344.32 + i);
            }
        }
        logger.info("createOrder end " + i);
    }
}
