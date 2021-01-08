package org.zk.service;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.zk.commons.PageParam;
import org.zk.commons.Result;
import org.zk.controller.param.LoginParam;
import org.zk.controller.param.UserParam;
import org.zk.dao.UserRepository;
import org.zk.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangkang on 2017/6/15.
 */
@Service
public class UserService {

    @Autowired
    UserService userService;

    @PersistenceContext
    private EntityManager em;

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public Result<User> login(LoginParam loginParam){
        User user = new User();
        user.setId(1L);
        user.setUsername("zk");
        return Result.success(user);
    }

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
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Transactional
    public void batchInsert(List<User> userList) {
        for(User user:userList) {
            userRepository.save(user);
        }
    }

    @Transactional
    public void read() {
//        User user = new User();
//        user.setUsername("zz");
//        user.setAge(10);
//        userRepository.save(user);
//        int count = userRepository.countAll();
//        BigDecimal sumAge = userRepository.sumAge();
//        System.out.println(count);
//        System.out.println(sumAge);

    }

    @Transactional
    public void cache() {
        userRepository.findOne(1L);
        userRepository.updateUsernameById("rr4", 1L);
        em.clear(); // 清除缓存，否则取到的是缓存中的数据
        User user = userRepository.findOne(1L);
        System.out.println(user.getUsername());
    }




    // https://zhuanlan.zhihu.com/p/148504094
    //	@Transactional
//	@Transactional(propagation = Propagation.NEVER) // 当前存在事务则抛出异常
//  @Transactional(propagation = Propagation.MANDATORY) // 当前没有事务则抛出异常
//	@Transactional(propagation = Propagation.REQUIRES_NEW) // 挂起存在的事务，创建一个新的事务，上层方法抛出异常不会影响本方法的事务提交
//	@Transactional(propagation = Propagation.NESTED) // 嵌套事务，上层方法抛出异常，本方法事务会回滚
//	@Transactional(propagation = Propagation.NOT_SUPPORTED) // 不论当前是否存在事务，本方法都会以非事务的方式运行。
//	@Transactional(propagation = Propagation.SUPPORTS) // 当前有事务则加入当前事务，没有则以非事务方式运行
    public void f2() {
        User user = new User();
        user.setUsername("f2-1");
        userRepository.save(user);
        if (true) {
            throw new RuntimeException("xxx");
        }
        User user2 = new User();
        user2.setUsername("f2-2");
        userRepository.save(user2);
    }
}
