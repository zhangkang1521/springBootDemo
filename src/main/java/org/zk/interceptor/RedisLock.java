package org.zk.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisLock {

	/**
	 * 根据SpEL表达式获取key，例如#orderParam.orderIds，属性只能为Collection, String, Number(包括Long,Integer等)
	 * @return
	 */
	String keySpel();
}
