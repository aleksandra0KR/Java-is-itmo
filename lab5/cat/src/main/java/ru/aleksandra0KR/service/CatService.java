package ru.aleksandra0KR.service;

import ru.aleksandra0KR.dto.CatDto;
import java.security.Principal;
import java.util.List;
import ru.aleksandra0KR.dto.CatDtoClient;
import ru.aleksandra0KR.ru.dto.CatDtoMessage;
import ru.aleksandra0KR.ru.dto.CatFriendDtoMessage;

public interface CatService {

  CatDtoClient findCatByID(long id);

  void addCat(CatDtoMessage cat);

  void updateCat(CatDtoMessage cat);

  void deleteCat(long id);

  List<CatDtoClient> getAllFriends(long id);

  void addFriend(CatFriendDtoMessage catFriendDtoMessage);

  List<CatDtoClient> getAllOwnerCats(long id);

  List<CatDtoClient> findCatsByColorOrBreedOrName(String color, String breed,
      String name);

}
