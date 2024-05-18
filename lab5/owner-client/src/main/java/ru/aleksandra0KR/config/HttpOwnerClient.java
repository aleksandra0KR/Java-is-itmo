package ru.aleksandra0KR.config;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;
import ru.aleksandra0KR.OwnerClient;
import ru.aleksandra0KR.dto.OwnerDto;

@RequiredArgsConstructor
public class HttpOwnerClient implements OwnerClient {

  private final WebClient ownersWebClient;

  @Override
  public OwnerDto getSelf() {
    HttpServletRequest request = getRequest();

    return ownersWebClient
        .get()
        .uri("/owner/self")
        .header("Authorization", request.getHeader("Authorization"))
        .retrieve()
        .bodyToMono(OwnerDto.class)
        .block();
  }

  @Override
  public OwnerDto adminGetById(long uuid) {
    var request = getRequest();

    return ownersWebClient
        .get()
        .uri("/admin/cat-owners/%s".formatted(uuid))
        .header("Authorization", request.getHeader("Authorization"))
        .retrieve()
        .bodyToMono(OwnerDto.class)
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