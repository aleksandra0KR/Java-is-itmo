package ru.aleksandra0KR.service;

import java.time.LocalDate;
import java.util.List;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;

public interface CatService {

  CatDto findCatByID(long id);

  CatDto addCat(String name, LocalDate birthday, String color, String breedDto, PersonDto owner);

  void updateCat(CatDto cat);

  void deleteCat(long id);

  List<CatDto> getAllFriends(long id);

  void addFriend(long cat, long catsFriend);
}
