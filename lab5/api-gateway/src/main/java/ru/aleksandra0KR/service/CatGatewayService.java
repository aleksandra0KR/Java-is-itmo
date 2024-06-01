package ru.aleksandra0KR.service;

import java.security.Principal;
import java.util.List;
import ru.aleksandra0KR.dto.CatPost;
import ru.aleksandra0KR.dto.CatDtoClient;
import ru.aleksandra0KR.dto.CatDtoGateway;
import ru.aleksandra0KR.ru.dto.CatFriendDtoMessage;

public interface CatGatewayService {

  void deleteCat(Principal principal, long id);

  void create(CatPost catPost);

  void update(CatPost catPost, Principal principal);

  CatDtoGateway getById(Long uuid, Principal principal);

  void addFriend(CatFriendDtoMessage catFriendDtoMessage, Principal principal);

  List<CatDtoClient> getCatsByColorOrBreedOrName(Principal principal, String color, String breed,
      String name);

  List<CatDtoClient> getFriendsById(Principal principal, long catId);

  List<CatDtoClient> getAllOwnerCats(Principal principal, long id);
}
