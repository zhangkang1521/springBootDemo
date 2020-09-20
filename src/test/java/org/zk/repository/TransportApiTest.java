package org.zk.repository;

import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class TransportApiTest {

	private Client client;

	private BulkProcessor bulkProcessor;

	public static final String index = "zk"; // 相当于db

	public static final String type = "article";  // 相当于表

	@Before
	public void createClient() throws Exception {
//		client = TransportClient.build()
//				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
		client =  new PreBuiltTransportClient(settings);
		((TransportClient) client)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
	}

	@After
	public void close() {
		client.close();
	}



	@Test
	public void insert() throws Exception {
		XContentBuilder article = jsonBuilder().startObject()
				.field("id", 301)
				.field("title", "hello")
				.field("abstract", "hello world")
				.field("content", "hello World")
				.field("postTime", new Date())
				.field("clickCount", 1)
				.endObject();

		// 方式1
//		IndexRequest indexRequest = new IndexRequest(index, type, "3");
//		indexRequest.source(article);
//		IndexResponse indexResponse = client.index(indexRequest).get();
//		System.out.println(indexResponse.isCreated());

		// 方式2
		IndexResponse indexResponse2 = client.prepareIndex(index, type, "301")
				.setSource(article).get();
		System.out.println(indexResponse2.getId());
	}

	@Test
	public void update() throws Exception {
		XContentBuilder updateArticle = jsonBuilder().startObject().field("title", "Hello4").endObject();
		// 方式1
//		UpdateRequest updateRequest = new UpdateRequest(index, type, "1");
//		updateRequest.doc(updateArticle);
//		client.update(updateRequest).get();
		// 方式2
		UpdateResponse updateResponse = client.prepareUpdate(index, type, "301")
				.setDoc(updateArticle).get();
		System.out.println(updateResponse);
	}

	@Test
	public void findById() {
		GetResponse getResponse = client.prepareGet(index, type, "301").get();
		System.out.println(getResponse.getSource().toString());
	}

	@Test
	public void delete() {
		DeleteResponse deleteResponse = client.prepareDelete(index, type, "301").get();
		System.out.println(deleteResponse.status());
	}

	@Test
	public void bulk() throws Exception {
		BulkRequest request = new BulkRequest();
		for (int i = 100; i < 110; i++) {
			request.add(new IndexRequest(index, type).source("{\"id\": " + i + "}", XContentType.JSON));
		}
		BulkResponse bulkResponse = client.bulk(request).get();
		System.out.println(bulkResponse.status());
	}

	@Test
	public void testBulkProcess() {
		bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
			@Override
			public void beforeBulk(long l, BulkRequest bulkRequest) {
			}

			@Override
			public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {
			}

			@Override
			public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {
				System.out.println("fail " + bulkRequest.numberOfActions());
			}
		}).setBulkActions(10)
				.setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
				.setFlushInterval(TimeValue.timeValueSeconds(5))
				.setConcurrentRequests(1)
				.setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
				.build();

		for (int i = 100; i < 111; i++) {
			bulkProcessor.add(new IndexRequest(index, type).source("{\"id\": " + i + "}", XContentType.JSON));
		}

	}


}
