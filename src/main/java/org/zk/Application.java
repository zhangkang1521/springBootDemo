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

    public static void main(String[] args) throws Exception {
        // https://www.cnblogs.com/markLogZhu/p/12497258.html
        // ApolloApplicationContextInitializer
        SpringApplication.run(Application.class, args);
    }
}