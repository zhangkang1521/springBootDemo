package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.zk.entity.User;

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


}
