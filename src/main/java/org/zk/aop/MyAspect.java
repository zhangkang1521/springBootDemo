package org.zk.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 8/23/2018.
 */
@Aspect
@Component
public class MyAspect {

    @Before("execution(public * org.zk.aop.*.*())")
    public void before() {
        System.out.println("=== before ===");
    }
}
