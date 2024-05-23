package ru.aleksandra0KR.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import ru.aleksandra0KR.CatClient;
import ru.aleksandra0KR.controller.CatPost;
import ru.aleksandra0KR.dto.CatDtoClient;
import ru.aleksandra0KR.dto.CatDtoGateway;
import ru.aleksandra0KR.ru.dto.CatFriendDtoMessage;

public interface CatGatewayService {

  void deleteCat(Principal principal, long id);

  void create(CatPost catPost);

  void update(CatPost catPost, Principal principal);

  CatDtoGateway getById(Long uuid, Principal principal);

  void addFriend(CatFriendDtoMessage catFriendDtoMessage);

  List<CatDtoClient> getCatsByColorOrBreedOrName(Principal principal, String color, String breed,
      String name);

  List<CatDtoClient> getFriendsById(Principal principal, long catId);
}
