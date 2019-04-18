package org.zk.service;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zk.entity.User;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhangkang on 2017/6/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;



    @Test
    public void batchInsert() {
        List<User> userList = Lists.newArrayList();
        User user1 = new User();
        user1.setUsername("aaa");
        User user2 = new User();
        user2.setUsername("111");
        userList.add(user1);
        userList.add(user2);
        userService.batchInsert(userList);
    }

    /**
     * 验证自己写入的东西，能读到
     */
    @Test
    public void testRead() {
        userService.read();
    }

    @Test
    public void testTran() {
        userService.testTran();
    }

}