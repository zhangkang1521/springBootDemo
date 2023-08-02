package org.zk;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zk.dto.UserDto;

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

    @Test
    public void sendObject() {
        // 序列化对象使用 MappingJackson2MessageConverter，
        // 对象无需实现序列化接口，不存在sprig-cache-redis序列化版本问题
        UserDto userDto = new UserDto();
        userDto.setId(3L);
//        userDto.setUsername("zk");
        rocketMQTemplate.convertAndSend("demo-topic", userDto);
    }
}
