package org.zk.aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Pointcut("@annotation(org.springframework.jms.annotation.JmsListener)")
	public void pointCut() {

	}

	@Before("pointCut()")
	public void before() {
		System.out.println("=== before ===");
	}
}
