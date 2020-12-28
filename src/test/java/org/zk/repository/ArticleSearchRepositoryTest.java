package org.zk.repository;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.search.MultiMatchQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;
import org.zk.domain.Article;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
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
	public void testSave() {
		Article article = new Article();
		article.setId(44L);
		article.setTitle("test");
		article.setContent("test");
		repository.save(article); // 内部使用ElasticSearchTemplate进行操作
	}

	@Test
	public void testQuery() {
		String queryString = "笑";//搜索关键字
		QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
		Iterable<Article> searchResult = repository.search(builder);
		Iterator<Article> iterator = searchResult.iterator();
		while (iterator.hasNext()) {
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
		while (iterator.hasNext()) {
			Article article = iterator.next();
			System.out.println(article);
		}
	}


}