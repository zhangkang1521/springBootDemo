package org.zk.mq;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.zhangkang.entity.MyMessage;
import org.zhangkang.entity.User;
import org.zk.BaseTest;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by zhangkang on 2017/8/31.
 */
public class ProducerTest extends BaseTest{

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    @Test
    public void test1(){
//        jmsTemplate.convertAndSend("aa", "what!");
        MyMessage myMessage = new MyMessage();
        myMessage.setCreateTime(new Date());
        myMessage.setContent("hello2");
        jmsMessagingTemplate.convertAndSend("user", myMessage);
        System.out.println(myMessage);
    }

    @Test
    public void testTopic() {
        MyMessage myMessage = new MyMessage();
        myMessage.setCreateTime(new Date());
        myMessage.setContent("hello2");
        jmsTemplate.convertAndSend(topic,myMessage);
    }


}