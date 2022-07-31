package org.zk;

import lombok.Data;

/**
 * @author zhangkang
 * @date 2022/7/31 18:48
 */
@Data
public class User {

    private Long id;

    private String username;

    private Integer age1;

    private String desc;

    private String createTime;
}
