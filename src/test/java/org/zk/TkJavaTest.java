package org.zk;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.zk.dao.UserDao;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import javax.sql.DataSource;
import java.io.InputStream;

/**
 * @author zhangkang
 * @date 2022/10/3 20:23
 */
public class TkJavaTest {

    private SqlSessionFactory sqlSessionFactory;


    private HikariDataSource initDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/zk?serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Before
    public void sqlSessionFactory() throws Exception {
        HikariDataSource dataSource = initDataSource();

        Configuration configuration = new Configuration();

        configuration.setEnvironment(new Environment("default", new JdbcTransactionFactory(), dataSource));

        // xml配置
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("mapper/UserMapper.xml");
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(inputStream,
                configuration, "UserMapper", configuration.getSqlFragments());
        xmlMapperBuilder.parse();

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

    }

    @Test
    public void test() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.findById(1);
    }
}
