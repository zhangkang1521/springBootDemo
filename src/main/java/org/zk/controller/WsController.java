package org.zk.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.zk.domain.MyMessage;
import org.zk.domain.MyResponse;

/**
 * Created by Administrator on 4/24/2018.
 */
@Controller
public class WsController {



    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public MyResponse say(MyMessage myMessage) {
        return new MyResponse("welcome:" + myMessage.getName());
    }
}
