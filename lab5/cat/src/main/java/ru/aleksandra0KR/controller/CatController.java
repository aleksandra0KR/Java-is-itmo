package ru.aleksandra0KR.controller;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.CatDtoClient;
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


  @GetMapping("/{id}")
  public CatDtoClient findCatByID(@PathVariable("id") long id) {
    return catService.findCatByID(id);
  }

  @GetMapping("/{id}/friends")
  public ResponseEntity<List<CatDto>> getAllFriends(@PathVariable("id") long id) {

    List<CatDto> cats = catService.getAllFriends(id);

    if (cats.isEmpty()) {
      return ResponseEntity.ok().body(Collections.emptyList());
    } else {
      return ResponseEntity.ok(cats);
    }
  }

  @GetMapping
  public ResponseEntity<List<CatDto>> getCatsByColorOrBreedOrName(
      @RequestParam(value = "color", required = false) String color,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "breed", required = false) String breed,
      @RequestParam("personId") long personId) {

    List<CatDto> catDTOList;
    try {
      catDTOList = catService.findCatsByColorOrBreedOrName(color, breed, name, personId);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(catDTOList, HttpStatus.OK);
  }
}