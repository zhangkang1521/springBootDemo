package org.zk.dao;

import org.zk.entity.User;

import java.util.List;

/**
 * @author zhangkang
 * @date 2022/8/10 16:09
 */
public interface UserDao {

    User getById(Long id);

    List<User> listByUsername(String username);

    void insert(User user);
}
