package org.zk.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.zk.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


	@Cacheable(cacheNames = "order")
	public List<User> findUser(List<Integer> userIds) {
		System.out.println("invoke findUsers");
		List<User> users = new ArrayList<>();
		for (int i = 0; i < userIds.size(); i++) {
			User user = new User();
			user.setId(userIds.get(i));
			users.add(user);
		}
		return users;
	}
}
