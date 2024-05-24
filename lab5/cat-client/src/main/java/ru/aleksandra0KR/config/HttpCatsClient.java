package ru.aleksandra0KR.config;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
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
    var res = catsWebClient
        .get()
        .uri("cat/%d/friends".formatted(id))
        .retrieve()
        .bodyToMono(List.class)
        .block();
    //System.out.println(res);
    return res;
  }


  @Override
  public List<CatDtoClient> findCatsByColorOrBreedOrName(String color, String breed, String name) {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080")
        .path("/cat");

    if (color != null) {
      uriBuilder.queryParam("color", color);
    }

    if (breed != null) {
      uriBuilder.queryParam("breed", breed);
    }

    if (name != null) {
      uriBuilder.queryParam("name", name);
    }

    URI uri = uriBuilder.build().toUri();

    var res = catsWebClient
        .get()
        .uri(uri)
        .retrieve()
        .bodyToMono(ArrayList.class)
        .block();
    System.out.println(res);

    return res;
  }
}
