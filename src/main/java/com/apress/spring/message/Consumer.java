package com.apress.spring.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer implements MessageListener {
  private static final Logger log = LoggerFactory.getLogger(Consumer.class);

  @Override
  public void onMessage(Message message) {
    try {
      ActiveMQTextMessage textMessage = (ActiveMQTextMessage)message;
      log.info("Consumer> " + textMessage.getText());
    } catch(JMSException jmse) {
      jmse.printStackTrace();
    }
  }
}