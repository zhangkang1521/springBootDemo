package org.zk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Autowired
	ServletContext servletContext;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.host("www.zk.com/sys/order")
				.pathProvider(new RelativePathProvider(servletContext) {
					@Override
					public String getApplicationBasePath() {
						return "/";
					}
				})
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.zk.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring Boot项目swagger API")
				.version("1.0")
				.build();
	}
}
