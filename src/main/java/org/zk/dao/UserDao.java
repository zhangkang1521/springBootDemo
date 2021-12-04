package org.zk.dao;

import org.zk.domain.User;

/**
 * @author zhangkang
 * @create 2021/11/21 17:04
 */
public interface UserDao {

	User findById(Long id);
}
