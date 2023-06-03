package org.zk.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author zhangkang
 * @date 2023/6/3 15:36
 */
@Component
@Slf4j
public class MyInstantiationBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    public static final String BEAN_NAME = "userService";

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (BEAN_NAME.equals(beanName)) {
            log.info("=== 实例化-前置处理 ===");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (BEAN_NAME.equals(beanName)) {
            log.info("=== 实例化-后置处理 ===");
        }
        return true;
    }

    /**
     * 实例化之后、设置属性前，例如@Auwowired
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
            throws BeansException {
        if (BEAN_NAME.equals(beanName)) {
            log.info("=== 实例化之后，设置属性前 ===");
        }
        return null;
    }
}
