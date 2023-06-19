package org.zk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * Created by zhangkang on 2017/1/12.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {


        //com.ctrip.framework.apollo.spring.boot.ApolloApplicationContextInitializer
        // 往enviroment加入 ApolloBootstrapPropertySources ApolloPropertySources

        // 自动配置
        //com.ctrip.framework.apollo.spring.boot.ApolloAutoConfiguration

        SpringApplication.run(Application.class, args);

        System.in.read();
    }

    @Bean
    public CommandLineRunner runner(Environment enviroment) {
        // configurationProperties
        // commandLindArgs 命令行参数
        // systemProperties 系统属性(spring)
        // systemEnviroment 系统环境变量(spring)
        // random 随机数
        // application.properties
        // demo.properties @PropertySource
        return (String... args) -> {
            System.out.println(enviroment);
        };
    }
}