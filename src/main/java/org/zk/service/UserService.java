package org.zk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private OrderService orderService;

    public void sayHello(int i) {
        logger.info("sayHello " + i);
        orderService.createOrder(i);
    }
}
