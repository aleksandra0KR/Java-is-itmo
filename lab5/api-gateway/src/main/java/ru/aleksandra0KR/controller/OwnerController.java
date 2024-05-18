package ru.aleksandra0KR.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aleksandra0KR.service.OwnerGatewayService;


@RestController
@RequestMapping("/owner")
@Validated
public class OwnerController {

  private OwnerGatewayService ownerGatewayService;

  @Autowired
  public OwnerController(OwnerGatewayService ownerGatewayService) {
    this.ownerGatewayService = ownerGatewayService;
  }
  /*
  private final OwnerService ownerService;

  final
  PersonService personService;

  @Autowired
  public OwnerController(OwnerService ownerService, PersonService personService) {
    this.ownerService = ownerService;
    this.personService = personService;
  }

  @GetMapping("/{id}")
  public OwnerDto findPersonById(@PathVariable("id") Long id) {
    return ownerService.findPersonByID(id);
  }

  @GetMapping("/name/{name}")
  public OwnerDto findPersonByName(@PathVariable("name") String name) {
    return ownerService.findPersonByName(name);
  }

  @GetMapping("/{id}/cats")
  public ResponseEntity<List<CatDto>> findAllCats(@PathVariable("id") Long id) {
    List<CatDto> cats = ownerService.findAllCats(id);

    if (cats.isEmpty()) {
      return ResponseEntity.ok().body(Collections.emptyList());
    } else {
      return ResponseEntity.ok(cats);
    }
  }

  @PostMapping
  public String addUser(@RequestBody OwnerDto ownerDto, Principal principal) {
    if (ownerDto != null) {
      personService.addOwner(ownerDto, principal);
      return "Owner was added successfully";
    } else {
      throw new EmptyPersonException();
    }
  }

  @DeleteMapping("/{id}")
  public void deletePerson(@PathVariable("id") Long id) {
    ownerService.deletePerson(id);
  }

  @PutMapping
  public void updatePerson(@Valid @RequestBody OwnerDto person) {
    ownerService.updatePerson(person);
  }*/
}