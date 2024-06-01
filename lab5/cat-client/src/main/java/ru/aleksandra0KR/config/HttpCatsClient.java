package ru.aleksandra0KR.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.swagger.v3.core.util.Json;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import ru.aleksandra0KR.CatClient;
import ru.aleksandra0KR.dto.CatDtoClient;
import ru.aleksandra0KR.ru.dto.PersonDtoMessage;


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

  @Override
  public List<CatDtoClient> getAllFriends(long id) {
    return catsWebClient
        .get()
        .uri("cat/%d/friends".formatted(id))
        .retrieve()
        .bodyToMono(List.class)
        .block();
  }


  @Override
  public List<CatDtoClient> findCatsByColorOrBreedOrName(String color, String breed, String name,
     PersonDtoMessage personDtoMessage) {
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
    List<CatDtoClient> cats = mapper.convertValue(
        res,
        new TypeReference<List<CatDtoClient>>() {
        }
    );
    if (personDtoMessage.getRoles().equals("ROLE_ADMIN")) return cats;

    cats.stream()
        .filter(cat -> cat.getOwner() != null && cat.getOwner().equals(personDtoMessage.getOwnerID())).toList();

    return cats;
  }

  @Override
  public List<CatDtoClient> getAllOwnerCats(PersonDtoMessage personDtoMessage) {
    var res = catsWebClient
        .get()
        .uri("cat//owner%d".formatted(personDtoMessage.getOwnerID()))
        .retrieve()
        .bodyToMono(Json.class)
        .block();

    ObjectMapper mapper = new ObjectMapper()
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule());
    List<CatDtoClient> cats = mapper.convertValue(
        res,
        new TypeReference<List<CatDtoClient>>() {
        }
    );

    if (personDtoMessage.getRoles().equals("ROLE_ADMIN")) return cats;
    cats.stream()
        .filter(cat -> cat.getOwner() != null && cat.getOwner().equals(personDtoMessage.getOwnerID())).toList();
    return cats;
  }
}
