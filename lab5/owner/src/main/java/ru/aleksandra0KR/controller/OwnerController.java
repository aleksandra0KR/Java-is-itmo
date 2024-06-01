package ru.aleksandra0KR.controller;

import org.springframework.web.bind.annotation.*;
import ru.aleksandra0KR.dto.OwnerDtoClient;
import ru.aleksandra0KR.service.OwnerService;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/owner")
@Validated
public class OwnerController {

  private final OwnerService ownerService;

  public OwnerController(OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @GetMapping("/{id}")
  public OwnerDtoClient findPersonById(@PathVariable("id") Long id) {
    return ownerService.findPersonByID(id);
  }

  @GetMapping("/name/{name}")
  public OwnerDtoClient findPersonByName(@PathVariable("name") String name) {
    return ownerService.findPersonByName(name);
  }

}