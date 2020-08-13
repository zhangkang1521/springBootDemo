package org.zk.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zk.domain.User;
import org.zk.enums.UserStatus;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhangkang on 2019/1/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSelect() {
        User user = userDao.selectById(1);
    }

    @Test
    public void save() {
        User user = new User();
        user.setUsername("zk");
        user.setStatus(UserStatus.AUDIT_PASS);
        userDao.insert(user);
    }

}