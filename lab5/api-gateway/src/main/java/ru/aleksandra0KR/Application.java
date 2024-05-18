package ru.aleksandra0KR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.aleksandra0KR.config.CatClientConfig;
import ru.aleksandra0KR.config.OwnerClientConfig;
import ru.aleksandra0KR.ru.rabbitMQ.RabbitConfig;
import ru.aleksandra0KR.ru.security.SecurityConfig;

@Import({
    SecurityConfig.class,
    RabbitConfig.class,
    OwnerClientConfig.class,
    CatClientConfig.class
})
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}