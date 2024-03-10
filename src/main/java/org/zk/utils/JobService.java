package org.zk.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class JobService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void indexJob(String jobId, String... skills) {
        for (String skill : skills) {
            redisTemplate.opsForSet().add(skill, jobId);
        }
        redisTemplate.opsForZSet().add("jobs:req", jobId, skills.length);
    }

    public void searchJob(List<String> skills) {
       // zunionStore 存储每个job的分数
        redisTemplate.opsForZSet().unionAndStore(skills.get(0), skills.subList(1, skills.size()), "temp", RedisZSetCommands.Aggregate.SUM, RedisZSetCommands.Weights.of(1, 1));
        // 分数为0的满足
        redisTemplate.opsForZSet().intersectAndStore("temp", Arrays.asList("jobs:req"), "temp2", RedisZSetCommands.Aggregate.SUM, RedisZSetCommands.Weights.of(-1, 1));

        Set<String> jobs = redisTemplate.opsForZSet().rangeByScore("temp2", 0, 0);
        System.out.println(jobs);
    }
}
