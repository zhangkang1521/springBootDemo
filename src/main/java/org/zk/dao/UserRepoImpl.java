package org.zk.dao;

/**
 * spring-boot版本1中，自定义接口实现名必须为"接口名+Impl"
 */
public class UserRepoImpl implements UserRepoCustom {

	@Override
	public void xx() {
		System.out.println("xx");
	}
}
