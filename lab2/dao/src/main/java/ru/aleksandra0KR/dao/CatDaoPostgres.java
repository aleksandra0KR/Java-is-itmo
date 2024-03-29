package ru.aleksandra0KR.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.hibernate.HibernateSessionFactoryUtil;

public class CatDaoPostgres implements CatDao {

  @Override
  public Cat findCatByID(long id) {
    return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Cat.class, id);
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
  public List<Cat> findAllFriends(long id) {
    var res = this.findCatByID(id).getFriends();
    return res;
  }

  @Override
  public void addFriend(Cat cat, Cat catsFriend) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    List<Cat> friends = cat.getFriends();
    friends.add(catsFriend);
    session.update(cat);
    transaction.commit();
    session.close();
  }
}
