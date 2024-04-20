package ru.aleksandra0KR;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.repository.CatRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCats {

  @LocalServerPort
  private int port;

  private String baseUrl = "http://localhost";

  private static RestTemplate restTemplate;

  @Autowired
  private CatRepository catRepository;


  @BeforeAll
  public static void init() {
    restTemplate = new RestTemplate();
  }

  @BeforeEach
  public void setUp() {
    baseUrl = baseUrl.concat(":").concat(port + "").concat("/cat");
  }

  @Test
  @Sql(statements = "INSERT INTO Cats (id, name, color, breed, birthday) VALUES (0,'Fufa', 'silver', 'black', '2020-01-01')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  public void findCatByColorTest() {
   List<Cat> cats = catRepository.findCatByColor("gold");
    assertEquals(0, cats.size());
  }

  @Test
  public void emptyFiendsTest() {

    CatDto catDto = new CatDto(0L, "Sasha", LocalDate.of(2000, 1, 1), "shinsh", "black");
    restTemplate.postForObject(baseUrl, catDto,
        CatDto.class);

    List<CatDto> cats = restTemplate.getForObject(baseUrl.concat("/0/friends"),
        List.class);

    assertEquals(0, cats.size());

  }

}