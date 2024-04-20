package ru.aleksandra0KR;

import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.service.PersonService;

@RestController
@RequestMapping("/person")
@Validated
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping("/findPersonByID")
  public PersonDto findPersonByID(@RequestParam("id") Long id) {
    return personService.findPersonByID(id);
  }

  @GetMapping("/findPersonByName")
  public PersonDto findPersonByName(@RequestParam("name") String name) {
    return personService.findPersonByName(name);
  }

  @GetMapping("/findAllCats")
  public ResponseEntity<List<CatDto>> findAllCats(@RequestParam("id") Long id) {
    List<CatDto> cats = personService.findAllCats(id);

    if (cats.isEmpty()) {
      return ResponseEntity.ok().body(Collections.emptyList());
    } else {
      return ResponseEntity.ok(cats);
    }
  }

  @PostMapping("/createPerson")
  public PersonDto addPerson(@Valid @RequestBody PersonDto person) {
    return personService.addPerson(person);
  }

  @DeleteMapping("/deletePerson")
  public void deletePerson(@RequestParam("id") Long id) {
    personService.deletePerson(id);
  }

  @PutMapping("/updatePerson")
  public void updatePerson(@Valid @RequestBody PersonDto person) {
    personService.updatePerson(person);
  }
}