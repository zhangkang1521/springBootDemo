package org.zk.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaListener {

	public static final Logger logger = LoggerFactory.getLogger(MyKafkaListener.class);

//	@KafkaListener(topics = "test")
	public void onMessage(String text) {
		logger.info("收到消息：{}", text);
	}
}
