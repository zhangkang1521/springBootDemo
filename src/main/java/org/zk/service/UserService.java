package org.zk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zk.dao.UserDao;
import org.zk.domain.User;

/**
 * @author zhangkang
 * @create 2021/11/21 17:01
 */
@Component
public class UserService {

	@Autowired
	private UserDao userDao;

	public User addAge(Long id) {
		User user = userDao.findById(id);
		user.setAge(19);
		user.setAge(user.getAge() + 1);
		return user;
	}
}
