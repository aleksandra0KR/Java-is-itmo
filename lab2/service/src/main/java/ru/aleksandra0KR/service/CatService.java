package ru.aleksandra0KR.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;

public interface CatService {

  CatDto findCatByID(long id);

  CatDto addCat(String name, LocalDate birthday, String color, String breedDto, PersonDto owner);

  void updateCat(CatDto cat);

  void deleteCat(CatDto cat);

  List<CatDto> findAllFriends(long id);

  void addFriend(CatDto cat, CatDto catsFriend);
}
