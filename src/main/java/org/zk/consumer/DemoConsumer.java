package org.zk.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangkang
 * @date 2023/1/2 20:57
 */
@RocketMQMessageListener(
        topic = "demo-topic-2",
        consumerGroup = "demo-consumer-2",
        consumeThreadNumber = 4,
        // consumeMode = ConsumeMode.ORDERLY
        selectorExpression = "xx"
)
@Slf4j
@Component
public class DemoConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String o) {
        try {
            log.info("收到消息 {} start", o);
            Thread.sleep(1000);
            log.info("收到消息 {} end", o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
