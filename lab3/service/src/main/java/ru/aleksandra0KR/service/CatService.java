package ru.aleksandra0KR.service;

import java.util.List;
import ru.aleksandra0KR.dto.CatDto;

public interface CatService {

  CatDto findCatByID(long id);

  List<CatDto> findCatsByColor(String color);

  List<CatDto> findCatByBreed(String breed);

  List<CatDto> findCatByName(String name);

  CatDto addCat(CatDto cat);

  void updateCat(CatDto cat);

  void deleteCat(long id);

  List<CatDto> getAllFriends(long id);

  void addFriend(long cat, long catsFriend);

  void attachPerson(Long personDto, Long catDto);

  void detachPerson(Long personId, Long catId);
  List<CatDto> findCatsByColor(String color, String breed, String name);

}
