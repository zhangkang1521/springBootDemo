package org.zk;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.*;

/**
 * Created by Administrator on 8/5/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {
    public static void main(String[] args) throws Exception{
        String url = "failover://tcp://localhost:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("HelloWorldQueue");
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 10; i++) {
            TextMessage textMessage1 = session.createTextMessage("" + i);
            producer.send(textMessage1);
        }

        session.close();
        connection.close();
    }
}
