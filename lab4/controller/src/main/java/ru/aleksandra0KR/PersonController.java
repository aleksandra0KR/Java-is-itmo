package ru.aleksandra0KR;

import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aleksandra0KR.dto.OwnerDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.exceptions.EmptyPersonException;
import ru.aleksandra0KR.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

  final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping("/registration")
  public String addUser(@RequestBody PersonDto person) {

    if (person != null) {
      personService.addUser(person);
      return "User was added successfully";
    } else {
      throw new EmptyPersonException();
    }
  }

}