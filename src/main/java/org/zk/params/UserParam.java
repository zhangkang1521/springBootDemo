package org.zk.params;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserParam {

	private Date birthday;

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
