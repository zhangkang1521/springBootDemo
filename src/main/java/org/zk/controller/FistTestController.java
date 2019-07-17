package org.zk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangkang on 2019/6/6.
 */
@RestController
@RequestMapping("/fish")
public class FistTestController {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("send")
    public void test() {
        Thread t1 = new Thread(new Send());
        Thread t2 = new Thread(new Send());
        t1.start();
        t2.start();
    }

    class Send implements Runnable {

        @Override
        public void run() {
            Map<String, Object> msgHeaders = new HashMap<String, Object>();
            msgHeaders.put("eventType", "PASSCODE_APPLY_NOTIFY");
            String str = "2024971916";
            jmsMessagingTemplate.convertAndSend("ActiveMQ.FINANCE.FISH", str, msgHeaders);
        }
    }
}
