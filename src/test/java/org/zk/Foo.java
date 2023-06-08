package org.zk;


import javax.validation.constraints.NotBlank;

/**
 * @author zhangkang
 * @date 2023/5/14 20:14
 */
public class Foo {

    @NotBlank(message = "id不能为空")
    private String id;
}
