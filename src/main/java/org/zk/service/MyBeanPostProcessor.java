package org.zk.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author zhangkang
 * @date 2023/6/3 15:52
 */
@Component
@Slf4j
public class MyBeanPostProcessor implements BeanPostProcessor {

    public static final String BEAN_NAME = "userService";

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (BEAN_NAME.equals(beanName)) {
            log.info("=== 初始化-前置处理 ===");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (BEAN_NAME.equals(beanName)) {
            log.info("=== 初始化-后置处理 ===");
        }
        return bean;
    }
}
