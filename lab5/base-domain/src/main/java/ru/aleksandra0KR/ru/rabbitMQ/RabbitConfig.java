package ru.aleksandra0KR.ru.rabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  @Bean
  public RabbitTemplate rabbitTemplate(
      ConnectionFactory connectionFactory,
      Jackson2JsonMessageConverter messageConverter
  ) {
    final var rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(messageConverter);
    return rabbitTemplate;
  }

  @Bean
  public Jackson2JsonMessageConverter producerJackson2MessageConverter(ObjectMapper objectMapper) {
    return new Jackson2JsonMessageConverter(objectMapper);
  }

  @Bean
  public Queue catAddQueue() {
    return new Queue("catAddQueue");
  }

  @Bean
  public Queue catUpdateQueue() {
    return new Queue("catUpdateQueue");
  }

  @Bean
  public Queue catAddFriendQueue() {
    return new Queue("catAddFriendQueue");
  }

  @Bean
  public Queue catPersonQueue() {
    return new Queue("catPersonQueue");
  }

  @Bean
  public Queue catDeleteQueue() {
    return new Queue("catDeleteQueue");
  }

  @Bean
  public Queue updatePersonQueue() {
    return new Queue("ownerUpdateQueue");
  }

  @Bean
  public Queue deletePersonQueue() {
    return new Queue("ownerDeleteQueue");
  }

  @Bean
  public Queue personAddQueue() {
    return new Queue("personAddQueue");
  }

  @Bean
  public Queue addOwnerQueue() {
    return new Queue("ownerAddQueue");
  }

}