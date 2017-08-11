package org.zk.mq;
import javax.jms.Queue;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Producer /*implements CommandLineRunner*/ {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

//    @Override
//    public void run(String... args) throws Exception {
//        send("Sample message");
//        System.out.println("Message was sent to the Queue");
//    }

    public void send(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }

    public void send2(String msg) {
        Map<String, Object> header = new HashMap<String,Object>();
        header.put("a", "AAA");
        header.put("b", "BBB");
        jmsMessagingTemplate.convertAndSend("foo", msg, header);
    }

}
