package org.zk.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhangkang
 * @date 2023/6/3 15:13
 */
@Component
@Slf4j
public class UserService implements InitializingBean, DisposableBean, BeanNameAware {

    private String id;

    public UserService() {
        log.info("* 1.实例化-构造方法");
    }

    @Value("${id:100}")
    public void setId(String id) {
        log.info("* 2.依赖注入");
        this.id = id;
    }

    @PostConstruct
    public void init() {
        log.info("* 3.调用初始化方法init-method");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("* 3.调用初始化方法afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        log.info("* 4.调用销毁方法");
    }

    @Override
    public void setBeanName(String name) {
        log.info("调用aware方法");
    }
}
