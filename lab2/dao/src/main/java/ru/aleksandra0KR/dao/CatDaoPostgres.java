package ru.aleksandra0KR.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.hibernate.HibernateSessionFactoryUtil;

public class CatDaoPostgres implements CatDao {

  @Override
  public Cat findCatByID(long id) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    Cat findCat = session.get(Cat.class, id);
    transaction.commit();
    session.close();
    return findCat;
  }

  @Override
  public long addCat(Cat cat) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.save(cat);
    transaction.commit();
    session.close();
    return cat.getId();
  }

  @Override
  public void updateCat(Cat cat) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.update(cat);
    transaction.commit();
    session.close();
  }

  @Override
  public void deleteCat(Cat cat) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.delete(cat);
    transaction.commit();
    session.close();
  }

  @Override
  public void addFriend(Cat cat, Cat friend) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
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
