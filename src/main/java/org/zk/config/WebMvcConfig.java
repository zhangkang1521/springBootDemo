package org.zk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


	@Bean(name = "localeResolver")
	public LocaleResolver localeResolver() {
		// 默认使用AcceptHeaderLocaleResolver
		return new SessionLocaleResolver();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		// http://localhost:9999/?locale=zh_CN
		registry.addInterceptor(new LocaleChangeInterceptor());
	}
}
