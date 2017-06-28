package org.zk.service;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.zk.commons.PageParam;
import org.zk.controller.param.UserParam;
import org.zk.dao.UserRepository;
import org.zk.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangkang on 2017/6/15.
 */
@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public Page<User> queryPage(final PageParam<UserParam> pageParam) {
        final UserParam user = pageParam.getParam();
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = Lists.newArrayList();
                if(StringUtils.hasText(user.getUsername())) {
                    predicateList.add(cb.like(root.<String>get("username"), "%" + user.getUsername() + "%"));
                }
                if(user.getBirthdayBegin() != null) {
                    predicateList.add(cb.greaterThanOrEqualTo(root.<Date>get("birthday"), user.getBirthdayBegin()));
                }
                if(user.getBirthdayEnd() != null) {
                    predicateList.add(cb.lessThanOrEqualTo(root.<Date>get("birthday"), user.getBirthdayEnd()));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        return userRepository.findAll(spec, pageParam.getPageable());
    }



    @Transactional
    public void batchInsert(List<User> userList) {
        for(User user:userList) {
            userRepository.save(user);
        }
    }
}
