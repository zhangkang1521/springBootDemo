package org.zk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zk.dao.UserRepository;
import org.zk.entity.User;

import java.util.List;

/**
 * Created by zhangkang on 2017/6/15.
 */
@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public boolean existUsername(String username) {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        User user = userRepository.findByUsername(username);
        logger.info("exist user, id:{}", user.getId());
        return user != null;
    }

    @Transactional
    public void batchInsert(List<User> userList) {
        for(User user:userList) {
            userRepository.save(user);
        }
    }
}
