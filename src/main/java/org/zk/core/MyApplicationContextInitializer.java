package org.zk.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zhangkang
 * @date 2022/8/14 10:38
 */
@Slf4j
public class MyApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        log.info("MyApplicationContextInitializer");
    }
}
