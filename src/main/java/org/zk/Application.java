package org.zk;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Created by zhangkang on 2017/1/12.
 */
@SpringBootApplication
@EnableJms
public class Application {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}