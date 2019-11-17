package org.zk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zk.dao.UserDao;
import org.zk.domain.User;

import java.util.List;

@Service
public class UserService extends ServiceImpl<UserDao, User> {

    public void saveBatch(List<User> list) {
        super.saveBatch(list);
    }
}
