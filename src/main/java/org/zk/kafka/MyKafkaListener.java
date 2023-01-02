package org.zk.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaListener {

	@Autowired
	ConcurrentKafkaListenerContainerFactory<Object, Object> containerFactory;

	public static final Logger logger = LoggerFactory.getLogger(MyKafkaListener.class);

	@KafkaListener(topics = "demo-topic")
	public void onMessage(String text) throws Exception {
		logger.info("收到消息start：{}", text);
		Thread.sleep(1000);
		logger.info("收到消息end  ：{}", text);
	}
}
