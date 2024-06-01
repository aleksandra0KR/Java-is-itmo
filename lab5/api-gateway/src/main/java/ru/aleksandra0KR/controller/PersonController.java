package ru.aleksandra0KR.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aleksandra0KR.dao.Person;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.ru.exception.EmptyPersonException;
import ru.aleksandra0KR.ru.exception.PersonPrivilegeException;
import ru.aleksandra0KR.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

  final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping
  public String addUser(@RequestBody PersonDto person, Principal principal) {
    if (person != null) {
      personService.addUser(person, principal);
      return "User was added successfully";
    } else {
      throw new EmptyPersonException();
    }

  }

}