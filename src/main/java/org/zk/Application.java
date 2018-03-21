package org.zk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.zk.cgb.CgbRestTemplateInterceptor;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collections;

/**
 * Created by zhangkang on 2017/1/12.
 */
@SpringBootApplication
public class Application {

    @Autowired
    CgbRestTemplateInterceptor cgbRestTemplateInterceptor;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate =  builder.build();
        SimpleClientHttpRequestFactory reqfac = new SimpleClientHttpRequestFactory();
        reqfac.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888)));
        restTemplate.setRequestFactory(reqfac);

        restTemplate.setInterceptors(Collections.singletonList(cgbRestTemplateInterceptor));

        return restTemplate;
    }
}