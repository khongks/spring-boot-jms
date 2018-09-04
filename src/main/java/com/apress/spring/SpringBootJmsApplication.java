package com.apress.spring;

import com.apress.spring.message.Producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;

@SpringBootApplication
public class SpringBootJmsApplication {

  private static final Logger log = LoggerFactory.getLogger(Producer.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJmsApplication.class, args);
	}

	@Value("${myapp.jmsQueue}")
	String queue;

	@Value("${myapp.anotherJmsQueue}")
	String anotherQueue;

	@JmsListener(destination="${myapp.anotherJmsQueue}")    
	@SendTo("${myapp.jmsQueue}")          
	public String simplerConsumer(String message){
		log.info("Simpler Consumer> " + message);
		return message + " and Spring Messaging too!";
	}

	@Bean
	CommandLineRunner sendMessage(JmsTemplate jmsTemplate){
		 return args -> {
		 	Producer producer = new Producer(jmsTemplate);
			 producer.sendTo(queue, "Spring Boot Rocks!");
			 producer.sendTo(anotherQueue, "Spring Boot Rocks in another queue!");
		 };
	}
}
