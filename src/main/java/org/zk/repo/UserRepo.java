package org.zk.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zk.dao.UserDao;
import org.zk.dto.UserDto;
import org.zk.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkang
 * @date 2023/2/19 21:03
 */
@Component
public class UserRepo {

    @Autowired
    private UserDao userDao;


    public List<UserDto> search() {
        List<User> userList = userDao.findList();
        // 下面这些写了没用，分页插件只取Executor.query返回的对象
        List<UserDto> userDtos = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDtos.add(userDto);
        }
        return userDtos;
    }
}
