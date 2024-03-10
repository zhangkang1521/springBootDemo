package org.zk.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.query.SortQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class SearchUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final Set<String> STOP_WORDS = new HashSet<>();

    static {
        STOP_WORDS.add("is");
        STOP_WORDS.add("and");
        STOP_WORDS.add("for");
    }

    private Pattern WORD_PATTERN = Pattern.compile("[a-z]{2,}");

    private Pattern QUERY_PATTERN = Pattern.compile("([\\+\\-]?)([a-z]{2,})");

    /**
     * 将句子拆分成单词，并移除停用词
     * @param content
     * @return
     */
    public Set<String> tokenize(String content) {
        Set<String> result = new HashSet<>();
        Matcher matcher = WORD_PATTERN.matcher(content.toLowerCase());
        while (matcher.find()) {
            String word = matcher.group();
            log.info("find word {}", word);
            result.add(word);
        }
        result.removeAll(STOP_WORDS);
        return result;
    }

    /**
     * 给文档添加倒排索引，放入set中，key为idx:单词 value为docId
     * @param docId
     * @param content
     */
    public void indexDocument(String docId, String content) {
        Set<String> words = tokenize(content);
        for (String word : words) {
            redisTemplate.opsForSet().add(word, docId);
        }
    }

    /**
     * 交集
     * @param key
     * @param otherKeys
     * @return
     */
    public String intersect(String key, Collection<String> otherKeys) {
        String dest = UUID.randomUUID().toString();
        redisTemplate.opsForSet().intersectAndStore(key, otherKeys, dest);
        redisTemplate.expire(dest, 1, TimeUnit.MINUTES);
        return dest;
    }

    /**
     * 并集
     * @param key
     * @param otherKeys
     * @return
     */
    public String union(String key, Collection<String> otherKeys) {
        String dest = UUID.randomUUID().toString();
        redisTemplate.opsForSet().unionAndStore(key, otherKeys, dest);
        redisTemplate.expire(dest, 1, TimeUnit.MINUTES);
        return dest;
    }

    /**
     * 并集
     * @param key
     * @param otherKeys
     * @return
     */
    public String difference(String key, Collection<String> otherKeys) {
        String dest = UUID.randomUUID().toString();
        redisTemplate.opsForSet().differenceAndStore(key, otherKeys, dest);
        redisTemplate.expire(dest, 1, TimeUnit.MINUTES);
        return dest;
    }

    /**
     * 将查询语句解析成集合关系
     * 例如：connect +connection chat +chats -proxy -proxies
     * 解析为 (connect || connection) && (chat || chats) -proxy -proxies
     * @param query
     * @return
     */
    public SearchParsed parse(String query) {
        SearchParsed searchParsed = new SearchParsed();
        Matcher matcher = QUERY_PATTERN.matcher(query);

        List<String> union = new ArrayList<>();
        while (matcher.find()) {
            String prefix = matcher.group(1);
            String word = matcher.group(2);
            // 重新开始一个交集
            if (("".equals(prefix) || "-".equals(prefix)) && !union.isEmpty()) {
                searchParsed.getIntersects().add(union);
                union = new ArrayList<>();
            }
            if (("".equals(prefix) || "+".equals(prefix))) {
                union.add(word);
            }
            // 差集
            if ("-".equals(prefix)) {
                searchParsed.getDifference().add(word);
            }
        }

        // 最后剩余的交集
        if (!union.isEmpty()) {
            searchParsed.getIntersects().add(union);
        }
        return searchParsed;
    }

    /**
     * 解析查询表达式并搜索
     * @param query
     * @return
     */
    public String parseAndSearch(String query) {
        SearchParsed searchParsed = parse(query);
        List<List<String>> intersects = searchParsed.getIntersects();
        List<String> unionResult = new ArrayList<>();
        for (List<String> intersect : intersects) {
            String key = union(intersect.get(0), intersect.subList(1, intersect.size()));
            unionResult.add(key);
        }
        String interResult = intersect(unionResult.get(0), unionResult.subList(1, unionResult.size()));
        return difference(interResult, searchParsed.getDifference());
        //return redisTemplate.opsForSet().members(diffResult);
    }

    /**
     * 搜索并排序
     * @param query
     * @param field
     * @return
     */
    public List<String> searchAndSort(String query, String field, SortParameters.Order order) {
        String setName = parseAndSearch(query);
        // pattern错误不会报错
        return redisTemplate.sort(SortQueryBuilder.sort(setName).by("doc:*->" + field).order(order).build());
    }

    /**
     * 按zset排序
     * @param query
     * @return
     */
    public Set<String> searchAndZSort(String query, int... weights) {
        String destKey = UUID.randomUUID().toString();
        log.info("destKey {}", destKey);
        String setName = parseAndSearch(query);
        redisTemplate.opsForZSet().intersectAndStore(setName, Arrays.asList("sort:update", "sort:votes"),
                destKey, RedisZSetCommands.Aggregate.SUM, RedisZSetCommands.Weights.of(weights));
        redisTemplate.expire(destKey, 1, TimeUnit.SECONDS);
        return redisTemplate.opsForZSet().range(destKey, 0, 2);
    }

}
