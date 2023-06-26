# springBootDemo activemq

1. ActiveMQAutoConfiguration 
    创建ActiveMQConnectionFactory
2. JmsAutoConfiguration 
    2.1 JmsTemplate 
    2.2 JmsMessageTemplate 
    2.3 @Import(JmsAnnotationDrivenConfiguration.class)
        2.3.1 DefaultJmsListenerContainerFactory 
        2.3.2 DefaultJmsListenerContainerFactoryConfigurer (配置DefaultJmsListenerContainerFactory)
        2.3.3 EnableJmsConfiguration(启用注解@EnableJms)