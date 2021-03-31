package org.zk.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

/**
 * redis分布式锁拦截器
 */
@Aspect
@Component
public class RedisLockInterceptor {

	private static Logger log = LoggerFactory.getLogger(RedisLockInterceptor.class);

	// 找出参数名
	private static final LocalVariableTableParameterNameDiscoverer DISCOVERER = new LocalVariableTableParameterNameDiscoverer();

	// spel表达式解析
	private static final ExpressionParser PARSER = new SpelExpressionParser();

	@Autowired
	RedissonClient redissonClient;

	private static final String KEY_PREFIX = "lvtour-invoice";

	/**
	 * 超时自动解锁
	 */
	public static final int EXPIRE_SECOND = 10;

	@Pointcut("@annotation(org.zk.interceptor.RedisLock)")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object doAround(ProceedingJoinPoint point) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		Method targetMethod = AopUtils.getMostSpecificMethod(methodSignature.getMethod(), point.getTarget().getClass());
		Object[] arguments = point.getArgs();
		log.info("进入Redis分布式锁拦截器,method:{}", targetMethod);
		RedisLock redisLock = targetMethod.getAnnotation(RedisLock.class);
		Object key = getLockKey(redisLock, targetMethod, arguments);
		Assert.notNull(key, "要锁住的资源为空，请检查@RedisLock.keySpel或传参");
		if (key instanceof Collection) {
			Assert.notEmpty((Collection) key, "要锁住的资源为空，请检查@RedisLock.keySpel或传参");
			return multiLock(point, (Collection) key);
		} else if (key instanceof String || key instanceof Number) {
			return singleLock(point, key);
		} else {
			throw new RedisLockException("key is not instance of String Number Collection");
		}
	}

	/**
	 * 锁住一个资源
	 * @param point
	 * @param key
	 * @return
	 * @throws Throwable
	 */
	private Object singleLock(ProceedingJoinPoint point, Object key) throws Throwable {
		String lockKey = addPrefixToKey(point, key);
		RLock lock = redissonClient.getLock(lockKey);
		log.info("try lock {}", lockKey);
		boolean lockSuccess =  lock.tryLock(0, EXPIRE_SECOND, TimeUnit.SECONDS);
		if(lockSuccess) {
			log.info("get lock {} success", lockKey);
			try {
				return point.proceed();
			} finally {
				lock.unlock();
				log.info("unlock {}", lockKey);
			}
		} else {
			log.info("get lock {} fail", lockKey);
			throw new AcquireRedisLockException();
		}
	}

	/**
	 * 锁住多个资源
	 * @param point
	 * @param keys
	 * @return
	 * @throws Throwable
	 */
	private Object multiLock(ProceedingJoinPoint point, Collection<Object> keys) throws Throwable {
		List<String> lockKeys = keys.stream().map(k -> addPrefixToKey(point, k)).collect(Collectors.toList());
		RLock[] locks = new RLock[lockKeys.size()];
		int i = 0;
		for (String key : lockKeys) {
			locks[i++] = redissonClient.getLock(key);
		}
		RedissonMultiLock lock = new RedissonMultiLock(locks);
		log.info("try multi lock {}", lockKeys);
		boolean lockSuccess = lock.tryLock(1, EXPIRE_SECOND, TimeUnit.SECONDS); // waitTime设置为0会导致获取不到锁，暂时设置为1s
		if (lockSuccess) {
			log.info("multi lock success {}", lockKeys);
			try {
				return point.proceed();
			} finally {
				lock.unlock();
				log.info("unlock multi {}", lockKeys);
			}
		} else {
			log.error("multi lock fail {}", lockKeys);
			throw new AcquireRedisLockException();
		}
	}

	/**
	 * 给key加上前缀，防止冲突
	 * @param point
	 * @param key
	 * @return
	 */
	private String addPrefixToKey(ProceedingJoinPoint point, Object key) {
		return KEY_PREFIX + ":" + point.getSignature().getName() + ":" +key;
	}

	/**
	 * 根据注解中的spel表达式获取参数中的属性值
	 * @param redisLock
	 * @param method
	 * @param arguments
	 * @return
	 */
	private Object getLockKey(RedisLock redisLock, Method method, Object[] arguments) {
		String keySpel = redisLock.keySpel();
		EvaluationContext context = new StandardEvaluationContext(method);
		String[] parameterNames = DISCOVERER.getParameterNames(method);
		for (int i = 0; i < parameterNames.length; i++) {
			context.setVariable(parameterNames[i], arguments[i]);
		}
		return PARSER.parseExpression(keySpel).getValue(context);
	}



}
