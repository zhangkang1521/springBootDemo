package org.zk.repository;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zk.domain.Article;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by zhangkang on 2017/8/1.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleSearchRepositoryTest {
    @Autowired
    ArticleSearchRepository repository;

    @Test
    public void testSave(){
        Article article = new Article();
        article.setId(3L);
        article.setTitle("笑傲江湖");
        article.setContent("xxx");
        repository.save(article);
    }

    @Test
    public void testQuery() {
        String queryString="笑";//搜索关键字
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
        Iterable<Article> searchResult = repository.search(builder);
        Iterator<Article> iterator = searchResult.iterator();
        while(iterator.hasNext()){
            Article article = iterator.next();
            System.out.println(article);
        }
    }

}