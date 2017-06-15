package org.zk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zk.entity.User;

/**
 * Created by zhangkang on 2017/6/15.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
