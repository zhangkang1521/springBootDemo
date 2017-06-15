package org.zk.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zk.entity.User;


/**
 * Created by zhangkang on 2017/6/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findAll(){
        System.out.println(userRepository.findAll());
    }

    @Test
    public void findByUsername() {
        User user = userRepository.findByUsername("zk");
        System.out.println(user.getId());
        System.out.println(user.getUsername());
    }

}