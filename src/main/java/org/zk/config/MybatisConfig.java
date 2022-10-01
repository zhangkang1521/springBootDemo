package org.zk.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.zk.dao.UserDao;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangkang
 * @date 2022/10/1 21:59
 */
@Configuration
public class MybatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        // 这种是错误的
//        sessionFactory.setMapperLocations(new Resource[]{new ClassPathResource("mapper/*.xml")});
        sessionFactory.setMapperLocations(resolveMapperLocations("mapper/*.xml"));
        return sessionFactory;
    }


    public Resource[] resolveMapperLocations(String mapperLocation) {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<Resource> resources = new ArrayList<Resource>();
        try {
            Resource[] mappers = resourceResolver.getResources(mapperLocation);
            resources.addAll(Arrays.asList(mappers));
        } catch (IOException e) {
            // ignore
        }
        return resources.toArray(new Resource[resources.size()]);
    }

}
