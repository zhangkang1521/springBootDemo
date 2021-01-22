package org.zk;

import org.apache.kafka.clients.producer.*;
import org.junit.Test;

import java.util.Properties;

public class KafkaProducerTest {


	@Test
	public void testSend() throws Exception {
		// kafka-console-consumer.bat --topic test --from-beginning --bootstrap-server localhost:9092
		Properties props = new Properties();
		props.put("bootstrap.servers", "10.200.4.77:9092,10.200.4.78:9092,10.200.4.79:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> kafkaProducer = new KafkaProducer<>(props);

		kafkaProducer.send(new ProducerRecord<>("test", "4-2"));

		kafkaProducer.close();

		System.in.read();
	}


}
