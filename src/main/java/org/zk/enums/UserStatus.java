package org.zk.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum  UserStatus implements BaseEnum, IEnum {

	UN_AUDIT(0, "未审核"),
	AUDIT_PASS(1, "审核通过"),
	AUDIT_REJECT(-1, "审核驳回");

	UserStatus(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	@EnumValue
	private final int code;

	private final String desc;

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public Serializable getValue() {
		return code;
	}
}
