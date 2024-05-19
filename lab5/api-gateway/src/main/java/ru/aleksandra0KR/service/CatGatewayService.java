package ru.aleksandra0KR.service;

import java.security.Principal;
import java.time.LocalDate;
import ru.aleksandra0KR.dto.CatDtoGateway;

public interface CatGatewayService {


  void create(String name, long ownerId, LocalDate birthdat, String breed, String color);
  CatDtoGateway getById(Long uuid, Principal principal);

  // get cat by color ...

  void addFriend(long catId, long friendId, long ownerId);


}
