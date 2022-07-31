package org.zk;

import lombok.Data;

import java.util.Date;

/**
 * @author zhangkang
 * @date 2022/7/31 18:48
 */
@Data
public class UserVO {

    private Long id;

    private String username;

    private Integer age2;

    private String desc;

    private Date createTime;
}
