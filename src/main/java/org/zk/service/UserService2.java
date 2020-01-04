package org.zk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zk.dao.UserDao;
import org.zk.model.User;

@Service
public class UserService2 {

//    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.NESTED)
    public void saveUser(String username) {
        User user = new User();
        user.setUsername(username);
        userDao.insert(user);
        if ("zk3".equals(username))
            throw new RuntimeException("xx");
    }

}
