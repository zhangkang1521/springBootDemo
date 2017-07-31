package org.zk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zk.entity.User;

import java.util.List;

/**
 * Created by zhangkang on 2017/6/15.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUsername(String username);

    // 参数按顺序排列即可
    List<User> findByUsernameOrAge(String username, Integer age);

    // 还可以加前缀: findTop10 findFirst10
    User findFirstByUsernameOrderByAgeDesc(String username);

}
