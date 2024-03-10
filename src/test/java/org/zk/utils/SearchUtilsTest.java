package org.zk.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchUtilsTest {

    @Autowired
    SearchUtils searchUtils;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void tokenize() {
        Set<String> set = searchUtils.tokenize("she is beautiful and cute");
    }

    @Test
    public void indexDocument() {
        searchUtils.indexDocument("1", "connect");
        searchUtils.indexDocument("2", "connection chat");
        searchUtils.indexDocument("3", "connection chat proxy");
        searchUtils.indexDocument("4", "chat");
        searchUtils.indexDocument("5", "connect chat");
    }

    @Test
    public void parseAndSearch() {
        String setName = searchUtils.parseAndSearch("connect +connection chat +chats -proxy -proxies");
        System.out.println(redisTemplate.opsForSet().members(setName));
    }

    @Test
    public void intersect() {
        String a = searchUtils.union("idx:connect", Arrays.asList("idx:connection"));
        System.out.println(redisTemplate.opsForSet().members(a)); // 1 2 3
        String b = searchUtils.intersect(a, Arrays.asList("idx:chat"));
        System.out.println(redisTemplate.opsForSet().members(b)); // 2 3
        String c = searchUtils.difference(b, Arrays.asList("idx:proxy"));
        System.out.println(redisTemplate.opsForSet().members(c)); // 2
    }

    @Test
    public void parse() {
        SearchParsed searchParsed = searchUtils.parse("connect +connection chat +chats -proxy -proxies");
    }

    @Test
    public void searchAndSort() {
        redisTemplate.opsForHash().put("doc:" + 1, "created", "99");
        redisTemplate.opsForHash().put("doc:" + 2, "created", "30");
        redisTemplate.opsForHash().put("doc:" + 3, "created", "60");
        redisTemplate.opsForHash().put("doc:" + 5, "created", "0");

        redisTemplate.opsForHash().put("doc:" + 1, "updated", "89");
        redisTemplate.opsForHash().put("doc:" + 2, "updated", "1000");
        redisTemplate.opsForHash().put("doc:" + 3, "updated", "32");
        redisTemplate.opsForHash().put("doc:" + 5, "updated", "64");

        // 根据hash指定字段排序
        List<String> list = searchUtils.searchAndSort("connect +connection", "updated", SortParameters.Order.ASC);

        System.out.println(list);
    }

    @Test
    public void searchAndZSort() {
        Set<String> set = searchUtils.searchAndZSort("chat", 0, 0, 1);
        System.out.println(set);
    }





}