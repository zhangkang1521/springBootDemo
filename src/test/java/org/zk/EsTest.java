package org.zk;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zk.doc.UserDoc;

import java.util.Date;

/**
 * @author zhangkang
 * @date 2023/3/12 17:10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EsTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void insert() throws Exception {
        UserDoc userDoc = new UserDoc();
        userDoc.setUserId(1L);
        // userDoc.setUsername("zk5");
        userDoc.setBirthday(new Date());
        IndexRequest indexRequest = new IndexRequest("user");
        // 时间转换问题
        // System.out.println(BeanUtil.beanToMap(userDoc));
        // indexRequest.source(BeanUtil.beanToMap(userDoc), XContentType.JSON);

        // 空字段显示为空，日期类型@JSONField注解
        indexRequest.source(JSON.toJSONString(userDoc, SerializerFeature.WriteMapNullValue), XContentType.JSON);
        indexRequest.id(userDoc.getUserId().toString());
        // 文档不存在则创建，存在会报错
        indexRequest.create(true);
        indexRequest.type("_doc");
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @Test
    public void update() throws Exception {
        UserDoc userDoc = new UserDoc();
        userDoc.setUserId(1L);
        userDoc.setUsername("zk1");
        userDoc.setBirthday(new Date());

        UpdateRequest request = new UpdateRequest("user", "_doc", "1");

        // 存在，执行doc；不存在，执行upsert;
        request.doc(JSON.toJSONString(userDoc, SerializerFeature.WriteMapNullValue), XContentType.JSON);
        request.docAsUpsert();
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
    }

    @Test
    public void fastJSON() {
        UserDoc userDoc = new UserDoc();
        userDoc.setUserId(8L);
        // userDoc.setUsername("zk5");
        userDoc.setBirthday(new Date());
        System.out.println(JSON.toJSONString(userDoc));
        System.out.println(JSON.toJSONString(userDoc, SerializerFeature.WriteMapNullValue));
    }
}
