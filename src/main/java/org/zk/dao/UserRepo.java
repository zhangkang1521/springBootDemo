package org.zk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zk.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>, UserRepoCustom {
}
