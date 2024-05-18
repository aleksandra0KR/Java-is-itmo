package ru.aleksandra0KR.service;

import java.time.LocalDate;
import ru.aleksandra0KR.dto.CatDtoGateway;

public interface CatGatewayService {


  void create(String name, long ownerId, LocalDate birthdat, String breed, String color);
  CatDtoGateway getById(Long uuid);

  // get cat by color ...

  void addFriend(long catId, long friendId, long ownerId);


}
