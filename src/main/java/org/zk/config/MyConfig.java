/**
 * 
 */
package org.zk.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.zk.service.OrderService;
import org.zk.service.UserService;
import org.zk.service.impl.OrderServiceImpl;

import javax.jms.ConnectionFactory;

@Configuration
public class MyConfig {

	private final ObjectProvider<UserService> userServiceObjectProvider;

	public MyConfig(ObjectProvider<UserService> userServiceObjectProvider) {
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		this.userServiceObjectProvider = userServiceObjectProvider;
//		UserService userService = userServiceObjectProvider.getIfUnique();
		PropertyMapper map = PropertyMapper.get();
		map.from(userServiceObjectProvider::getIfUnique).whenNonNull()
				.to(orderServiceImpl::setUserService);
		System.out.println(orderServiceImpl);
	}


}
