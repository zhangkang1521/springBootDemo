package org.zk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangkang
 * @date 2022/8/14 14:53
 */
@Data
@Component
@ConfigurationProperties(prefix = "test")
public class DemoConfig {

    private String aa;
}
