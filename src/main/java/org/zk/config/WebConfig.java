package org.zk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.zk.domain.UserFormatter;
import org.zk.domain.MyMessageConvert;
import org.zk.domain.StringUserConvert;

import java.util.List;

/**
 * Created by Administrator on 6/15/2018.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new StringUserConvert());
        registry.addFormatter(new UserFormatter());
//        registry.addConverter(new GenericBigDecimalConverter());
//        registry.addFormatter(new UserFormatter());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(userConvert());
    }

    @Bean
    public MyMessageConvert userConvert() {
        return new MyMessageConvert();
    }

}
