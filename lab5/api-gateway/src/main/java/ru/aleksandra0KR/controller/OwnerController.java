package ru.aleksandra0KR.controller;

import jakarta.validation.Valid;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aleksandra0KR.dto.OwnerDtoClient;
import ru.aleksandra0KR.exception.EmptyPersonException;
import ru.aleksandra0KR.service.OwnerGatewayService;
import ru.aleksandra0KR.service.PersonService;


@RestController
@RequestMapping("/owner")
@Validated
public class OwnerController {

  private final OwnerGatewayService ownerGatewayService;
  private final PersonService personService;


  @Autowired
  public OwnerController(OwnerGatewayService ownerGatewayService, PersonService personService) {
    this.ownerGatewayService = ownerGatewayService;
    this.personService = personService;
  }

  @GetMapping("/{id}")
  public OwnerDtoClient findPersonById(@PathVariable("id") Long id, Principal principal) {
    return ownerGatewayService.findPersonById(id, principal);
  }
  @GetMapping("/name/{name}")
  public OwnerDtoClient findPersonByName(@PathVariable("name") String name, Principal principal) {
    return ownerGatewayService.findPersonByName(name, principal);
  }

  @PutMapping
  public void updatePerson(@Valid @RequestBody OwnerPost person, Principal principal) {
    ownerGatewayService.update(person, principal);
  }

  @DeleteMapping("/{id}")
  public void deletePerson(@PathVariable("id") Long id, Principal principal) {
    ownerGatewayService.deleteOwner(principal, id);
  }

  @PostMapping
  public String addOwner(@RequestBody OwnerPost ownerDto) {
    if (ownerDto != null) {
      ownerGatewayService.addOwner(ownerDto);
      return "Owner was added successfully";
    } else {
      throw new EmptyPersonException();
    }
  }

}