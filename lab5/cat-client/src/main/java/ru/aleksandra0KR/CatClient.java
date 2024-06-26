package ru.aleksandra0KR;

import java.util.List;
import ru.aleksandra0KR.dto.CatDtoClient;
import ru.aleksandra0KR.ru.dto.PersonDtoMessage;

public interface CatClient {

  CatDtoClient findCatByID(long id);


  List<CatDtoClient> getAllFriends(long id);

  List<CatDtoClient> findCatsByColorOrBreedOrName(String color, String breed,
      String name, PersonDtoMessage personDtoMessage);

  List<CatDtoClient> getAllOwnerCats(PersonDtoMessage personDtoMessage);
}