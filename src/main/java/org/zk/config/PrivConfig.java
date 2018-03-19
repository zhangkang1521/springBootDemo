package org.zk.config;

/**
 * Created by zhangkang on 2018/3/19.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "priv", locations = {"classpath:priv.yml"})
public class PrivConfig {
    private String admin;

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
