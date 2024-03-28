package ru.aleksandra0KR.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.aleksandra0KR.dao.CatDao;
import ru.aleksandra0KR.dao.CatDaoPostgres;
import ru.aleksandra0KR.dao.PersonDao;
import ru.aleksandra0KR.dao.PersonPostgresDao;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.mapper.CatMapper;

public class CatServiceImplementation implements CatService {

  private final CatDao catDao = new CatDaoPostgres();
  private final PersonDao personDao = new PersonPostgresDao();


  public CatDto findCatByID(long id) {
    return CatMapper.asDto(catDao.findCatByID(id));
  }

  public CatDto addCat(String name, LocalDate birthday, String color, String breed, PersonDto owner) {
    Cat cat = new Cat(name, color, breed, birthday, personDao.findPersonByID(owner.getId()));
    long id = catDao.addCat(cat);
    CatDto catDto = CatMapper.asDto(cat);
    catDto.setId(id);
    return catDto;
  }

  public void updateCat(CatDto cat) {
    catDao.updateCat(CatMapper.asDao(cat));
  }

  public void deleteCat(CatDto cat) {

    catDao.deleteCat(CatMapper.asDao(cat));
  }

  public List<CatDto> findAllFriends(long id) {
    List<CatDto> friendsDto = new ArrayList<>();
    for (Cat cat : catDao.findAllFriends(id)) {
      friendsDto.add(CatMapper.asDto(cat));
    }
    return friendsDto;
  }

  public void addFriend(CatDto cat, CatDto catsFriend) {
    catDao.addFriend(CatMapper.asDao(cat), CatMapper.asDao(catsFriend));
  }
}







