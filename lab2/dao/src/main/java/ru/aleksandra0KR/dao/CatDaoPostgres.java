package ru.aleksandra0KR.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.aleksandra0KR.entity.Cat;

public class CatDaoPostgres implements CatDao {

  private final Session session;
  private final Transaction transaction;
  public CatDaoPostgres(Session session, Transaction transaction) {
    this.session = session;
    this.transaction = transaction;
  }

  @Override
  public Cat findCatByID(long id) {
    Cat findCat = session.get(Cat.class, id);
    transaction.commit();
    session.close();
    return findCat;
  }

  @Override
  public long addCat(Cat cat) {
    session.save(cat);
    transaction.commit();
    session.close();
    return cat.getId();
  }

  @Override
  public void updateCat(Cat cat) {
    session.update(cat);
    transaction.commit();
    session.close();
  }

  @Override
  public void deleteCat(Cat cat) {
    session.delete(cat);
    transaction.commit();
    session.close();
  }

  @Override
  public void addFriend(Cat cat, Cat friend) {
    cat.addFriend(friend);
    session.merge(cat);
    transaction.commit();
    session.close();
  }

  @Override
  public List<Cat> findAllFriends(Cat cat) {
    return cat.getFriends();
  }
}
