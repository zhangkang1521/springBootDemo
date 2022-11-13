package org.zk.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.spring.annotation.MapperScan;

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
@MapperScan("org.zk.dao")
public class MybatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        //tk.mybatis.mapper.session.Configuration
        tk.mybatis.mapper.session.Configuration configuration = new tk.mybatis.mapper.session.Configuration();
        //可以对 MapperHelper 进行配置后 set
        configuration.setMapperHelper(new MapperHelper());
        //设置为 tk 提供的 Configuration
        Config config = new Config();
        config.setSafeUpdate(true);
        config.setSafeDelete(true);
        configuration.setConfig(config);
        sessionFactory.setConfiguration(configuration);

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
