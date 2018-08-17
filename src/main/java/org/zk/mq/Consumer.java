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

    @JmsListener(destination = "sample.queue")
    public void receiveQueue(String text) {
        System.out.println("consumer received: "+text);
    }


//    @JmsListener(destination = "foo")
    @JmsListeners ( value=
            {@JmsListener(destination =  "foo"),
            @JmsListener(destination = "foo2")}
    )
    public void receiveQueue2(Message message) throws Exception {
        Thread.sleep(500);
        if (message instanceof TextMessage) {
            log.info("receive text message: {}", ((TextMessage) message).getText());
        }
//        Enumeration<?> enumeration = message.getPropertyNames();
//        while(enumeration.hasMoreElements()) {
//            String key = enumeration.nextElement().toString();
//            Object value = message.getObjectProperty(key);
//            log.info("{}:{}", key, value);
//        }
    }
}
