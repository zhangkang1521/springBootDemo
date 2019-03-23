package org.zk.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Created by zhangkang on 2017/8/10.
 */
@Component
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    @JmsListener(destination = "HelloWorldQueue")
    public void receiveQueue(Message text)  {
        log.info("receive start,content {}", text);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("receive end");
    }



}
