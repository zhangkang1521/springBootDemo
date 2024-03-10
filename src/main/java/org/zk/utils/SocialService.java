package org.zk.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class SocialService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void createUser(String name) {
        Long id = redisTemplate.opsForValue().increment("user:id");
        Map<String, Object> map = new HashMap<>();
        map.put("id", id+"");
        map.put("name", name);
        map.put("signup", System.currentTimeMillis()+"");
        redisTemplate.opsForHash().putAll(String.format("user:%d", id), map);
    }

    public void sendMsg(String userId, String msg) {
        Long id = redisTemplate.opsForValue().increment("status:id");
        Map<String, Object> map = new HashMap<>();
        map.put("id", id+"");
        map.put("msg", msg);
        map.put("userId", userId);
        redisTemplate.opsForHash().putAll(String.format("status:%d", id), map);

        long now = System.currentTimeMillis();
        redisTemplate.opsForZSet().add("profile:" + userId, id+"", now);

        // 更新我的粉丝主页：遍历我的粉丝，将这条消息加入home，然后裁剪
        Set<String> fans = redisTemplate.opsForSet().members("fans:" + userId);
        for (String fan : fans) {
            redisTemplate.opsForZSet().add("home:" + fan, id+"", now);
        }
    }

    public void delMsg(String userId, String id) {
        redisTemplate.opsForHash().delete(String.format("status:%d", id));

        long now = System.currentTimeMillis();
        redisTemplate.opsForZSet().remove("profile:" + userId, id+"");

        // 更新我的粉丝主页：遍历我的粉丝，将这条消息从home删除
        Set<String> fans = redisTemplate.opsForSet().members("fans:" + userId);
        for (String fan : fans) {
            redisTemplate.opsForZSet().remove("home:" + fan, id+"");
        }
    }

    public void follow(String userId, String otherUserId) {
        redisTemplate.opsForSet().add("following:" + userId, otherUserId);
        redisTemplate.opsForSet().add("fans:" + otherUserId, userId);

        // 更新我的主页：将otherUserId的profile加入home，然后裁剪
        Set<ZSetOperations.TypedTuple<String>> profiles = redisTemplate.opsForZSet().reverseRangeWithScores("profile:" + otherUserId, 0, 10);
        for (ZSetOperations.TypedTuple<String> profile:profiles) {
            redisTemplate.opsForZSet().add("home:" + userId, profiles);
        }

    }

    public void unFollow(String userId, String otherUserId) {
        redisTemplate.opsForSet().add("following:" + userId, otherUserId);
        redisTemplate.opsForSet().add("fans:" + otherUserId, userId);

        // 更新我的主页：将otherUserId的profile删除
        Set<String> profiles = redisTemplate.opsForZSet().reverseRange("profile:" + otherUserId, 0, 10);
        for (String profile:profiles) {
            redisTemplate.opsForZSet().remove("home:" + userId, profile);
        }

    }



}
