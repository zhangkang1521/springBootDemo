package org.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Created by zhangkang on 2017/6/15.
 */
@SpringBootApplication
@EnableAutoConfiguration //TODO　这个注解不用也行？
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
