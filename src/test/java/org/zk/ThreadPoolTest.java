package org.zk;

import org.junit.Rule;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author zhangkang
 * @date 2022/5/28 19:06
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 5, 60000, TimeUnit.MILLISECONDS, new SynchronousQueue());
        for (int i = 0; i < 6; i++) {
            executor.submit(new Task());
        }
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread() + " ok");
        }
    }
}
