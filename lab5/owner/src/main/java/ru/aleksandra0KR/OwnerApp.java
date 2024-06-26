package ru.aleksandra0KR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.aleksandra0KR.ru.rabbitMQ.RabbitConfig;


@Import({
    RabbitConfig.class
})
@SpringBootApplication
public class OwnerApp {

  public static void main(String[] args) {
    SpringApplication.run(OwnerApp.class, args);
  }
}