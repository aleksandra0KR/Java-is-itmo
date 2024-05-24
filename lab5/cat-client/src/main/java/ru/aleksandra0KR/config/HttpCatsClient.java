package ru.aleksandra0KR.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.swagger.v3.core.util.Json;
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
  public List<CatDtoClient> findCatsByColorOrBreedOrName(String color, String breed, String name, long id) {
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
    ObjectMapper mapper = new ObjectMapper()
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule());
    JsonNode res = catsWebClient
        .get()
        .uri(uri)
        .retrieve()
        .bodyToMono(JsonNode.class)
        .block();
    List<CatDtoClient> accountList = mapper.convertValue(
        res,
        new TypeReference<List<CatDtoClient>>(){}
    );
    accountList.stream()
        .filter(cat -> cat.getOwner() != null && cat.getOwner().equals(id)).toList();
    System.out.println(res);

    return accountList;
  }

  @Override
  public List<CatDtoClient> getAllOwnerCats(long id) {
    var res = catsWebClient
        .get()
        .uri("cat//owner%d".formatted(id))
        .retrieve()
        .bodyToMono(Json.class)
        .block();


    ObjectMapper mapper = new ObjectMapper()
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule());
    List<CatDtoClient> accountList = mapper.convertValue(
        res,
        new TypeReference<List<CatDtoClient>>(){}
    );
    accountList.stream()
        .filter(cat -> cat.getOwner() != null && cat.getOwner().equals(id)).toList();
    return accountList;
  }
}
