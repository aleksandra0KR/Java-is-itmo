package ru.aleksandra0KR.dao;

import java.util.List;
import org.hibernate.Session;
import ru.aleksandra0KR.entity.Cat;

public class CatDaoPostgres implements CatDao {

  @Override
  public Cat findCatByID(long id, Session session) {
    Cat findCat = session.get(Cat.class, id);
    return findCat;
  }

  @Override
  public long addCat(Cat cat, Session session) {
    session.save(cat);
    return cat.getId();
  }

  @Override
  public void updateCat(Cat cat, Session session) {
    session.update(cat);
  }

  @Override
  public void deleteCat(Cat cat, Session session) {
    session.delete(cat);
  }

  @Override
  public void addFriend(Cat cat, Cat friend, Session session) {
    cat.addFriend(friend);
    session.merge(cat);
  }

  @Override
  public List<Cat> findAllFriends(Cat cat) {
    return cat.getFriends();
  }
}
