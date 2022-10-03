package org.zk.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.zk.BaseTest;

import static org.junit.Assert.*;

public class UserDaoTest extends BaseTest {

    @Autowired
    UserDao userDao;

    @Test
    public void test1() {
        System.out.println(userDao.findById(1));
    }

    @Test
    public void test2() {
        System.out.println(userDao.selectAll());
    }
}