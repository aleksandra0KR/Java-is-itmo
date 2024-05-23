package ru.aleksandra0KR.config;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
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

  @Override
  public List<CatDtoClient> getAllFriends(long id) {
    return catsWebClient
        .get()
        .uri("/%d/friends".formatted(id))
        .retrieve()
        .bodyToMono(List.class)
        .block();
  }


  @Override
  public List<CatDtoClient> findCatsByColorOrBreedOrName(String color, String breed, String name) {
    LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.put("name", List.of(name));
    params.put("color", List.of(color));
    params.put("breed", List.of(breed));

    return catsWebClient
        .get()
        .uri(it -> it.path("/cat").queryParams(params).build())
        .retrieve()
        .bodyToMono(List.class)
        .block();
  }
}
