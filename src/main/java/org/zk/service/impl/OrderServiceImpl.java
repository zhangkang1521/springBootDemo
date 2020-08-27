package org.zk.service.impl;

import org.springframework.stereotype.Service;
import org.zk.service.OrderService;
import org.zk.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
