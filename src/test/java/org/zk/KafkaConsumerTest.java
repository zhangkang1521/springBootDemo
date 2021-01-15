package org.zk;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerTest {

	@Test
	public void testReceive() {
		// kafka-console-producer.bat --broker-list localhost:9092 --topic test
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "group-1"); // 一个消息只会通知组内的1个消费者
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		KafkaConsumer consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList("test", "demo"));
		while(true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(2000));
			for (ConsumerRecord<String, String> record : records) {
				System.out.println("收到消息：" + record.value());
			}
			consumer.commitSync();
		}
		// consumer.close();
	}
}
