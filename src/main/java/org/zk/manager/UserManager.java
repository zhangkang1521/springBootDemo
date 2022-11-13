package org.zk.manager;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zk.dao.UserDao;
import org.zk.model.User;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;

/**
 * @author zhangkang
 * @date 2022/10/2 22:07
 */
@Component
public class UserManager {

    @Autowired
    private UserDao userDao;

    public void findByUsername(String username) {
        // 为空查询所有，最好增加notNull为true
        // 如果是分页查询，notNull可设置为false，没有这个查询条件则不查
        Example example = new Example(User.class, true, true);
        example.createCriteria().andEqualTo("username", username);
        userDao.selectByExample(example);
    }

    public void findByUsernameByWeekend(String username) {
        // 为空会报错
        Example weekendSqls = new Example.Builder(User.class)
                .where(WeekendSqls.<User>custom().andEqualTo(User::getUsername, username))
                .build();
        userDao.selectByExample(weekendSqls);
    }

    public void findByUsernameByWeekend2(String username) {
        // 为空查询所有
//        Weekend<User> weekend = new Weekend(User.class);
        Weekend<User> weekend = new Weekend(User.class, true, true);
        weekend.weekendCriteria().andEqualTo(User::getUsername, username);
        userDao.selectByExample(weekend);
    }

    public void updateByWeekend(String username) {
        User user = new User();
        user.setUsername("xx");

        // 默认传参为空会更新全表
        // 指定notNull为true，为空会报错
        Weekend<User> weekend = new Weekend(User.class);
//        Weekend<User> weekend = new Weekend(User.class, true, true);
        // username为空，safeUpdate无法拦截到
         weekend.weekendCriteria().andEqualTo(User::getUsername, username);
        userDao.updateByExampleSelective(user, weekend);
    }

    public void updateByPrimaryKey() {
        User user = new User();
        user.setId(1);
        user.setUsername("xx");
        userDao.updateByPrimaryKeySelective(user);
    }

    public void findTopByUsername(String username) {
        Weekend<User> weekend = new Weekend(User.class, true, true);
        weekend.weekendCriteria().andEqualTo(User::getUsername, username);
        List<User> list = userDao.selectByExampleAndRowBounds(weekend, new RowBounds(0, 1));
        System.out.println(list);
    }
}
