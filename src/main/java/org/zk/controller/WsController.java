package org.zk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.zk.domain.MyMessage;
import org.zk.domain.MyResponse;

import java.security.Principal;

/**
 * Created by Administrator on 4/24/2018.
 */
@Controller
public class WsController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // 聊天室
    @MessageMapping("/chat")
    public void handleChat(Principal principal, MyMessage message) {
        if ("zk".equals(principal.getName())) {
            messagingTemplate.convertAndSendToUser("zy", "/queue/notifications", message.getName());
        } else {
            messagingTemplate.convertAndSendToUser("zk", "/queue/notifications", message.getName());
        }
    }


    // 广播
    @MessageMapping("/welcome") // 接收
    @SendTo("/topic/getResponse") // 发送
    public MyResponse say(MyMessage myMessage) {
        return new MyResponse("welcome:" + myMessage.getName());
    }
}
