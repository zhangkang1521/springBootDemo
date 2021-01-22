package org.zk;


import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;

public class OldKafkaConsumerTest {

	@Test
	public void consume() throws Exception {
		Properties props = new Properties();
		props.put("zookeeper.connect", "10.200.4.74:2181");
		props.put("group.id", "group-1");
		props.put("zookeeper.session.timeout.ms", "30000");
		props.put("zookeeper.sync.time.ms", "2000");
		props.put("auto.commit.interval.ms", "1000");
		ConsumerConfig consumerConfig = new ConsumerConfig(props);
		ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);

		String topic = "test";
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, new Integer(1));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> kafkaStreams = consumerMap.get(topic);
		for (final KafkaStream kafkaStream : kafkaStreams) {
			ConsumerIterator<byte[], byte[]> iterator = kafkaStream.iterator();
			// https://cloud.tencent.com/developer/article/1032491
			// kafka.server.AbstractFetcherThread#processFetchRequest 使用simpleConsumer.fetch(fetchRequest)拉取消息（安装scala插件看源码）
			// kafka.consumer.ConsumerFetcherThread.processPartitionData 向阻塞队列里放数据
			while (iterator.hasNext()) {
				byte[] msgBytes = iterator.next().message();
				String message = new String(msgBytes, "utf-8");
				System.out.println(message);
			}
		}
	}
}
