package org.zk.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zk.entity.User;

import java.util.List;


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
//        System.out.println(userRepository.findAll());
        Pageable pageable = new PageRequest(0, 10);
        Page<User> page = userRepository.findAll(pageable);
        System.out.println(page.getTotalElements());
    }

    @Test
    public void findByUsername() {
        User user = userRepository.findByUsername("zk");
        System.out.println(user.getId());
        System.out.println(user.getUsername());
    }

    @Test
    public void findByUserNameOrAge() {
//        List<User> list = userRepository.findByUsernameOrAge("zk", 18);
//        List<User> list = userRepository.findByUsernameOrNickName("zk", "zy");
        User user = userRepository.findFirstByUsernameOrderByAgeDesc("test2");
        System.out.println(user.getAge());
    }

    @Test
    public void save() {
        User user = new User();
        user.setUsername("test2");
        userRepository.save(user);
    }

}