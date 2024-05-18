package ru.aleksandra0KR.controller;

import lombok.AllArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aleksandra0KR.dto.CatDtoGateway;
import ru.aleksandra0KR.service.CatGatewayService;

@RestController
@RequestMapping("/cat")
@Validated
@AllArgsConstructor
public class CatController {

  private final CatGatewayService catGatewayService;


  @PostMapping
  public void catRegistration(@RequestBody CatPost cat) {
    System.out.println(cat);
    catGatewayService.create(
        cat.getName(),
     cat.getOwnerID(),
        cat.getBirthDay(),
        cat.getBreed(),
        cat.getColor()
    );
  }

  @GetMapping("/{id}")
  public CatDtoGateway findCatByID(@PathVariable("id") Long id) {
    return  catGatewayService.getById(id);
  }

  // TODO
  /*
  @GetMapping
  public ResponseEntity<List<CatDto>> getCatsByColorOrBreedOrName(
      @RequestParam(value = "color", required = false) String color,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "breed", required = false) String breed,
      Principal principal) {

    List<CatDto> catDTOList;
    try {
      catDTOList = catService.findCatsByColorOrBreedOrName(principal, color, breed, name);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(catDTOList, HttpStatus.OK);
  }

  @PutMapping
  public void updateCat(@Valid @RequestBody CatDto cat, Principal principal) {
    catService.updateCat(principal, cat);
  }

  @DeleteMapping
  public void deleteCat(@RequestParam("id") Long id, Principal principal) {
    catService.deleteCat(principal, id);
  }

  @GetMapping("/{id}/friends")
  public ResponseEntity<List<CatDto>> getAllFriends(@PathVariable("id") Long id) {

    List<CatDto> cats = catService.getAllFriends(id);

    if (cats.isEmpty()) {
      return ResponseEntity.ok().body(Collections.emptyList());
    } else {
      return ResponseEntity.ok(cats);
    }
  }

  @PutMapping("/{id}/friends/{friendId}")
  public void addFriend(@PathVariable("id") Long id, @PathVariable("friendId") Long friendId) {
    catService.addFriend(id, friendId);
  }

  @PutMapping("/{id}/people/{personId}")
  public void attachPerson(@PathVariable("id") Long id, @PathVariable("personId") Long personId) {
    catService.attachPerson(id, personId);
  }

  @DeleteMapping("/{id}/people/{personId}")
  public void detachPerson(@PathVariable("id") Long id, @PathVariable("personId") Long personId) {
    catService.detachPerson(id, personId);
  }*/
}