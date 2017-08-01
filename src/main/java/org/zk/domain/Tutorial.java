package org.zk.domain;

import java.io.Serializable;

/**
 * 教程
 * Created by zhangkang on 2017/8/1.
 */
public class Tutorial implements Serializable{
    private static final long serialVersionUID = -1448598571336532136L;

    private Long id;
    private String name;//教程名称

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
