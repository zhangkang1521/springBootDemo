package org.zk.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import java.util.Enumeration;

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

    @JmsListener(destination = "foo")
    public void receiveQueue2(Message message) throws Exception{
        log.info("receive foo queue message: {}", message);
        Enumeration<?> enumeration = message.getPropertyNames();
        while(enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString();
            Object value = message.getObjectProperty(key);
            log.info("{}:{}", key, value);
        }
        log.error("xxx");
    }
}
