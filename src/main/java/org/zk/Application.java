package org.zk;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.zk.mybatis.MyMapperScan;
import org.zk.mybatis.MybatisProperties;

/**
 * Created by zhangkang on 2017/1/12.
 */
@SpringBootApplication(exclude = MybatisAutoConfiguration.class)
//@MapperScan("org.zk.dao")
@MyMapperScan("org.zk.dao")
public class Application {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }


}