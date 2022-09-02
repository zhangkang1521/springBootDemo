package org.zk;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkang
 * @date 2022/9/2 15:13
 */
@Slf4j
public class ParallelStreamTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.parallelStream().forEach(i -> {
            log.info("execute {} start", i);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("execute {} end", i);
        });
    }

}
