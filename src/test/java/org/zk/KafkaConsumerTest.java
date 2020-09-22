package org.zk;

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
		props.put("group.id", "test");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList("test"));
		while(true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(2000));
			for (ConsumerRecord<String, String> record : records) {
				System.out.println("收到消息：" + record.value());
			}
		}
		// consumer.close();
	}
}
