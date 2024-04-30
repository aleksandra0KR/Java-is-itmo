package ru.aleksandra0KR;

import jakarta.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.service.CatService;

@RestController
@RequestMapping("/cat")
@Validated
public class CatController {

  private final CatService catService;

  @Autowired
  public CatController(CatService catService) {
    this.catService = catService;
  }

  @PostMapping
  public void catRegistration(@Valid @RequestBody CatDto cat) {
    catService.addCat(cat);
  }

  @GetMapping("/{id}")
  public CatDto findCatByID(@PathVariable("id") Long id) {
    return catService.findCatByID(id);
  }

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
    catService.detachPerson(personId, id);
  }
}