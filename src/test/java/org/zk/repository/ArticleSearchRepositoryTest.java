package org.zk.repository;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.search.MultiMatchQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
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
        article.setId(44L);
        article.setTitle("射雕英雄传");
        article.setContent("射雕英雄传xx");
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

    @Test
    public void testQueryField() {
        QueryBuilder qb = QueryBuilders.termsQuery("id", new Integer[]{3, 4});
        System.out.println(qb);
        Iterable<Article> searchResult = repository.search(qb);
        Iterator<Article> iterator = searchResult.iterator();
        while(iterator.hasNext()){
            Article article = iterator.next();
            System.out.println(article);
        }
    }

}