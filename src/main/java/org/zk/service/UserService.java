package org.zk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.zk.interceptor.RedisLock;

@Service
public class UserService {

	private static Logger log = LoggerFactory.getLogger(UserService.class);

	@RedisLock(keySpel = "#userParam.userId")
//	@RedisLock(keySpel = "#userParam.orderIds")
	public void sayHello(UserParam userParam) {
		log.info("invoke start " + userParam.getOrderIds());
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("invoke end " + userParam.getOrderIds());
	}
}
