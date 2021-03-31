package org.zk.interceptor;

public class AcquireRedisLockException extends RuntimeException {

	public AcquireRedisLockException() {
		this("获取锁失败，请稍后再试");
	}

	public AcquireRedisLockException(String message) {
		super(message);
	}
}
