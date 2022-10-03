package org.zk.dao;

import org.apache.ibatis.annotations.Param;
import org.zk.model.User;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zhangkang on 2017/7/26.
 */
public interface UserDao extends Mapper<User> {

    User findById(@Param("id") Integer id);

    List<User> findList();
}
