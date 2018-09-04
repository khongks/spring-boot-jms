package com.apress.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="myapp")
public class ConfigProperties {
  
  private String jmsQueue;

  public String getJmsQueue() {
    return this.jmsQueue;
  }
  
  public void setJmsQueue(String queue) {
    this.jmsQueue = queue;
  }
}