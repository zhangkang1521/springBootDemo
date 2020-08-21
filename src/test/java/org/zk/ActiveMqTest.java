package org.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
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
		msgHeaders.put("eventType", "SUPPLIER_ORDER_REFUNDED_MSG");
		jmsMessagingTemplate.convertAndSend("ActiveMQ.FINANCE", "{1}", msgHeaders);
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
}
