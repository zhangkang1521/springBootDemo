package org.zk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zk.dao.UserRepository;
import org.zk.entity.User;

@Service
public class OrderService {

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;

	@Transactional
	public void f1() {
		User user = new User();
		user.setUsername("f1");
		userRepository.save(user);
		userService.f2();
	}
}
