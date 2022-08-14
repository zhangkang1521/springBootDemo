package org.zk.dao;

import org.zk.entity.User;

import java.util.List;

/**
 * @author zhangkang
 * @date 2022/8/10 16:09
 */
public interface UserDao {

    User getById(Long id);

    List<User> listAll();

    void insert(User user);

    void update(String username, Long id);

    void deleteById(Long id);
}
