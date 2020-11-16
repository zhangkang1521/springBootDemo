package org.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zk.entity.OrderEntity;
import org.zk.repo.OrderRepository;
import org.zk.service.OrderService;

/**
 * Created by Administrator on 8/5/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    OrderService orderService;

    @Test
    public void test1() {
        orderService.saveOrder();
    }
}
