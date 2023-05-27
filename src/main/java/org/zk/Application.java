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
//@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        String str = "delete from delivery_performance_order_%04d where performance_order_status = 10;";
        for (int i = 128*3; i < 128*4; i++) {
            System.out.println(String.format(str, i));
        }
    }
}