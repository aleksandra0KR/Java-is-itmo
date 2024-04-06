package ru.aleksandra0KR.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.aleksandra0KR.dao.CatDao;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.hibernate.HibernateSessionFactoryUtil;
import ru.aleksandra0KR.mapper.CatMapper;
import ru.aleksandra0KR.mapper.PersonMapper;

public class CatServiceImplementation implements CatService {

  private final CatDao catDaoPostgres;
  Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();


  public CatServiceImplementation(CatDao catDaoPostgres){
    this.catDaoPostgres = catDaoPostgres;
  }
  public CatDto findCatByID(long id) {
    Transaction transaction =  session.beginTransaction();

    var catDto = CatMapper.asDto(catDaoPostgres.findCatByID(id, session));
    if (catDto == null) {
      throw new NullPointerException("this cat does not exist");
    }
    transaction.commit();
    return catDto;
  }

  public CatDto addCat(String name, LocalDate birthday, String color, String breed,
      PersonDto owner) {
    Transaction transaction =  session.beginTransaction();

    Cat cat = new Cat(name, color, breed, birthday, PersonMapper.asDao(owner));

    long id = catDaoPostgres.addCat(cat, session);
    CatDto catDto = CatMapper.asDto(cat);
    catDto.setId(id);
    transaction.commit();
    return catDto;
  }

  public void updateCat(CatDto cat) {
    Transaction transaction =  session.beginTransaction();

    catDaoPostgres.updateCat(CatMapper.asDao(cat), session);
    transaction.commit();
  }

  public void addFriend(long catID, long friendID) {
    Transaction transaction =  session.beginTransaction();

    Cat cat = catDaoPostgres.findCatByID(catID, session);
    if (cat == null) {
      throw new NullPointerException("this cat does not exist");
    }
    Cat friend = catDaoPostgres.findCatByID(friendID, session);
    if (friend == null) {
      throw new NullPointerException("this cat does not exist");
    }
    catDaoPostgres.addFriend(cat, friend, session);
    transaction.commit();
  }

  public List<CatDto> getAllFriends(long id) {
    Transaction transaction =  session.beginTransaction();
    Cat cat = catDaoPostgres.findCatByID(id, session);
    if (cat == null) {
      throw new NullPointerException("this cat does not exist");
    }
    List<Cat> catList = catDaoPostgres.findAllFriends(cat);
    List<CatDto> friends = new ArrayList<>();
    for (Cat c : catList) {
      friends.add(CatMapper.asDto(c));
    }
    transaction.commit();
    return friends;
  }

  public void deleteCat(long id) {
    Transaction transaction =  session.beginTransaction();

    var cat = catDaoPostgres.findCatByID(id, session);
    if (cat == null) {
      throw new NullPointerException("this cat does not exist");
    }
    catDaoPostgres.deleteCat(cat, session);
    transaction.commit();
  }

}







