package ru.aleksandra0KR.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.aleksandra0KR.entity.Cat;

public interface CatDao {
  Cat findCatByID(long id,  Session session);

  long addCat(Cat cat,  Session session);

  void updateCat(Cat cat,  Session session);

  void deleteCat(Cat cat,  Session session);

  List<Cat> findAllFriends(Cat cat);

  void addFriend(Cat cat, Cat catsFriend,  Session session);

}
