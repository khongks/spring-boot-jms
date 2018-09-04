package com.apress.spring.config;

import javax.jms.ConnectionFactory;

import com.apress.spring.message.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
public class MessagingConfig {

  private static final Logger log = LoggerFactory.getLogger(MessagingConfig.class);

  @Autowired
  private ConnectionFactory connectionFactory;

	@Value("${myapp.jmsQueue}")
  private String jmsQueue;

  @Bean
  public DefaultMessageListenerContainer messageListener() {
    DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setDestinationName(jmsQueue);
    container.setMessageListener(new Consumer());
    log.info("DefaultMessageListenerContainer created: " + jmsQueue);
    return container;
  }
}