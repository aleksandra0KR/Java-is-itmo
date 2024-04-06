package ru.aleksandra0KR.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.hibernate.HibernateSessionFactoryUtil;

public class CatDaoPostgres implements CatDao {

  Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
  @Override
  public Cat findCatByID(long id) {
    Cat findCat = session.get(Cat.class, id);
    session.close();
    return findCat;
  }

  @Override
  public long addCat(Cat cat) {
    session.save(cat);
    session.close();
    return cat.getId();
  }

  @Override
  public void updateCat(Cat cat) {
    session.update(cat);
    session.close();
  }

  @Override
  public void deleteCat(Cat cat) {
    session.delete(cat);
    session.close();
  }

  @Override
  public void addFriend(Cat cat, Cat friend) {
    cat.addFriend(friend);
    session.merge(cat);
    session.close();
  }

  @Override
  public List<Cat> findAllFriends(Cat cat) {
    return cat.getFriends();
  }
}
