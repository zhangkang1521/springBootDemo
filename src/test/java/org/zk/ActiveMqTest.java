package org.zk;

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
public class ActiveMqTest {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	public void testSend() {
		Map<String, Object> msgHeaders = new HashMap<String, Object>();
		msgHeaders.put("eventType", "DISTRIBUTOR_MODIFY_MERCHANT_ORDERID_MSG");
		jmsMessagingTemplate.convertAndSend("ActiveMQ.FINANCE", "123", msgHeaders);
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
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
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
}
