# springBootDemo

自动配置类 RocketMQAutoConfiguration
  引入 ListenerContainerConfiguration => 扫描注解@RocketMQMessageListener注入DefaultRocketMQListenerContainer
  注入 DefaultMQProducer
  注入 RocketMQTemplate