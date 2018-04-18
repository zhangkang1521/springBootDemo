package org.zk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.zk.cgb.*;
import org.zk.entity.Result;
import org.zk.entity.User;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.*;

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
    public User index(@RequestBody User u){
        User user = new User();
        user.setId(100);
        user.setUsername("张康");
//        user.setUsername(user.getUsername() + "hh");
        return user;
    }

    @GetMapping("/test")
    public void test() {
        String url = "http://www.baidu.com";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/xml; charset=GBK");
        headers.setContentType(type);
        headers.add("Accept", "application/json;charset=GBK");
        User user = new User();
        user.setUsername("张康");
        HttpEntity<User> formEntity = new HttpEntity<User>(user, headers);
        String result = restTemplate.postForObject("http://www.baidu.com", formEntity, String.class);
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

    @GetMapping("list")
    @ResponseBody
    public BEDC<User> list() {
        List<User> list = new ArrayList<>();
        for (int i=0; i<3; i++) {
            User user = new User();
            user.setId(10);
            user.setUsername("zk" + i);
            list.add(user);
        }
        BEDC<User> bedc = new BEDC<>();
        Message<User> message = new Message<>();
        CommHead commHead = new CommHead();
        bedc.setMessage(message);
        message.setCommHead(commHead);
        User user = new User();
        user.setId(100);
        user.setUsername("zk");
        message.setBody(user);
        return bedc;
    }

    @GetMapping("listTest")
    @ResponseBody
    public String test2() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity requestEntity = new HttpEntity("", headers);
        ResponseEntity<BEDC<User>> resp = restTemplate.exchange("http://localhost:9999/user/list", HttpMethod.GET, requestEntity, new ParameterizedTypeReference<BEDC<User>>(){});
        System.out.println(resp);
        return "ok";
    }





}
