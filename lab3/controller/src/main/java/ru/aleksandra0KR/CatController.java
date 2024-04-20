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

  @PostMapping("/createCat")
  public void catRegistration(@Valid @RequestBody CatDto cat) {
    catService.addCat(cat);
  }

  @GetMapping("/findCatByID")
  public CatDto findCatByID(@RequestParam("id") Long id) {
    return catService.findCatByID(id);
  }

  @GetMapping("/findCatsByColor")
  public ResponseEntity<List<CatDto>> findCatsByColor(@RequestParam("color") String color) {
    List<CatDto> cats = catService.findCatsByColor(color);

    if (cats.isEmpty()) {
      return ResponseEntity.ok().body(Collections.emptyList());
    } else {
      return ResponseEntity.ok(cats);
    }
  }

  @GetMapping("/findCatByBreed")
  public ResponseEntity<List<CatDto>> findCatByBreed(@RequestParam("breed") String breed) {
    List<CatDto> cats = catService.findCatByBreed(breed);

    if (cats.isEmpty()) {
      return ResponseEntity.ok().body(Collections.emptyList());
    } else {
      return ResponseEntity.ok(cats);
    }
  }

  @GetMapping("/findCatByName")
  public ResponseEntity<List<CatDto>> findCatByName(@RequestParam("name") String name) {
    List<CatDto> cats = catService.findCatByName(name);

    if (cats.isEmpty()) {
      return ResponseEntity.ok().body(Collections.emptyList());
    } else {
      return ResponseEntity.ok(cats);
    }
  }

  @PutMapping("/updateCat")
  public void updateCat(@Valid @RequestBody CatDto cat) {
    catService.updateCat(cat);
  }

  @DeleteMapping("/deleteCat")
  public void deleteCat(@RequestParam("id") Long id) {
    catService.deleteCat(id);
  }

  @GetMapping("/getAllFriends")
  public ResponseEntity<List<CatDto>> getAllFriends(@RequestParam("id") Long id) {

    List<CatDto> cats = catService.getAllFriends(id);

    if (cats.isEmpty()) {
      return ResponseEntity.ok().body(Collections.emptyList());
    } else {
      return ResponseEntity.ok(cats);
    }
  }

  @PutMapping("/addFriend")
  public void addFriend(@RequestParam("catId") Long catId,
      @RequestParam("friendId") Long friendId) {
    catService.addFriend(catId, friendId);
  }

  @PutMapping("/attachPerson")
  public void attachPerson(@RequestParam("personid") Long personid,
      @RequestParam("catid") Long catid) {
    catService.attachPerson(personid, catid);
  }

  @PutMapping("/detachPerson")
  public void detachPerson(@RequestParam("personid") Long personid,
      @RequestParam("catid") Long catid) {
    catService.detachPerson(personid, catid);
  }
}