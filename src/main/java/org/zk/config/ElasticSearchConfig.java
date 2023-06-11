package org.zk.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.mapper.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.text.SimpleDateFormat;

/**
 * @author zhangkang
 * @date 2023/3/12 17:09
 */
@Configuration
public class ElasticSearchConfig {

    // 使用spring的自动配置更好
//    @Bean
//    public RestHighLevelClient restHighLevelClient() {
//        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("localhost", 9200, "http")));
//        return restHighLevelClient;
//    }

    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate(RestHighLevelClient restHighLevelClient) {
        // TODO yyyy-MM-dd HH:mm:ss 日期格式如何转换
        return new ElasticsearchRestTemplate(restHighLevelClient);
    }
}
