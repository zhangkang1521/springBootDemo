package org.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 8/5/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {

    @Autowired
    private JavaMailSender mailSender;


    @Test
    public void send(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("hello");
        message.setText("dear Jane: xxx");
        message.setTo("zhangkang@lvmama.com");
        message.setFrom("zhangkang_test@163.com");
        mailSender.send(message);
    }
}
