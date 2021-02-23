package org.zk;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.junit.Test;

import java.util.Properties;

public class OldKafkaProducerTest {

	@Test
	public void  send() {
		// search-plus: 10.200.5.175:9092,10.200.5.176:9092,10.200.5.177:9092
		Properties props = new Properties();
		props.put("metadata.broker.list", "10.200.4.77:9092,10.200.4.78:9092,10.200.4.79:9092");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("partitioner.class", "kafka.producer.DefaultPartitioner");
		props.put("request.required.acks", "-1");
		ProducerConfig config = new ProducerConfig(props);
		Producer kafkaProducer = new Producer<Object, Object>(config);

		for (int i = 0; i < 3; i++) {
			KeyedMessage<Object, Object> keyedMessage = new KeyedMessage<Object, Object>("test", "hello");
			kafkaProducer.send(keyedMessage);
		}
	}
}
