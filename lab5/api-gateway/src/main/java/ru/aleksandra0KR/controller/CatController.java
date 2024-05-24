package ru.aleksandra0KR.controller;

import jakarta.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;

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
import ru.aleksandra0KR.dto.CatDtoClient;
import ru.aleksandra0KR.dto.CatDtoGateway;
import ru.aleksandra0KR.ru.dto.CatFriendDtoMessage;
import ru.aleksandra0KR.service.CatGatewayService;

@RestController
@RequestMapping("/cat")
@Validated
@AllArgsConstructor
public class CatController {

  private final CatGatewayService catGatewayService;


  @PostMapping
  public void catRegistration(@RequestBody CatPost cat) {
    catGatewayService.create(cat);
  }

  @GetMapping("/{id}")
  public CatDtoGateway findCatByID(@PathVariable("id") Long id, Principal principal) {
    return catGatewayService.getById(id, principal);
  }

  @GetMapping
  public List<CatDtoClient> getCatsByColorOrBreedOrName(
      @RequestParam(value = "color", required = false) String color,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "breed", required = false) String breed,
      Principal principal) {
    List<CatDtoClient> cats = catGatewayService.getCatsByColorOrBreedOrName(principal, color, breed, name);

      return cats;

  }

  @GetMapping("/{id}/friends")
  public List<CatDtoClient> getAllFriends(@PathVariable("id") Long id,
      Principal principal) {

    List<CatDtoClient> cats = catGatewayService.getFriendsById(principal, id);

   return cats;
  }

  @PutMapping
  public void updateCat(@Valid @RequestBody CatPost cat, Principal principal) {
    catGatewayService.update(cat, principal);
  }

  @DeleteMapping
  public void deleteCat(@RequestParam("id") Long id, Principal principal) {
    catGatewayService.deleteCat(principal, id);
  }


  @PutMapping("/{id}/friends/{friendId}")
  public void addFriend(@PathVariable("id") Long id, @PathVariable("friendId") Long friendId) {
    catGatewayService.addFriend(CatFriendDtoMessage.builder().catId(id).friendId(friendId).build());
  }
}
