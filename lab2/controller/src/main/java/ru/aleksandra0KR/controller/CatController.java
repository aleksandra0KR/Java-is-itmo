package ru.aleksandra0KR.controller;

import java.time.LocalDate;
import java.util.List;
import lombok.NoArgsConstructor;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.service.CatServiceImplementation;
import ru.aleksandra0KR.service.CatService;

@NoArgsConstructor
public class CatController {

  private final CatService catService = new CatServiceImplementation();

  public CatDto findCatByID(long id) {
    CatDto cat = catService.findCatByID(id);
    System.out.println("The Cat has been found" + cat.toString());
    return cat;
  }

  public CatDto createCat(String name, LocalDate birthday, String color, String breed,
      PersonDto owner) {
    CatDto cat = catService.addCat(name, birthday, color, breed, owner);
    System.out.println("The Cas has been created with id: " + cat.getId());
    return cat;
  }

  public CatDto changeBirthdate(CatDto cat, LocalDate date) {
    cat.setBirthDate(date);
    catService.updateCat(cat);
    CatDto newCat = catService.findCatByID(cat.getId());
    System.out.println("The birthdate has been changed to " + newCat.getBirthDate());
    return cat;
  }

  public CatDto changeName(CatDto cat, String name) {
    cat.setName(name);
    catService.updateCat(cat);
    CatDto newCat = catService.findCatByID(cat.getId());
    System.out.println("The name has been changed to " + newCat.getName());
    return newCat;
  }

  public CatDto changeColor(CatDto cat, String color) {
    cat.setColor(color);
    catService.updateCat(cat);
    CatDto newCat = catService.findCatByID(cat.getId());
    System.out.println("The color has been changed to " + newCat.getColor());
    return newCat;
  }

  public CatDto changeBreed(CatDto cat, String breed) {
    cat.setBreed(breed);
    catService.updateCat(cat);
    CatDto newCat = catService.findCatByID(cat.getId());
    System.out.println("The breed has been changed to " + newCat.getBreed());
    return newCat;
  }

  public void addFriend(long idFirst, long idSecond) {
    catService.addFriend(idFirst, idSecond);
    System.out.println("The friend has been added");
  }

  public void deleteCat(long id) {
    System.out.println("The cat has been deleted");
    catService.deleteCat(id);
  }

  public List<CatDto> getAllFriends(long id) {
    List<CatDto> friends = catService.getAllFriends(id);
    for (CatDto cat : friends) {
      System.out.println(cat.toString());
    }
    return friends;
  }

}

