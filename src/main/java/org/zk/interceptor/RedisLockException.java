package org.zk.interceptor;

public class RedisLockException extends RuntimeException {

	public RedisLockException() {
	}

	public RedisLockException(String message) {
		super(message);
	}
}
