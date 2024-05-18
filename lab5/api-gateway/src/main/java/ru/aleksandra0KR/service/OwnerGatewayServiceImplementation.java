package ru.aleksandra0KR.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.aleksandra0KR.CatClient;
import ru.aleksandra0KR.OwnerClient;

@Service
public class OwnerGatewayServiceImplementation implements OwnerGatewayService {

  private final RabbitTemplate rabbitTemplate;
  private final OwnerClient ownerClient;

  public OwnerGatewayServiceImplementation(RabbitTemplate rabbitTemplate, OwnerClient ownerClient) {
    this.rabbitTemplate = rabbitTemplate;
    this.ownerClient = ownerClient;
  }
}
