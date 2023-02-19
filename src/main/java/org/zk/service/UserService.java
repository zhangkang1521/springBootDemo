package org.zk.service;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zk.dao.UserDao;
import org.zk.model.User;
import org.zk.repo.UserRepo;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public void saveUser() {

    }

    public void search() {
//        PageHelper.startPage(1, 10);
//        List<User> list = userDao.findList();
        PageInfo pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                userRepo.search();
            }
        });
    }

}
