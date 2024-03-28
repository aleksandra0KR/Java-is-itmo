package ru.aleksandra0KR.dao;

import java.util.ArrayList;
import java.util.List;
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
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    String sql = "SELECT friend_id FROM Friends WHERE cat_id=:$0";
    Query query = session.createSQLQuery(sql);

    query.setParameter(0, id);

    List<Long> friends_id = (List<Long>) query.getResultList();
    List<Cat> friends = new ArrayList<>();

    for (long a : friends_id) {
      friends.add(findCatByID(a));
    }
    session.close();
    return friends;
  }

  @Override
  public void addFriend(Cat cat, Cat catsFriend) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    String sql = "INSERT INTO Friends (cat_id, friend_id) VALUES ($0, $1)";
    Query query = session.createSQLQuery(sql);

    Transaction transaction = session.beginTransaction();
    query.setParameter(0, cat.getId());
    query.setParameter(1, catsFriend.getFriends());
    query.executeUpdate();
    transaction.commit();
  }
}
