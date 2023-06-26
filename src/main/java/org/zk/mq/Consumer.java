package org.zk.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhangkang on 2017/8/10.
 */
@Component
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    private List<TextMessage> list =  Collections.synchronizedList(new ArrayList<TextMessage>());

    public Consumer() {
        System.out.println("ok");
    }

    @JmsListener(destination = "demo-queue")
    public void onMessage(Message msg)  throws Exception {
        log.info("message arrive: {}", msg);
        Thread.sleep(500);
    }
}
