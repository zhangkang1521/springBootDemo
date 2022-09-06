package org.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.util.List;

/**
 * @author zhangkang
 * @date 2022/9/4 19:07
 */
@Slf4j
public class RocketMqTest {

    @Test
    public void testSend() throws Exception {
        DefaultMQProducer mqProducer = new DefaultMQProducer("producer-1");
        mqProducer.setNamesrvAddr("localhost:9876");
        mqProducer.start();
        SendResult sendResult = mqProducer.send(new Message("test-topic", "hello4".getBytes()));
        System.out.println(sendResult);
    }

    @Test
    public void testReceive() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consume-1");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe("test-topic", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                log.info("收到消息：body:{} msgId:{}", new String(list.get(0).getBody()), list.get(0).getMsgId());
                // throw new RuntimeException("xxx");
                 return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.in.read();
    }
}
