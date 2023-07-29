package org.zk.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.zk.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@CacheConfig
public class UserService {


    /**
     * key: user::1
     * @param userId
     * @return
     */
    @Cacheable(cacheNames = "user")
    public User findUser(Long userId) {
        log.info("findUser from db, userId:{}", userId);
        User user = new User();
        user.setId(userId);
//        user.setSex("M");
        return user;
    }
}
