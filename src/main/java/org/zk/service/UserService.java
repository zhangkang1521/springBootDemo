package org.zk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zk.dao.UserDao;
import org.zk.model.User;

@Service
public class UserService {

//    @Autowired
    private UserDao userDao;

//    @Autowired
    private UserService2 userService2;

    @Transactional
    public void saveUser() {
        for (int i = 0; i < 5; i++) {
            try {
                userService2.saveUser("zk" + i);
            } catch (Exception e) {
//               throw new RuntimeException(e);
            }
        }
    }

}
