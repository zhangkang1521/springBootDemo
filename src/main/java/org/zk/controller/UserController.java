package org.zk.controller;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zk.service.UserService;
import org.zk.service.impl.HotSwapUserService;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
@RequestMapping("/user")
public class UserController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    @Qualifier("userServiceImpl1")
    UserService userServiceImpl1;

//    @Autowired
//    @Qualifier("proxyFactoryBean")
//    UserService userService;

    @RequestMapping("/sayHello")
    public String sayHello(){
       // return userService.sayHello();
        UserService userService = (UserService)applicationContext.getBean("proxyFactoryBean");
        return userService.sayHello();
    }

    @RequestMapping("/swap")
    public String swap(String beanName){
        // ((HotSwapUserService)userService).swap(bean);
        HotSwappableTargetSource hotSwappableTargetSource = (HotSwappableTargetSource)applicationContext.getBean("hotSwappableTargetSource");

        hotSwappableTargetSource.swap(applicationContext.getBean(beanName));
        return "已经切换到"+beanName;
    }

    @Bean
    public HotSwappableTargetSource hotSwappableTargetSource() {
        return new HotSwappableTargetSource(userServiceImpl1);
    }

    @Bean
    public ProxyFactoryBean proxyFactoryBean(HotSwappableTargetSource hotSwappableTargetSource) {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTargetSource(hotSwappableTargetSource);
        return proxyFactoryBean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
