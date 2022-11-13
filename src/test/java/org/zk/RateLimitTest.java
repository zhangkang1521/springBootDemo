package org.zk;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.Date;

/**
 * @author zhangkang
 * @date 2022/11/13 9:44
 */
public class RateLimitTest {

    @Test
    public void test() {
        RateLimiter rateLimiter = RateLimiter.create(2);
        for (int i = 0; i < 20; i++) {
            rateLimiter.acquire();
            System.out.println(new Date());
        }
    }
}
