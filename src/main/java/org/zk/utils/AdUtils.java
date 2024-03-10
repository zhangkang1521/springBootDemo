package org.zk.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void indexAd(String id, List<String> locations, List<String> contents, double value) {
        for (String location : locations) {
            redisTemplate.opsForSet().add("idx:req:" + location, id);
        }
        for (String content : contents) {
            redisTemplate.opsForZSet().add("idx:" + content,  id, 0);
        }
        redisTemplate.opsForZSet().add("idx:ad:value", id, value);
    }

    public String targetAds(List<String> locations, List<String> content) {
        List<String> idxLocations = locations.stream().map(s ->  "idx:req:" + s).collect(Collectors.toList());
        String unionLocations = "unionLocations";
        redisTemplate.opsForSet().unionAndStore(idxLocations.get(0), idxLocations.subList(1, idxLocations.size()), unionLocations);
        redisTemplate.opsForZSet().intersectAndStore("idx:ad:value", Arrays.asList(unionLocations), "result", RedisZSetCommands.Aggregate.SUM, RedisZSetCommands.Weights.of(1, 0));
        return "result";
    }
}
