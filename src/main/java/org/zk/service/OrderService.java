package org.zk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zk.dao.UserRepository;
import org.zk.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class OrderService {

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	EntityManager entityManager;

	@Transactional
	public void f1() {
//		User user = new User();
//		user.setUsername("f1");
//		userRepository.save(user);

		Query query = entityManager.createNativeQuery("update tb_user set username = 'xx' where id = 1");
		query.executeUpdate();
	}
}
