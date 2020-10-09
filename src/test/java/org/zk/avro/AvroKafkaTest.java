package org.zk.avro;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.zk.dto.User;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class AvroKafkaTest {

	@Test
	public void send() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.zk.avro.AvroSerializer");

		Producer<String, User> kafkaProducer = new KafkaProducer<>(props);

		User user = new User();
		user.setName("zk");
		kafkaProducer.send(new ProducerRecord<>("test", "b", user));

		kafkaProducer.close();
	}

	@Test
	public void read() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "group-1"); // 一个消息只会通知组内的1个消费者
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.zk.avro.AvroDeserializer");
		KafkaConsumer consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList("test"));
		while(true) {
			ConsumerRecords<String, User> records = consumer.poll(Duration.ofMillis(2000));
			for (ConsumerRecord record : records) {
				System.out.println("收到消息：" + record.value());
			}
		}
	}
}
