package org.zk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zk.entity.OrderEntity;
import org.zk.repo.OrderRepository;

@Component
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	// 跨数据库怎么做到事务的？？？
//	@Transactional
	public void saveOrder() {
		// ds0
		for (int i = 1; i <= 4; i++) {
			OrderEntity order = new OrderEntity();
			order.setOrderId(Long.valueOf(i));
			order.setUserId(2L);
			orderRepository.save(order);
		}
//		if (true)
//			throw new RuntimeException("xx");
		// ds1
		for (int i = 5; i <= 8; i++) {
			OrderEntity order = new OrderEntity();
			order.setOrderId(Long.valueOf(i));
			order.setUserId(3L);
			orderRepository.save(order);
		}

//		throw new RuntimeException("xx");
	}
}
