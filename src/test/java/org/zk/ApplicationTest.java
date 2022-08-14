package org.zk;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.zk.dao.UserDao;
import org.zk.entity.User;

import java.util.List;

/**
 * Created by Administrator on 8/5/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private UserDao userDao;

    // 单测直接数据互不影响
    @Test
    @Sql(statements = "insert into tb_user(username) values('h2test3');")
    public void test1() {
        List<User> userList = userDao.listAll();
        Assert.assertEquals(3, userList.size());
    }

    @Test
    public void test2() {
        List<User> userList = userDao.listAll();
        Assert.assertEquals(2, userList.size());
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("zk");
        userDao.insert(user);
        List<User> list = userDao.listAll();
        Assert.assertEquals("zk", list.get(2).getUsername());
    }

    @Test
    public void update() {
        userDao.update("aaa", 1L);
        User user = userDao.getById(1L);
        Assert.assertEquals("aaa", user.getUsername());
    }

    @Test
    public void delete() {
        userDao.deleteById(1L);
        List<User> list = userDao.listAll();
        Assert.assertEquals(1, list.size());
    }
}
