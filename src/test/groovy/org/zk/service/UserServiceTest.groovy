package org.zk.service

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.zk.dao.UserDao
import org.zk.domain.User
import spock.lang.Specification

/**
 * https://blog.csdn.net/qq_15060345/article/details/100791354
 * @author zhangkang
 * @create 2021/11/21 17:05
 */
@SpringBootTest
@ContextConfiguration(classes = UserService.class)
class UserServiceTest extends Specification {

    @Autowired
    UserService userService

    @SpringBean
    UserDao userDao = Stub()

//    @Autowired
//    UserController userController

    def "test addAge1"() {
        given:
        User user1 = new User(age: 19)

        and:
        userDao.findById(1L) >> user1

        when:
        userService.addAge(1L)

        then:
        println("spock 执行了")
        user1.getAge() == 20
    }

}
