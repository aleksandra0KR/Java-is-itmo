package ru.aleksandra0KR.service;

import java.security.Principal;
import java.util.List;
import ru.aleksandra0KR.dto.CatDto;

public interface CatService {

  CatDto findCatByID(long id);

  CatDto addCat(CatDto cat);

  void updateCat(Principal principal, CatDto cat);

  void deleteCat(Principal principal, long id);

  List<CatDto> getAllFriends(long id);

  void addFriend(long cat, long catsFriend);

  void attachPerson(Long personDto, Long catDto);

  void detachPerson(Long personId, Long catId);

  List<CatDto> findCatsByColorOrBreedOrName(Principal principal, String color, String breed,
      String name);

}
