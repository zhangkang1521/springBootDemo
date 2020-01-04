package org.zk.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
public class MybatisAutoConfiguration {


    private MybatisProperties mybatisProperties;

    public MybatisAutoConfiguration(MybatisProperties mybatisProperties) {
        this.mybatisProperties = mybatisProperties;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MySqlSessionFactoryBean mySqlSessionFactoryBean = new MySqlSessionFactoryBean();
        mySqlSessionFactoryBean.setDataSource(dataSource);
        mySqlSessionFactoryBean.setMapperLocations(mybatisProperties.getMapperLocations());
        return (SqlSessionFactory)mySqlSessionFactoryBean.getObject();
    }

//    @Bean
//    public MyMapperFactoryBean myMapperFactoryBean(SqlSessionFactory sqlSessionFactory) {
//        MyMapperFactoryBean myMapperFactoryBean = new MyMapperFactoryBean();
//        myMapperFactoryBean.setMapperInterface(UserDao.class);
//        myMapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
//        return null;
//    }


}
