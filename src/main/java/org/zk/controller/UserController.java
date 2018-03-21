package org.zk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.zk.cgb.*;
import org.zk.entity.User;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/1")
    @ResponseBody
    public User index(@RequestBody User user){
//        User user = new User();
//        user.setId(100);
//        user.setUsername("zk");
        user.setUsername(user.getUsername() + "hh");
        return user;
    }

    @GetMapping("/test")
    public void test() {
        String url = "http://localhost:9999/user/1";
//        ResponseEntity<User> resp = restTemplate.getForEntity("http://localhost:9999/user/1", User.class);
//        System.out.println(resp);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        headers.setContentType(MediaType.APPLICATION_XML);
        User user = new User();
        user.setId(100);
        user.setUsername("zk");
        HttpEntity<User> requestEntity = new HttpEntity<User>(user, headers);

        ResponseEntity<User> resp = restTemplate.exchange(url, HttpMethod.POST, requestEntity, User.class);
        System.out.println(resp);
    }

    @GetMapping("/cgb")
    @ResponseBody
    public String testCgb() {
        String url = "http://10.113.1.58:9528/CGBClient/BankAction";

        BEDC bedc = new BEDC();
        Message<BalanceReq> message = new Message<BalanceReq>();
        CommHead commHead = CommHead.buildRequestCommHead("0001");
        message.setCommHead(commHead);
        BalanceReq balanceReq = new BalanceReq();
        balanceReq.setAccount("9550880200382500210");
        message.setBody(balanceReq);
        bedc.setMessage(message);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<BEDC> requestEntity = new HttpEntity<BEDC>(bedc, headers);



        ResponseEntity<BEDC> resp = restTemplate.exchange(url, HttpMethod.POST, requestEntity, BEDC.class);
        System.out.println(resp.getBody().getMessage().getBody());
        return "ok";
    }




}
