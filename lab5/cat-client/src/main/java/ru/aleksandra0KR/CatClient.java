package ru.aleksandra0KR;

import java.util.List;
import ru.aleksandra0KR.dto.CatDtoClient;

public interface CatClient {

  CatDtoClient findCatByID(long id);


  List<CatDtoClient> getAllFriends(long id);

  List<CatDtoClient> findCatsByColorOrBreedOrName(String color, String breed,
      String name, long id);

   List<CatDtoClient> getAllOwnerCats(long id);
}