package org.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zk.entity.OrderEntity;
import org.zk.repo.OrderRepository;
import org.zk.service.OrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 8/5/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void test1() {
//        orderService.saveOrder();
        List<OrderEntity> orderEntities = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderId(Long.valueOf(i));
            orderEntity.setUserId(1L);
            orderEntities.add(orderEntity);
            orderRepository.saveAll(orderEntities);
        }
    }

    @Test
    public void test2() {
         List<OrderEntity> list = orderRepository.findByOrOrderIdBetween(3L, 12L);
    }
}
