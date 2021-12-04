package org.zk.utils;

import java.util.Date;

/**
 * @author zhangkang
 * @create 2021/11/21 16:41
 */
public class Person {
	private String name;
	private int age;
	private Date birthday;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
