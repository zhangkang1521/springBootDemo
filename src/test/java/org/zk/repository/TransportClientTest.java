package org.zk.repository;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class TransportClientTest {

	private Client client;

	public static final String index = "zk"; // 相当于db

	public static final String type = "article";  // 相当于表

	@Before
	public void createClient() throws Exception {
		client = TransportClient.builder().build()
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
	}

	@After
	public void close() {
		client.close();
	}



	@Test
	public void insert() throws Exception {
		XContentBuilder article = jsonBuilder().startObject()
				.field("id", 3)
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
		IndexResponse indexResponse2 = client.prepareIndex(index, type, "6")
				.setSource(article).get();
		System.out.println(indexResponse2.isCreated());
	}

	@Test
	public void update() throws Exception {
		XContentBuilder updateArticle = jsonBuilder().startObject().field("title", "Hello4").endObject();
		// 方式1
		UpdateRequest updateRequest = new UpdateRequest(index, type, "1");
		updateRequest.doc(updateArticle);
		client.update(updateRequest).get();
		// 方式2
		UpdateResponse updateResponse = client.prepareUpdate(index, type, "1")
				.setDoc(updateArticle).get();
		System.out.println(updateResponse);
	}

	@Test
	public void findById() {
		GetResponse getResponse = client.prepareGet(index, type, "1").get();
		System.out.println(getResponse.getSource().toString());
	}

	@Test
	public void delete() {
		DeleteResponse deleteResponse = client.prepareDelete(index, type, "1").get();
		System.out.println(deleteResponse.isFound());
	}


}
