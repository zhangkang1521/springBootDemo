package org.zk;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zk.doc.UserDoc;

import java.util.List;

/**
 * @author zhangkang
 * @date 2023/6/10 16:40
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RestClientTest {

    @Autowired
    private RestClient restClient;

    @Test
    public void sql() throws Exception {
        Request request = new Request("GET", "/_sql");
        request.setJsonEntity("{\"query\":\"SELECT userId, username FROM user \"}");
        Response response = restClient.performRequest(request);
        String responseBody = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = JSON.parseObject(responseBody);
        JSONArray jsonArray =  (JSONArray) jsonObject.get("rows");

        for (Object list : jsonArray) {
            UserDoc userDoc = new UserDoc();

            ReflectUtil.setFieldValue(userDoc, "userId", ((JSONArray)list).get(0));
            ReflectUtil.setFieldValue(userDoc, "username", ((JSONArray)list).get(1));
            System.out.println(userDoc);
        }

    }
}
