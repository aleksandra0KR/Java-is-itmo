package ru.aleksandra0KR.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
  public List<CatDtoClient> getAllFriends(@PathVariable("id") long id) {

    return catService.getAllFriends(id);
  }

  @GetMapping
  public List<CatDtoClient> getCatsByColorOrBreedOrName(
      @RequestParam(value = "color", required = false) String color,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "breed", required = false) String breed) {

    return catService.findCatsByColorOrBreedOrName(color, breed, name);
  }

  @GetMapping("/owner/{id}")
  public List<CatDtoClient> getAllOwnerCats(@PathVariable("id") Long id) {

    return catService.getAllOwnerCats(id);
  }
}