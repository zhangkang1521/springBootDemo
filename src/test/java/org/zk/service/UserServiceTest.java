package org.zk.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zk.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testSaveBatch() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            User user = new User();
            user.setUsername("zk" + i);
            list.add(user);
        }
        userService.saveBatch(list);
    }

}