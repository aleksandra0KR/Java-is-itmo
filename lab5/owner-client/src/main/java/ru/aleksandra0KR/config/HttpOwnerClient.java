package ru.aleksandra0KR.config;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;
import ru.aleksandra0KR.OwnerClient;
import ru.aleksandra0KR.dto.OwnerDtoClient;

@RequiredArgsConstructor
public class HttpOwnerClient implements OwnerClient {

  private final WebClient ownersWebClient;

  @Override
  public OwnerDtoClient GetOwnerById(long id) {

    return ownersWebClient
        .get()
        .uri("/owner/%d".formatted(id))
        .retrieve()
        .bodyToMono(OwnerDtoClient.class)
        .block();
  }

  @Override
  public OwnerDtoClient GetOwnerByName(String name) {
    return ownersWebClient
        .get()
        .uri("/owner/name/%s".formatted(name))
        .retrieve()
        .bodyToMono(OwnerDtoClient.class)
        .block();
  }

  private static HttpServletRequest getRequest() {
    return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
        .filter(ServletRequestAttributes.class::isInstance)
        .map(ServletRequestAttributes.class::cast)
        .map(ServletRequestAttributes::getRequest)
        .orElseThrow(RuntimeException::new);
  }
}