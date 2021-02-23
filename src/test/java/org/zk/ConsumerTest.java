package org.zk;

import com.lvmama.pub.DistributedContext;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

public class ConsumerTest {

	@Test
	public void receive() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		context.start();

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover://(tcp://10.200.4.83:61616?wireFormat.maxInactivityDuration=0)?randomize=false");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("zx");
		MessageConsumer consumer = session.createConsumer(destination);
		while(true) {
			TextMessage msg = (TextMessage) consumer.receive(1000);
			if (msg != null) {
				DistributedContext.putToContext(DistributedContext.toObject(msg.getStringProperty("distributedContextJson")));
				System.out.println("收到消息：" + msg.getText());
			}
		}
//		session.commit();
//		session.close();
//		connection.close();
	}
}
