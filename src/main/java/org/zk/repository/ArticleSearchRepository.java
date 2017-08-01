package org.zk.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.zk.domain.Article;

/**
 * Created by zhangkang on 2017/8/1.
 */
public interface ArticleSearchRepository extends ElasticsearchRepository<Article, Long> {
}
