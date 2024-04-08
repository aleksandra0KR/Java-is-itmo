package ru.aleksandra0KR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("ru.aleksandra0KR.repository")
@EntityScan("ru.aleksandra0KR.entity")
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(Application.class, args);
    // PersonService personService = context.getBean(PersonService.class);
    // personService.addPerson("rfg", LocalDate.now());
  }
}