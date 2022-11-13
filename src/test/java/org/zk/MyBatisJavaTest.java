package org.zk;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.Environment;
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
import org.zk.model.User;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.session.Configuration;
import tk.mybatis.mapper.weekend.Weekend;

import javax.sql.DataSource;
import java.io.InputStream;

/**
 * @author zhangkang
 * @date 2022/10/3 20:23
 */
public class MyBatisJavaTest {

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

        // tk的Configuration,重写了mybatis的Configuration,重写了addMappedStatement方法
        Configuration configuration = new Configuration();
        configuration.setMapperHelper(new MapperHelper());
        Config config = new Config();
        config.setSafeDelete(true);
        config.setSafeUpdate(true);

        configuration.setConfig(config);

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
        //从刚刚创建的 sqlSessionFactory 中获取 session
        SqlSession session = sqlSessionFactory.openSession();
        // 使用tk的Configuration，可以自动处理每个MapperHelper
        // MapperHelper mapperHelper = new MapperHelper();
        // mapperHelper.processConfiguration(session.getConfiguration());


        UserDao userDao = session.getMapper(UserDao.class);
        User update = new User();
        update.setUsername("zk2");

        Weekend<User> weekend = new Weekend(User.class);
         weekend.weekendCriteria().andEqualTo(User::getUsername, "zk");
        // 全表更新
        // userDao.updateByExampleSelective(update, weekend);
//        userDao.deleteByExample(weekend);

        // userDao.selectAll();
    }


}
