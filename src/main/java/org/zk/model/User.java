package org.zk.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangkang
 * @create 2021/12/7 14:18
 */
@Data
public class User implements Serializable {

	private Integer id;

	private String username;
}
