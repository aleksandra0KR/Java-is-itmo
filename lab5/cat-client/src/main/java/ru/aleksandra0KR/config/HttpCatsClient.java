package ru.aleksandra0KR.config;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;
import ru.aleksandra0KR.CatClient;
import ru.aleksandra0KR.dto.CatDtoClient;


public class HttpCatsClient implements CatClient {

  @Autowired
  private WebClient catsWebClient;

  @Override
  public CatDtoClient findCatByID(long id) {


    return catsWebClient
        .get()
        .uri("/cat/%d".formatted(id))
        .retrieve()
        .bodyToMono(CatDtoClient.class)
        .block();

  }
  private static HttpServletRequest getRequest() {
    return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
        .filter(ServletRequestAttributes.class::isInstance)
        .map(ServletRequestAttributes.class::cast)
        .map(ServletRequestAttributes::getRequest)
        .orElseThrow(RuntimeException::new);
  }

  // TODO
  @Override
  public List<CatDtoClient> getAllFriends(long id) {
    return List.of();
  }

  // TODO
  @Override
  public List<CatDtoClient> findCatsByColorOrBreedOrName(String color, String breed, String name,
      long userId) {
    return List.of();
  }
}
