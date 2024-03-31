package ru.aleksandra0KR.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import ru.aleksandra0KR.dao.CatDao;
import ru.aleksandra0KR.dao.CatDaoPostgres;
import ru.aleksandra0KR.dao.PersonDao;
import ru.aleksandra0KR.dao.PersonPostgresDao;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.mapper.CatMapper;

@NoArgsConstructor
public class CatServiceImplementation implements CatService {

  private final CatDao catDaoPostgres = new CatDaoPostgres();
  private final PersonDao personDao = new PersonPostgresDao();

  public CatDto findCatByID(long id) {
    return CatMapper.asDto(catDaoPostgres.findCatByID(id));
  }

  public CatDto addCat(String name, LocalDate birthday, String color, String breed,
      PersonDto owner) {

    Cat cat = new Cat(name, color, breed, birthday, personDao.findPersonByID(owner.getId()));
    personDao.findPersonByID(owner.getId()).addCat(cat);

    long id = catDaoPostgres.addCat(cat);
    CatDto catDto = CatMapper.asDto(cat);
    catDto.setId(id);
    return catDto;
  }

  public void updateCat(CatDto cat) {
    catDaoPostgres.updateCat(CatMapper.asDao(cat));
  }

  public void addFriend(long catID, long friendID) {
    Cat cat = catDaoPostgres.findCatByID(catID);
    Cat friend = catDaoPostgres.findCatByID(friendID);
    catDaoPostgres.addFriend(cat, friend);
  }

  public List<CatDto> getAllFriends(long id) {
    List<Cat> catList = catDaoPostgres.findAllFriends(catDaoPostgres.findCatByID(id));
    List<CatDto> friends = new ArrayList<>();
    for (Cat cat : catList) {
      friends.add(CatMapper.asDto(cat));
    }
    return friends;
  }

  public void deleteCat(long id) {
    catDaoPostgres.deleteCat(catDaoPostgres.findCatByID(id));
  }
}







