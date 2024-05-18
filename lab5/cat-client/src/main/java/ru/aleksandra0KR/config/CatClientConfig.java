package ru.aleksandra0KR.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CatWebClientConfig.class, HttpCatsClient.class})
public class CatClientConfig {

}
