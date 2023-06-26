package org.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by zhangkang on 2017/1/12.
 */
@SpringBootApplication
//@EnableJms (可以不用配置，会自动配置，参考JmsAnnotationDrivenConfiguration)
public class Application {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}