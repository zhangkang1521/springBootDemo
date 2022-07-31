package org.zk;

import org.junit.Test;

/**
 * @author zhangkang
 * @date 2022/7/31 18:50
 */
public class MapStructTest {

    @Test
    public void test1() {
        User user = new User();
        user.setId(1L);
        user.setUsername("zk");
        user.setAge1(100);
        user.setCreateTime("2022-01-01 12:00:00");
        UserVO userVO = UserMapper.INSTANCE.toUserVo(user);
    }
}
