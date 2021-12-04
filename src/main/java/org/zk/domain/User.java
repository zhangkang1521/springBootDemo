package org.zk.domain;

/**
 * @author zhangkang
 * @create 2021/11/21 17:02
 */
public class User {

	private Long id;
	private Integer age;
	private String name;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
