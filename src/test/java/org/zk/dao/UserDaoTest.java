package org.zk.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author zhangkang
 * @create 2021/12/7 14:24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDaoTest {

	@Autowired
	UserDao userDao;

	@Test
	public void findById() {
		userDao.findById(1);
	}
}