package org.zk.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;
import org.zk.domain.Article;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ElasticsearchTemplateTest {


	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;

	@Test
	public void save() {
		// 内部使用TransportClient进行操作
		elasticsearchTemplate.delete("zk", "article", "1");
	}

	@Test
	public void testScroll() {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(matchAllQuery())
				.withIndices("zk")
				.withTypes("article")
				.withPageable(new PageRequest(0, 1))
				.build();
		String scrollId = elasticsearchTemplate.scan(searchQuery, 1000, false);
		boolean hasRecord = true;
		int i = 0;
		while (hasRecord) {
			Page<Article> page = elasticsearchTemplate.scroll(scrollId, 5000L, Article.class);

			List<Article> list = page.getContent();
			if (!CollectionUtils.isEmpty(list)) {
				System.out.println("==== " + (++i) * list.size() +"/"+page.getTotalElements());
				for (Article article : list) {
					System.out.println(article.getTitle());
				}
				hasRecord = true;
			} else {
				hasRecord = false;
			}
		}

	}
}
