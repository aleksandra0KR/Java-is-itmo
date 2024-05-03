package ru.aleksandra0KR;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.OwnerDto;
import ru.aleksandra0KR.model.Cat;
import ru.aleksandra0KR.model.Person;
import ru.aleksandra0KR.repository.OwnerRepository;
import ru.aleksandra0KR.repository.PersonRepository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class Tests {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private OwnerRepository ownerRepository;


  @Test
  @WithMockUser(username = "user1", password = "user1", roles = "USER")
  public void createOwnerFromUserRole() throws Exception {

    Person person = new Person();
    person.setUsername("user1");
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    person.setPassword(passwordEncoder.encode("user1"));
    person.setRoles("USER");
    personRepository.save(person);

    OwnerDto ownerDTO = new OwnerDto(2L, "user1", LocalDate.of(2004, 5, 9), "USER");

    mvc.perform(post("/owner")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(ownerDTO)))
        .andExpect(status().isOk());

    MvcResult result = mvc.perform(get("/owner/2"))
        .andExpect(status().isOk())
        .andReturn();

    assertEquals(2, ownerRepository.findAll().size());

    String response = result.getResponse().getContentAsString();
    OwnerDto ownerFromResponse = objectMapper.readValue(response, OwnerDto.class);

    assertAll(
        () -> assertEquals(ownerFromResponse.getRoles(), ownerDTO.getRoles()),
        () -> assertEquals(ownerFromResponse.getBirthDate(), ownerDTO.getBirthDate()),
        () -> assertEquals(ownerFromResponse.getName(), ownerDTO.getName())
    );
  }


  @Test
  @WithMockUser(username = "user1", password = "user1", roles = "USER")
  public void failedToCreatePersonFromUserRole() throws Exception {

    OwnerDto ownerDTO = new OwnerDto(1L, "user1", LocalDate.of(2004, 5, 9), "USER");

    mvc.perform(post("/person")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(ownerDTO)))
        .andExpect(status().isForbidden());
  }


  @Test
  @WithMockUser(username = "user2", password = "user2", roles = "USER")
  public void createAndAttachCat() throws Exception {

    Person person = new Person();
    person.setUsername("user2");
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    person.setPassword(passwordEncoder.encode("user2"));
    person.setRoles("USER");
    personRepository.save(person);

    OwnerDto ownerDTO2 = new OwnerDto(1L, "user2", LocalDate.of(2004, 5, 9), "USER");

    mvc.perform(post("/owner")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(ownerDTO2)))
        .andExpect(status().isOk());

    CatDto catDto = new CatDto(1L, "Fufa", LocalDate.of(2020, 1, 31), "shinshilla", "grey");

    mvc.perform(post("/cat")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(catDto)))
        .andExpect(status().isOk());

    mvc.perform(put("/cat/1/people/1"))
        .andExpect(status().isOk())
        .andReturn();

    MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/owner/1/cats"))
        .andExpect(status().isOk())
        .andReturn();

    String response = result.getResponse().getContentAsString();
    ArrayList<Cat> cats = objectMapper.readValue(response, ArrayList.class);

    assertEquals(
        cats.size(), 1);
  }

}