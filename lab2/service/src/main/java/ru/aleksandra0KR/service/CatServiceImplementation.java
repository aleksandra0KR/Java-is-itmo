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
    var catDto = CatMapper.asDto(catDaoPostgres.findCatByID(id));
    if (catDto == null) {
      throw new NullPointerException("this cat does not exist");
    }
    return catDto;
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
    if (cat == null) {
      throw new NullPointerException("this cat does not exist");
    }
    Cat friend = catDaoPostgres.findCatByID(friendID);
    if (friend == null) {
      throw new NullPointerException("this cat does not exist");
    }
    catDaoPostgres.addFriend(cat, friend);
  }

  public List<CatDto> getAllFriends(long id) {
    Cat cat = catDaoPostgres.findCatByID(id);
    if (cat == null) {
      throw new NullPointerException("this cat does not exist");
    }
    List<Cat> catList = catDaoPostgres.findAllFriends(cat);
    List<CatDto> friends = new ArrayList<>();
    for (Cat c : catList) {
      friends.add(CatMapper.asDto(c));
    }
    return friends;
  }

  public void deleteCat(long id) {
    var cat = catDaoPostgres.findCatByID(id);
    if (cat == null) {
      throw new NullPointerException("this cat does not exist");
    }
    catDaoPostgres.deleteCat(cat);
  }
}







