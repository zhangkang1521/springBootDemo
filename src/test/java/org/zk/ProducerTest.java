package org.zk;

import com.lvmama.pub.DistributedContext;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerTest {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	public void sendCycle() {
		jmsTemplate.send("zx", new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				Message msg = session.createTextMessage("hello,zx");
				msg.setStringProperty("distributedContextJson", DistributedContext.toJSON()); //消息体中添加 distributedContextJson 信息
				return msg;
			}
		});
	}

	@Test
	public void testSend() {
		Map<String, Object> msgHeaders = new HashMap<String, Object>();
		msgHeaders.put("eventType", "DISTRIBUTOR_MODIFY_MERCHANT_ORDERID_MSG");
		jmsMessagingTemplate.convertAndSend("zx", "123", msgHeaders);
	}

	@Test
	public void testJmsTemplateSend() {
		jmsTemplate.send("ActiveMQ.FINANCE", new MessageCreator() {
			@Override
			public javax.jms.Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage("{2}");
				textMessage.setStringProperty("eventType",  "SUPPLIER_ORDER_REFUNDED_MSG");
				return textMessage;
			}
		});
	}

	@Test
	public void testSimple() throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover://(tcp://10.200.4.83:61616?wireFormat.maxInactivityDuration=0)?randomize=false");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

		Destination queue = session.createQueue("ActiveMQ.FINANCE");
		MessageProducer messageProducer = session.createProducer(queue);
		TextMessage textMessage = session.createTextMessage("hello");
		textMessage.setStringProperty("eventType",  "SUPPLIER_ORDER_PAYMENT_MSG");

		messageProducer.send(textMessage);
		session.commit();

		messageProducer.close();
		connection.close();
	}

	@Test
	public void testAutoAccount() {
		Map<String, Object> msgHeaders = new HashMap<String, Object>();
		msgHeaders.put("messageType", "ChangeVisitTime");
		jmsMessagingTemplate.convertAndSend("ActiveMQ.FINANCE.AUTO_ACCOUNTTING", "{\"orderId\":84290370,  \"visitTime\": \"2020-12-27 14:00:00\"}", msgHeaders);
	}
}
