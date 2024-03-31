package ru.aleksandra0KR.dao;

import java.util.List;
import ru.aleksandra0KR.entity.Cat;

public interface CatDao {

  Cat findCatByID(long id);

  long addCat(Cat cat);

  void updateCat(Cat cat);

  void deleteCat(Cat cat);

  List<Cat> findAllFriends(Cat cat);

  void addFriend(Cat cat, Cat catsFriend);

}
