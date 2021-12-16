package org.zk.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.zk.model.User;

/**
 * @author zhangkang
 * @create 2021/12/7 14:18
 */
@Component
@Slf4j
public class UserDao {

	/**
	 * 	key: user::1
 	 */
	@Cacheable(cacheNames = "user")
	public User findById(Integer id) {
		log.info("ok");
		User user = new User();
		user.setId(id);
		return user;
	}
}
