package org.zk.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zhangkang
 * @date 2022/8/10 16:23
 */
@Data
public class User {

    private Long id;

    private String username;

    private Date gmtCreate;
}
