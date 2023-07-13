package org.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangkang on 2017/1/12.
 */
@SpringBootApplication
public class Application {

    static {
        // 先启动dashboard => java -Dserver.port=8080 -jar sentinel-dashboard-1.7.2.jar
        System.setProperty("csp.sentinel.dashboard.server", "localhost:8080");
        System.setProperty("project.name", "spring-boot-demo");
    }

    public static void main(String[] args) throws Exception {
        // -Dcsp.sentinel.dashboard.server=localhost:8080
        SpringApplication.run(Application.class, args);
    }
}