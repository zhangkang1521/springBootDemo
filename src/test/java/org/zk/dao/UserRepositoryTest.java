package org.zk.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by zhangkang on 2017/6/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(App.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findAll(){
        System.out.println(userRepository.findAll());
    }

}