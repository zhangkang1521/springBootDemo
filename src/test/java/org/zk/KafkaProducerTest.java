package org.zk;

import org.apache.kafka.clients.producer.*;
import org.junit.Test;

import java.util.Properties;

public class KafkaProducerTest {

	@Test
	public void testSend() {
		// kafka-console-consumer.bat --topic test --from-beginning --bootstrap-server localhost:9092
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> kafkaProducer = new KafkaProducer<>(props);

		kafkaProducer.send(new ProducerRecord<>("test", "b", "33311"));

		kafkaProducer.close();
	}
}
