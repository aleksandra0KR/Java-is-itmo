package ru.aleksandra0KR;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.repository.CatRepository;
import ru.aleksandra0KR.repository.PersonRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestPerson {

  @LocalServerPort
  private int port;

  private String baseUrl = "http://localhost";

  private static RestTemplate restTemplate;

  @Autowired
  private PersonRepository personRepository;

  @BeforeAll
  public static void init() {
    restTemplate = new RestTemplate();
  }

  @BeforeEach
  public void setUp() {
    baseUrl = baseUrl.concat(":").concat(port + "").concat("/person");
  }


  @Test
  public void createPersonTest() {
    PersonDto owner = new PersonDto(0L, "Sasha", LocalDate.of(2000, 1, 1));

    PersonDto owner2 = restTemplate.postForObject(baseUrl.concat("/createPerson"), owner,
        PersonDto.class);
    assertEquals(owner2.getBirthDate(), owner.getBirthDate());
    assertEquals(owner2.getName(), owner.getName());
  }


  @Test
  @Sql(statements = "INSERT INTO People (person_id, birthday, name) VALUES (0, '2000-01-01', 'Sasha')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  public void updatePersonTest() {
    PersonDto personDto = new PersonDto(0L, "Lena", LocalDate.of(2000, 1, 1));
    restTemplate.put(baseUrl.concat("/updatePerson"), personDto, PersonDto.class);
    PersonDto personDto2 = restTemplate.getForObject(baseUrl.concat("/findPersonByID?id=0"),
        PersonDto.class);
    assertAll(
        () -> assertEquals("Lena", personDto2.getName())
    );
  }

  @Test
  @Sql(statements = "INSERT INTO People (person_id, birthday, name) VALUES (1, '2000-01-01', 'Sasha')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  public void findPersonTest() {
    PersonDto personDto = restTemplate.getForObject(baseUrl.concat("/findPersonByID?id=1"),
        PersonDto.class);
    assertAll(
        () -> assertEquals("Sasha", personDto.getName()),
        () -> assertEquals(LocalDate.of(2000, 1, 1), personDto.getBirthDate())
    );
  }


  @Test
  public void deletePersonTest() {
    int recordCount = personRepository.findAll().size();
    assertEquals(1, recordCount);
    restTemplate.delete(baseUrl + "/deletePerson?id=1", 1);
    assertEquals(0, personRepository.findAll().size());

  }

}