package org.zk;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 8/5/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRocketMqTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void send() {
        rocketMQTemplate.convertAndSend("demo-topic", "hello");
    }
}
