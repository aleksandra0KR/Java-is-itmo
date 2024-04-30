package ru.aleksandra0KR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableJpaRepositories("ru.aleksandra0KR.repository")
@EntityScan("ru.aleksandra0KR.model")
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    System.out.println(bCryptPasswordEncoder.encode("admin"));
    ApplicationContext context = SpringApplication.run(Application.class, args);
  }
}