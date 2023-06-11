package org.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.zk.doc.UserDoc;

/**
 * @author zhangkang
 * @date 2023/6/11 15:53
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchRestTemplateTest {


    @Autowired
    private ElasticsearchRestTemplate template;

    @Test
    public void index() {
        GetQuery getQuery = new GetQuery();
        getQuery.setId("2");
        UserDoc user = template.queryForObject(getQuery, UserDoc.class);
        System.out.println(user);
    }
}
