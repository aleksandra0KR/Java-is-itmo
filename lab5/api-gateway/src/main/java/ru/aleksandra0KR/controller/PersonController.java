package ru.aleksandra0KR.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {
/*
  final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping
  public String addUser(@RequestBody PersonDto person) {

    if (person != null) {
      personService.addUser(person);
      return "User was added successfully";
    } else {
      throw new EmptyPersonException();
    }

  }
*/
}