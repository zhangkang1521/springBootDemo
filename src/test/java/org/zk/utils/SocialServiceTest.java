package org.zk.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SocialServiceTest {

    @Autowired
    private SocialService socialService;

    @Test
    public void createUser() {
        socialService.createUser("zk");
        socialService.createUser("zx");

    }

    @Test
    public void follow() {
        socialService.follow("1", "2");
    }

    @Test
    public void sendMsg() {

        socialService.sendMsg("2", "word3");
        // socialService.sendMsg("2", "word2");

    }


}