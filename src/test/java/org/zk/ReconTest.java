package org.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zk.service.recon.ReconProject;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @author zhangkang
 * @date 2022/8/7 17:29
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ReconTest {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void test() throws Exception {
        ReconProject reconProject = new ReconProject();
        reconProject.setATableName("a1");
        reconProject.setBTableName("b1");
        reconProject.setAKey("order_no");
        reconProject.setBKey("biz_no");
        reconProject.setAAmount("amt");
        reconProject.setBAmount("biz_amt");

        SqlSession sqlSession = sqlSessionFactory.openSession();
        Connection connection = sqlSession.getConnection();
        SqlRunner sqlRunner = new SqlRunner(connection);
        String aSql = new SQL().SELECT("*").FROM(reconProject.getATableName()).toString();
        List<Map<String, Object>> aList = sqlRunner.selectAll(aSql);
        for (Map<String, Object> aMap : aList) {
            Object orderNo = aMap.get(reconProject.getAKey().toUpperCase());
            String bSql = new SQL()
                    .SELECT("*")
                    .FROM(reconProject.getBTableName())
                    .WHERE(reconProject.getBKey() + " = ?").toString();
            List<Map<String, Object>> bList = sqlRunner.selectAll(bSql, orderNo);
            if (bList.size() == 1) {
                Map<String, Object> bMap = bList.get(0);
                Object aAmount = aMap.get(reconProject.getAAmount().toUpperCase());
                Object bAmount = bMap.get(reconProject.getBAmount().toUpperCase());
                if (aAmount.equals(bAmount)) {
                    log.info("{} 对平", orderNo);
                } else {
                    log.info("{} 差异", orderNo);
                }
            } else if (bList.size() > 1) {
                // 汇总
            } else if (bList.size() == 0) {
                log.info("{} a方单边", orderNo);
            }
        }
        // b方数据为空的设置为单边


    }
}
