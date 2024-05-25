package ru.aleksandra0KR.controller;

import jakarta.validation.Valid;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.aleksandra0KR.dto.OwnerDtoClient;
import ru.aleksandra0KR.dto.OwnerPost;
import ru.aleksandra0KR.ru.exception.EmptyPersonException;
import ru.aleksandra0KR.service.OwnerGatewayService;


@RestController
@RequestMapping("/owner")
@Validated
public class OwnerController {

  private final OwnerGatewayService ownerGatewayService;


  @Autowired
  public OwnerController(OwnerGatewayService ownerGatewayService) {
    this.ownerGatewayService = ownerGatewayService;
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
  public String addOwner(@RequestBody OwnerPost ownerDto, Principal principal) {
    if (ownerDto != null) {
      ownerGatewayService.addOwner(ownerDto.getName(), ownerDto.getBirthDay(), principal);
      return "Owner was added successfully";
    } else {
      throw new EmptyPersonException();
    }
  }

}