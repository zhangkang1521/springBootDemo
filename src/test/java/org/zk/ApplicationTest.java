package org.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zk.dao.UserDao;
import org.zk.entity.User;

import java.util.List;

/**
 * Created by Administrator on 8/5/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void getById() {
       User user = userDao.getById(1L);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("h2-test-3");
        userDao.insert(user);
        List<User> userList = userDao.listByUsername("h2-test-3");
    }
}
