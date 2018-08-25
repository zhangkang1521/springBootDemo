package org.zk.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 8/23/2018.
 */
@Component
public class MyTargetImpl implements MyTarget {

    final public void sayHello() {
        System.out.println("hello");
//        this.sayWorld();
        ((MyTarget) AopContext.currentProxy()).sayWorld();
    }

    public void sayWorld() {
        System.out.println("sayWorld");
    }
}
