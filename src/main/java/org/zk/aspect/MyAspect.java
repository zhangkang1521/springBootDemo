package org.zk.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.zk.controller.UserController;

@Component
@Aspect
public class MyAspect {

    private static final Logger logger = LoggerFactory.getLogger(MyAspect.class);

    @Around("execution(* org.zk..*Controller.*(..))")
    public Object intoControllerLog(ProceedingJoinPoint point) throws Throwable {
        logger.info("====> aspect");
        return point.proceed();
    }

}
