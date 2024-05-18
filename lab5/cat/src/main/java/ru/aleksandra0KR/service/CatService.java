package ru.aleksandra0KR.service;

import ru.aleksandra0KR.dto.CatDto;
import java.security.Principal;
import java.util.List;
import ru.aleksandra0KR.dto.CatDtoClient;

public interface CatService {

  CatDtoClient findCatByID(long id);

  void addCat(CatDto cat);

  void updateCat(CatDto cat, long userId);

  void deleteCat(long id, long userId);

  List<CatDto> getAllFriends(long id);

  void addFriend(long cat, long catsFriend);

  void attachPerson(Long personDto, Long catDto);

  void detachPerson(Long personId, Long catId);

  List<CatDto> findCatsByColorOrBreedOrName(String color, String breed,
      String name, long userId);

}
