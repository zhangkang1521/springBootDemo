package org.zk;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;

/**
 * TODO
 *
 * @author zhangkang
 * @date 2022/5/28 19:12
 */
@Slf4j
public class SynchronousQueueTest {

    private static SynchronousQueue synchronousQueue = new SynchronousQueue();

    public static void main(String[] args) throws Exception {
        new Thread(new PutTask()).start();
        new Thread(new TakeTask()).start();
    }

    static class PutTask implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                try {
                    log.info("准备 put: {}", i);
                    // 被take掉才能put成功，否则一直阻塞在这里
                    synchronousQueue.put(i);
                    log.info("put: {} 成功", i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class TakeTask implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                     Object value = synchronousQueue.take();
                     log.info("take: {}", value);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
