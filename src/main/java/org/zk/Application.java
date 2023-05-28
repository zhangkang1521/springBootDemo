package org.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangkang on 2017/1/12.
 */
@SpringBootApplication
@ComponentScan(
        basePackages = "org.zk",
        excludeFilters =
                {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "^org\\.zk\\.service\\..*$")}
)
public class Application {

    /**
     * @param args
     * @throws Exception
     * @see org.springframework.core.type.filter.RegexPatternTypeFilter
     * @see org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider#isCandidateComponent(org.springframework.core.type.classreading.MetadataReader)
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}